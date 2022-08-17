public class Main {
    public static void main(String[] args) {

        FlyweightTriangulo flyweightTriangulo = new FlyweightTriangulo();

        Triangulo triangulo = flyweightTriangulo.obterTriangulo("vermelho");
        triangulo.setTamanho(2);
        Triangulo triangulo2 = flyweightTriangulo.obterTriangulo("vermelho");
        triangulo2.setTamanho(4);
        Triangulo triangulo3 = flyweightTriangulo.obterTriangulo("azul");
        triangulo3.setTamanho(6);

        System.out.println(flyweightTriangulo.getTrianguloHashMap().size());
        System.out.println(triangulo.toString());
        System.out.println(triangulo2.toString());
        System.out.println(triangulo3.toString());


        FlyweightQuadrado flyweightQuadrado = new FlyweightQuadrado();

        Quadrado quadrado = flyweightQuadrado.obterQuadrado(4);
        quadrado.setCor("Preto");
        Quadrado quadrado2 = flyweightQuadrado.obterQuadrado(4);
        quadrado2.setCor("Vermelho");
        Quadrado quadrado3 = flyweightQuadrado.obterQuadrado(8);
        quadrado3.setCor("Marrom");

        String[][] lista = {
                {"triangulo", "vermelho", "2"},
                {"triangulo", "vermelho", }
        }

    }
}