package application;

import models.Filme;

public class GradeDeFilmes implements IGradeDeFilmes {

    @Override
    public Filme getFilme(String nomeFilme) {
        Filme filme = null;

        switch(nomeFilme) {
            case "O Poderoso Chefão":
                filme = new Filme("O Poderoso Chefão", "Brasil", "https://www.youtube.com/watch?v=kqUHl5Ht_E0");
                break;
            case "Os 101 Dálmatas":
                filme = new Filme("Os 101 Dálmatas", "Argentina", "https://www.youtube.com/watch?v=kqUHl5Ht_E0");
                break;
            case "Super Xuxa Contra o Baixo-Astral":
                filme = new Filme("Super Xuxa Contra o Baixo-Astral", "Brasil", "https://www.youtube.com/watch?v=kqUHl5Ht_E0");
                break;
            case "Duna":
                filme = new Filme("Duna", "Colombia", "https://www.youtube.com/watch?v=kqUHl5Ht_E0");
                break;
        }

        return filme;
    }
}
