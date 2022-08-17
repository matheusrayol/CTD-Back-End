public class ApiProduto {

    public int desconto(Produto produto) {
        if (produto.getTipo().equals("Latas")) {
            return 10;
        } else {
            return 0;
        }
    }

}
