public class Main {
    public static void main(String[] args) {

        Cartao cartao = new Cartao("1234567890123456", "Star Bank");
        Produto produto = new Produto("Batatas", "Pacote");

        FacadeDesconto facadeDesconto = new FacadeDesconto();
        System.out.println(facadeDesconto.calcularDesconto(cartao, produto, 15));

    }
}