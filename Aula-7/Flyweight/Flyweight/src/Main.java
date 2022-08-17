public class Main {
    public static void main(String[] args) {
        ComputadorFactory computadorFactory = new ComputadorFactory();

        Computador mac = computadorFactory.getComputador(8, 500);
        Computador win = computadorFactory.getComputador(16, 128);
        Computador mac2 = computadorFactory.getComputador(8, 500);

        System.out.println(mac.toString());
        System.out.println(win.toString());
        System.out.println(mac2.toString());
    }
}