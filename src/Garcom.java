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
            // Pega o primeiro ingrediente
            waitEnter("[" + getName() + "] tentando bloquear " + primeiroIngrediente.getNome());
            if (!chef.solicitarBloqueio(this, primeiroIngrediente, TipoBloqueio.exclusivo)) {
                System.out.println("[" + getName() + "] não conseguiu bloquear " + primeiroIngrediente.getNome());
                return;
            }
            waitEnter("[" + getName() + "] pegou " + primeiroIngrediente.getNome() +
                      " (pressione Enter para continuar antes do segundo ingrediente)");

            // Tenta pegar o segundo ingrediente
            if (!chef.solicitarBloqueio(this, segundoIngrediente, TipoBloqueio.exclusivo)) {
                System.out.println("[" + getName() + "] bloqueio negado para " + segundoIngrediente.getNome() +
                        ", esperando intervenção do Chef...");
                Thread.sleep(500);

                // Chef intervém liberando apenas os bloqueios necessários para resolver deadlock
                System.out.println(">>> CHEF INTERVEM: Deadlock detectado para " + getName());
                chef.resolverDeadlock(); // novo método para liberar bloqueios de forma segura

                // Tenta o segundo ingrediente **apenas uma vez após intervenção**
                if (!chef.solicitarBloqueio(this, segundoIngrediente, TipoBloqueio.exclusivo)) {
                    System.out.println("[" + getName() + "] ainda não conseguiu o segundo ingrediente, abortando pedido.");
                    return;
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
