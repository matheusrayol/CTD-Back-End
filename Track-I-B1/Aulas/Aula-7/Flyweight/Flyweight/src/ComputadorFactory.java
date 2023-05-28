import java.util.HashMap;
import java.util.Map;

public class ComputadorFactory {

    private static Map<String, Computador> computadorMap = new HashMap<>();

    public Computador getComputador(int ram, int hd) {
        String id = "ID: " + ram + ":" + hd;

        System.out.println("Criando computador " + id);

        if (computadorMap.containsKey(id)) {
            Computador computador = computadorMap.get(id);
            computador.setContador(computador.getContador() + 1);
            return computador;
        } else {
            computadorMap.put(id, new Computador(ram, hd));
            return computadorMap.get(id);
        }
    }

}
