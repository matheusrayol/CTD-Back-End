public class Main {
    public static void main(String[] args) {

        ArvoreFactory arvoreFactory = new ArvoreFactory();

        Arvore ornamental1 = new Arvore(200, 400, "Verde", "Ornamental");
        Arvore frutifera1 = new Arvore(500, 300, "Vermelho", "Frutifera");
        Arvore florifera1 = new Arvore(100, 200, "Azul", "Florifera");

        System.out.println(ornamental1.toString());
        System.out.println(frutifera1.toString());
        System.out.println(florifera1.toString());
    }
}