package application;

import exceptions.FilmeNaoHabilitadoException;
import models.Filme;

public interface IGradeDeFilmes {

    public Filme getFilme(String nomeFilme) throws FilmeNaoHabilitadoException;

}
