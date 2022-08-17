import java.util.HashMap;

public class FlyweightQuadrado {

    private HashMap<Integer, Quadrado> quadradoHashMap = new HashMap<>();

    public Quadrado obterQuadrado(int tamanho) {

        Quadrado quadrado = quadradoHashMap.get(tamanho);

        if (quadrado == null) {
            quadrado = new Quadrado(tamanho);
            quadradoHashMap.put(tamanho, quadrado);
        }

        return quadrado;
    }

    public HashMap<Integer, Quadrado> getQuadradoHashMap() {
        return quadradoHashMap;
    }

}
