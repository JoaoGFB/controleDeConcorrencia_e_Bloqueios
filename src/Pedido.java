package src;

public class Pedido {
    private String nome;
    private int quantidade;

    public Pedido(String nome, int quantidade) {
        this.setNome(nome);
        this.setQuantidade(quantidade);
    }

    public void consumir(int qtd) {
        if (qtd <= this.quantidade) {
            this.quantidade -= qtd;
        } else {
            throw new IllegalStateException("Ingrediente insuficiente: " + this.nome);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
