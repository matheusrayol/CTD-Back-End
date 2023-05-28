/* Você deve manter uma lista de contatos em um arquivo.
 Os contatos possuem um nome, e-mail e telefone.
 Para fazer isso, sugerimos que você utilize o método main para
 salvar a coleção de contatos em um arquivo e, em seguida,
 recupere o conteúdo do arquivo para outra coleção e exiba a
 lista de contatos no console.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Contato> contatos = new ArrayList<>();

        Contato contato1 = new Contato("Matheus", "falecom@matheusrayol.com", "3132109876");
        Contato contato2 = new Contato("Fábio", "falecom@fabioneres.com", "3132109776");
        Contato contato3 = new Contato("Wallacy", "falecom@wallacydossantos.com", "3132109850");
        Contato contato4 = new Contato("Camila", "falecom@camilaferreira.com", "3132559876");

        contatos.add(contato1);
        contatos.add(contato2);
        contatos.add(contato3);
        contatos.add(contato4);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("ListaContatos");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(contatos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<Contato> listaContatos = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("ListaContatos");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            listaContatos = (List<Contato>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Contato contato: listaContatos) {
            System.out.println("Nome: " + contato.getNome() + "\nE-mail: " + contato.getEmail() + "\nTelefone: " + contato.getTelefone() + "\n\n");
        }
    }
}
