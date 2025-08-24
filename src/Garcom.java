package src;

public class Garcom {
    private String nome;

    public Garcom(String nome) {
        this.setNome(nome);
    }

    public void consultarIngrediente(Pedido ingrediente) {
        System.out.println(this.nome+" consultou "+ingrediente.getNome()+" (estoque: "+ingrediente.getQuantidade()+")");
    }

    public void fazerPedido(Pedido ingrediente, int qtd) {
        System.out.println(this.nome+" vai usar "+qtd+" de "+ingrediente.getNome());
        ingrediente.consumir(qtd);
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}