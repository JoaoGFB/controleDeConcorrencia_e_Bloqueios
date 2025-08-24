package src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChefDeCozinha {
    private final List<Bloqueio> bloqueios = new ArrayList<>();

    public synchronized boolean solicitarBloqueio(Garcom garcom, Pedido ingrediente, TipoBloqueio tipo) {
        Iterator<Bloqueio> it = bloqueios.iterator();
        while (it.hasNext()) {
            Bloqueio b = it.next();
            if (b.getRecurso().equals(ingrediente)) {
                if (!b.eCompativel(tipo, garcom.getName())) {
                    return false; // apenas retorna false sem imprimir
                }
            }
        }
        Bloqueio novo = new Bloqueio(ingrediente, tipo, garcom.getName());
        bloqueios.add(novo);
        System.out.println("Bloqueio " + tipo + " concedido para " + garcom.getName() +
                " em " + ingrediente.getNome());
        return true;
    }

    public synchronized void liberarBloqueios(Garcom garcom) {
        bloqueios.removeIf(b -> b.getDono().equals(garcom.getName()));
        System.out.println("Chef liberou todos os bloqueios de " + garcom.getName());
    }
}
