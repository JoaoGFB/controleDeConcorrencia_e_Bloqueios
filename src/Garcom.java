package src;

import java.util.Scanner;

public class Garcom extends Thread {
    private final ChefDeCozinha chef;
    private final Pedido primeiroIngrediente;
    private final Pedido segundoIngrediente;
    private final Scanner scanner = new Scanner(System.in);

    public Garcom(String nome, ChefDeCozinha chef, Pedido primeiro, Pedido segundo) {
        super(nome);
        this.chef = chef;
        this.primeiroIngrediente = primeiro;
        this.segundoIngrediente = segundo;
    }

    @Override
    public void run() {
        try {
            // 1) Tentar bloquear o primeiro ingrediente
            waitEnter("[" + getName() + "] tentando bloquear " + primeiroIngrediente.getNome());
            if (!chef.solicitarBloqueio(this, primeiroIngrediente, TipoBloqueio.exclusivo)) {
                System.out.println("[" + getName() + "] não conseguiu bloquear " + primeiroIngrediente.getNome());
                return;
            }

            // Pausa para garantir que outro garçom pegue seu primeiro ingrediente
            waitEnter("[" + getName() + "] pegou " + primeiroIngrediente.getNome() +
                    " (pressione Enter para continuar antes do segundo ingrediente)");

            long start = System.currentTimeMillis();
            boolean avisoMostrado = false;

            // 2) Tentar bloquear o segundo ingrediente (pode gerar deadlock)
            while (!chef.solicitarBloqueio(this, segundoIngrediente, TipoBloqueio.exclusivo)) {
                if (!avisoMostrado) {
                    System.out.println("[" + getName() + "] bloqueio negado para " + segundoIngrediente.getNome() + ", aguardando...");
                    avisoMostrado = true;
                }
                Thread.sleep(50);

                if (System.currentTimeMillis() - start > 500) {
                    System.out.println(">>> CHEF INTERVEM: Deadlock detectado para " + getName());
                    chef.liberarBloqueios(this);
                    Thread.sleep(50);
                    chef.solicitarBloqueio(this, primeiroIngrediente, TipoBloqueio.exclusivo);
                    avisoMostrado = false; // reset
                }
            }

            waitEnter("[" + getName() + "] consumindo ingredientes");
            consumirIngrediente(primeiroIngrediente);
            consumirIngrediente(segundoIngrediente);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            chef.liberarBloqueios(this);
        }
    }

    private void consumirIngrediente(Pedido ingrediente) {
        synchronized (ingrediente) {
            if (ingrediente.getQuantidade() > 0) {
                ingrediente.consumir(1);
                System.out.println("[" + getName() + "] consumiu 1 " + ingrediente.getNome());
            } else {
                System.out.println("[" + getName() + "] não encontrou " + ingrediente.getNome());
            }
        }
    }

    private void waitEnter(String mensagem) {
        System.out.println(mensagem + " (pressione Enter para continuar)");
        scanner.nextLine();
    }
}
