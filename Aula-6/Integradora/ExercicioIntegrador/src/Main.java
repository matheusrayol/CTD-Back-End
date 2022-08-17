import application.GradeDeFilmes;
import application.GradeDeFilmesProxy;
import application.GradeDeSeries;
import application.GradeDeSeriesProxy;
import exceptions.FilmeNaoHabilitadoException;
import exceptions.SerieNaoHabilitadaException;
import models.Ip;

public class Main {
    public static void main(String[] args) {
        GradeDeFilmesProxy proxy = new GradeDeFilmesProxy(new GradeDeFilmes());
        proxy.setIp(new Ip(100, 23, 23, 25));

        try {
            System.out.println(proxy.getFilme("Duna").getLink());
            System.out.println("Acesso Liberado");
        } catch (FilmeNaoHabilitadoException e) {
            System.out.println(e);
        }

        GradeDeSeriesProxy proxy2 = new GradeDeSeriesProxy(new GradeDeSeries());
        proxy2.setQtdViews(4);

        try {
            System.out.println(proxy2.getSerie("Game of Thrones"));
            System.out.println("Acesso Liberado");
        } catch (SerieNaoHabilitadaException e) {
            System.out.println(e);
        }
    }
}