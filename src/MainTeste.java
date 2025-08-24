package src;

public class MainTeste {
    public static void main(String[] args) throws InterruptedException {
        ChefDeCozinha chef = new ChefDeCozinha();

        Pedido salmao = new Pedido("Salmão", 1);
        Pedido limao  = new Pedido("Limão", 1);

        Garcom carlos = new Garcom("Carlos", chef, salmao, limao);
        Garcom ana    = new Garcom("Ana", chef, limao, salmao);

        System.out.println("=== Cenário de Deadlock controlado para apresentação ===\n");

        carlos.start();
        ana.start();

        carlos.join();
        ana.join();

        System.out.println("\n--- Estoque final ---");
        System.out.println(salmao.getNome() + ": " + salmao.getQuantidade());
        System.out.println(limao.getNome() + ": " + limao.getQuantidade());
    }
}
