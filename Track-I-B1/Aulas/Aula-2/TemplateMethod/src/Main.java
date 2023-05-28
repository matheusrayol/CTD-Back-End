public class Main {
    public static void main(String[] args) {

        Efetivo efetivo = new Efetivo("Odair", "Antunes", "1234567890", 3000.0,
                1000.0, 500.0);

        Funcionario contratado = new Contratado("Marlene", "Peixoto", "9876543210", 60.0,
                50);

        efetivo.pagamentoSalario();
        contratado.pagamentoSalario();
    }
}
