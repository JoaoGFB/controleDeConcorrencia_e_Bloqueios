package src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChefDeCozinha {
    private final List<Bloqueio> bloqueios = new ArrayList<>();

    public boolean solicitarBloqueio(Garcom garcom, Pedido ingrediente, TipoBloqueio tipo) {
        Iterator<Bloqueio> it = bloqueios.iterator();
        while (it.hasNext()) {
            Bloqueio b = it.next();
            if (b.getRecurso().equals(ingrediente)) {
                if (!b.eCompativel(tipo, garcom.getName())) {
                    return false;
                }
            }
        }
        bloqueios.add(new Bloqueio(ingrediente, tipo, garcom.getName()));
        System.out.println("Bloqueio " + tipo + " concedido para " + garcom.getName() + " em " + ingrediente.getNome());
        return true;
    }

    public void liberarBloqueios(Garcom garcom) {
        bloqueios.removeIf(b -> b.getDono().equals(garcom.getName()));
        System.out.println("Chef liberou todos os bloqueios de " + garcom.getName());
    }

    // Resolve deadlock liberando apenas bloqueios que travam outros
    public void resolverDeadlock() {
        System.out.println(">>> CHEF liberando bloqueios para resolver deadlock...");
        bloqueios.clear(); // simplificação: libera todos os bloqueios para continuar
    }
}
