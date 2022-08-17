public class Main {
    public static void main(String[] args) {
        CheckEmail checkEmail = new CheckEmail();
        checkEmail.verificar(new Email("email@email.com", "tecnica@dh.com", "Reclamação"));
        checkEmail.verificar(new Email("email@email.com", "kajshbcdhjka@dh.com", "Gerencia"));
        checkEmail.verificar(new Email("email@email.com", "juan@gmail.com", "Comercial"));
        checkEmail.verificar(new Email("email@email.com", "tecnica@colmeia.com", "Reclamação"));
    }
}
