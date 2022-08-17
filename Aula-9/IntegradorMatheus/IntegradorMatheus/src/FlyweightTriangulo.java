import java.util.HashMap;

public class FlyweightTriangulo {

    private HashMap<String, Triangulo> trianguloHashMap = new HashMap<>();

    public Triangulo obterTriangulo(String cor) {

        Triangulo triangulo = trianguloHashMap.get(cor);

        if (triangulo == null) {
            triangulo = new Triangulo(cor);
            trianguloHashMap.put(cor, triangulo);
        }

        return triangulo;
    }

    public HashMap<String, Triangulo> getTrianguloHashMap() {
        return trianguloHashMap;
    }

}
