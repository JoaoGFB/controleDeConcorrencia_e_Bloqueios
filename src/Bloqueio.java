package src;

enum TipoBloqueio {
    compartilhado,
    exclusivo
}

public class Bloqueio {
    private Pedido recurso;
    private TipoBloqueio tipo;
    private String dono; // Nome do garçom

    public Bloqueio(Pedido recurso, TipoBloqueio tipo, String dono) {
        this.setRecurso(recurso);
        this.setTipo(tipo);
        this.setDono(dono);
    }

    public boolean eCompativel(TipoBloqueio novoTipo, String novoDono) {
        if (this.dono.equals(novoDono))
            return true;
        if (this.tipo == TipoBloqueio.compartilhado && novoTipo == TipoBloqueio.compartilhado)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Bloqueio{" +
                "recurso=" + recurso.getNome() +
                ", tipo=" + tipo +
                ", dono='" + dono + '\'' +
                '}';
    }

    public Pedido getRecurso() {
        return recurso;
    }
    public void setRecurso(Pedido recurso) {
        this.recurso = recurso;
    }
    public TipoBloqueio getTipo() {
        return tipo;
    }
    public void setTipo(TipoBloqueio tipo) {
        this.tipo = tipo;
    }
    public String getDono() {
        return dono;
    }
    public void setDono(String dono) {
        this.dono = dono;
    }
}
