import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Cachorro> cachorros = new ArrayList<>();

        Cachorro cachorro1 = new Cachorro(3,"Pluto");
        Cachorro cachorro2 = new Cachorro(1, "Scooby");
        Cachorro cachorro3 = new Cachorro(5, "Beethoven");

        cachorros.add(cachorro1);
        cachorros.add(cachorro2);
        cachorros.add(cachorro3);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("OutputCachorros.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(cachorros);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Cachorro> cachorros1 = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("OutputCachorros.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            cachorros1 = (List<Cachorro>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Cachorro cachorro: cachorros1) {
            System.out.println(cachorro.getNome() + ": " + cachorro.getIdade());
        }
    }
}
