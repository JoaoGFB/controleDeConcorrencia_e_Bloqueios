package src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChefDeCozinha {
    private final List<Bloqueio> bloqueios = new ArrayList<>();

    public boolean solicitarBloqueio(Garcom garcom, Pedido ingredientes, TipoBloqueio tipo) {
        Iterator<Bloqueio> it = bloqueios.iterator();
        while (it.hasNext()) {
            Bloqueio b = it.next();
            if (b.getRecurso().equals(ingredientes)) {//verifica se o ingrediente está bloqueado
                if (!b.eCompativel(tipo, garcom.getNome())) {//verifica se o tipo é compatível
                    System.out.println("Bloqueio negado para "+garcom.getNome()+
                            " em "+ingredientes.getNome()+" (já está com "+b.getTipo()+" por "+b.getDono()+")");
                    return false;//caso esteja bloqueado e o tipo é imcompatível o bloqueio é negado
                }
            }
        }//caso contrário cria um novo bloqueio
        Bloqueio novo = new Bloqueio(ingredientes, tipo, garcom.getNome());
        bloqueios.add(novo);
        System.out.println("Bloqueio "+tipo+" concedido para "+garcom.getNome() + " em "+ingredientes.getNome());
        return true;
    }

    public void liberarBloqueios(Garcom garcom) {
        System.out.println("Liberando bloqueios de "+garcom.getNome()+"...");
        System.out.println("Antes: "+this.bloqueios);
        bloqueios.removeIf(b -> b.getDono().equals(garcom.getNome()));
        System.out.println("Depois: "+this.bloqueios);
    }
}
