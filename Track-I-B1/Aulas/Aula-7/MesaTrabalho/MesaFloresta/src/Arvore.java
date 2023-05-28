public class Arvore {

    private String id;

    private int altura;
    private int largura;
    private String cor;
    private String tipo;
    private int contador;

    public Arvore(int altura, int largura, String cor, String tipo) {
        this.altura = altura;
        this.largura = largura;
        this.cor = cor;
        this.tipo = tipo;
        this.contador = 1;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}
