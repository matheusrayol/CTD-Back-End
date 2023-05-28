package application;

import exceptions.SerieNaoHabilitadaException;

public class GradeDeSeriesProxy implements IGradeDeSeries {

    private int qtdViews;

    private IGradeDeSeries gradeDeSeries;

    public GradeDeSeriesProxy(GradeDeSeries gradeDeSeries) {
        this.qtdViews = 0;
        this.gradeDeSeries = new GradeDeSeries();
    }

    @Override
    public String getSerie(String nomeSerie) throws SerieNaoHabilitadaException {
        String url = null;
        this.qtdViews++;
        if (this.qtdViews < 6) {
            url = this.gradeDeSeries.getSerie(nomeSerie);
        } else {
            throw new SerieNaoHabilitadaException("A serie " + nomeSerie + " não está habilitada");
        }
        return url;
    }

    public int getQtdViews() {
        return qtdViews;
    }

    public void setQtdViews(int qtdViews) {
        this.qtdViews = qtdViews;
    }

    public IGradeDeSeries getGradeDeSeries() {
        return gradeDeSeries;
    }

    public void setGradeDeSeries(IGradeDeSeries gradeDeSeries) {
        this.gradeDeSeries = gradeDeSeries;
    }

}
