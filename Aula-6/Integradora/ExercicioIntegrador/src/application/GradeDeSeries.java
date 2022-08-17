package application;

import models.Serie;

public class GradeDeSeries implements IGradeDeSeries {

    private String nome;
    private String url;

    @Override
    public String getSerie(String serie) {
        this.nome = serie;
        this.url = "www." + serie.replace(" ", "").toLowerCase() + ".com";
        return this.url;
    }

}
