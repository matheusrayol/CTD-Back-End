package application;

import exceptions.SerieNaoHabilitadaException;
import models.Serie;

public interface IGradeDeSeries {

    String getSerie(String nomeSerie) throws SerieNaoHabilitadaException;

}
