package src;

public class MainTeste {
    public static void main(String[] args) throws InterruptedException {
        ChefDeCozinha chef = new ChefDeCozinha();

        Pedido salmao = new Pedido("Salmão", 1);
        Pedido limao  = new Pedido("Limão", 1);

        // Carlos pega Salmão primeiro, depois Limão
        Garcom carlos = new Garcom("Carlos", chef, salmao, limao);

        // Ana pega Limão primeiro, depois Salmão
        Garcom ana    = new Garcom("Ana", chef, limao, salmao);

        System.out.println("=== Cenário de Deadlock controlado para apresentação ===");
        System.out.println("Carlos tenta pegar Salmão e depois Limão");
        System.out.println("Ana tenta pegar Limão e depois Salmão\n");

        // Inicia as threads
        carlos.start();
        ana.start();

        carlos.join();
        ana.join();

        System.out.println("\n--- Estoque final ---");
        System.out.println(salmao.getNome() + ": " + salmao.getQuantidade());
        System.out.println(limao.getNome() + ": " + limao.getQuantidade());
    }
}
