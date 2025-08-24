package src;

public class Pedido {
    private final String nome;
    private int quantidade;

    public Pedido(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public synchronized void consumir(int qtd) {
        if (qtd <= quantidade) {
            quantidade -= qtd;
        } else {
            throw new IllegalStateException("Ingrediente insuficiente: " + nome);
        }
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
