public class FacadeFactory {

    private FlyweightQuadrado flyweightQuadrado;
    private FlyweightTriangulo flyweightTriangulo;

    public FacadeFactory() {
        this.flyweightQuadrado = new FlyweightQuadrado();
        this.flyweightTriangulo = new FlyweightTriangulo();
    }

    public void criarFormas(String[][] listaFormas) {
        for (int i = 0; i < listaFormas.length; i++) {
            if (listaFormas[i][0].equals("quadrado")) {
                Quadrado quadrado = flyweightQuadrado.obterQuadrado(Integer.parseInt(listaFormas[i][1]));
                quadrado.setCor(listaFormas[i][2]);
            } else if (listaFormas[i][0].equals("triangulo")) {
                Triangulo triangulo = flyweightTriangulo.obterTriangulo(listaFormas[i][2]);
                triangulo.setTamanho(Integer.parseInt(listaFormas[i][1]));
            }
        }
    }
}
