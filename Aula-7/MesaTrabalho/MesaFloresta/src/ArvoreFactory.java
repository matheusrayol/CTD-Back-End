import java.util.HashMap;
import java.util.Map;

/* A floresta é um conjunto de árvores e permitirá o plantio de árvores.
ArvoreFactory será o local onde os diferentes tipos de árvores serão
armazenados. Isso permitirá, antes de criar o objeto, validar se já existe
um idêntico ao que está sendo solicitado. Em caso afirmativo, retorna o
objeto existente; se não existir, ele cria o novo objeto e o armazena em
cache para ser reutilizado posteriormente. */

public class ArvoreFactory {

    private static Map<String, Arvore> arvoreMap = new HashMap<>();

    public Arvore getArvore(String tipo, int altura, int largura, String cor) {
        String id = "Tipo: " + tipo + ", Altura: " + altura + ", Largura: " + largura + ", Cor: " + cor;

        System.out.println("Arvore: " + id);

        if (arvoreMap.containsKey(id)) {
            Arvore arvore = arvoreMap.get(id);
            arvore.setContador(arvore.getContador() + 1);
            return arvore;
        } else {
            arvoreMap.put(id, new Arvore(altura, largura, cor, tipo));
            return arvoreMap.get(id);
        }
    }
}
