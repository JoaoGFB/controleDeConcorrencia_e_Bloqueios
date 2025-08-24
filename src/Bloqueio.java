package src;

public class Bloqueio {
    private final Pedido recurso;
    private final TipoBloqueio tipo;
    private final String dono;

    public Bloqueio(Pedido recurso, TipoBloqueio tipo, String dono) {
        this.recurso = recurso;
        this.tipo = tipo;
        this.dono = dono;
    }

    public boolean eCompativel(TipoBloqueio novoTipo, String novoDono) {
        if (dono.equals(novoDono)) return true;
        return tipo == TipoBloqueio.compartilhado && novoTipo == TipoBloqueio.compartilhado;
    }

    public Pedido getRecurso() {
        return recurso;
    }

    public TipoBloqueio getTipo() {
        return tipo;
    }

    public String getDono() {
        return dono;
    }

    @Override
    public String toString() {
        return "Bloqueio{" +
                "recurso=" + recurso.getNome() +
                ", tipo=" + tipo +
                ", dono='" + dono + '\'' +
                '}';
    }
}
