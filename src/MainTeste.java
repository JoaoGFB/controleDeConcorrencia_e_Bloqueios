package src;

public class MainTeste {
    public static void main(String[] args) {
        ChefDeCozinha chef = new ChefDeCozinha();
        //criando garçons
        Garcom g1 = new Garcom("João");
        Garcom g2 = new Garcom("Maria");

        //criando comidas
        Pedido i1 = new Pedido("Carne", 1);
        Pedido i2 = new Pedido("Batata", 2);

        //João pede bloqueio exclusivo na Carne
        chef.solicitarBloqueio(g1, i1, TipoBloqueio.exclusivo);

        //Maria tenta pegar bloqueio compartilhado na Carne (não pode, pq já tá exclusivo)
        chef.solicitarBloqueio(g2, i1, TipoBloqueio.compartilhado);

        //Maria pede bloqueio compartilhado na Batata (vai funcionar)
        chef.solicitarBloqueio(g2, i2, TipoBloqueio.compartilhado);

        //João pede bloqueio compartilhado na Batata (funciona tbm, pq já é compartilhado)
        chef.solicitarBloqueio(g1, i2, TipoBloqueio.compartilhado);

        //liberar os bloqueios de Maria
        chef.liberarBloqueios(g2);

        //João tenta pedir bloqueio exclusivo na Batata (agora deve funcionar pq só ele tem compartilhado)
        chef.solicitarBloqueio(g1, i2, TipoBloqueio.exclusivo);

        //liberar os bloqueios de João
        chef.liberarBloqueios(g1);
    }
}