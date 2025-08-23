package src;

public class Garcom {
    private String nome;

    public void consultarIngrediente(Ingrediente ingrediente) {
        System.out.println(this.nome + " consultou " + ingrediente.getNome() +
                " (estoque: " + ingrediente.getQuantidade() + ")");
    }

    public void fazerPedido(Ingrediente ingrediente, int qtd) {
        System.out.println(this.nome + " vai usar " + qtd + " de " + ingrediente.getNome());
        ingrediente.consumir(qtd);
    }

    public Garcom(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
}