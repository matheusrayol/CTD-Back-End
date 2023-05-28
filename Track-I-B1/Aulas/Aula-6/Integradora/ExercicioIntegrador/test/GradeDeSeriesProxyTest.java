import application.GradeDeSeries;
import application.GradeDeSeriesProxy;
import exceptions.SerieNaoHabilitadaException;
import org.junit.jupiter.api.Test;

public class GradeDeSeriesProxyTest {

    @Test
    void testException() {
        GradeDeSeriesProxy gradeDeSeriesProxy = new GradeDeSeriesProxy(new GradeDeSeries());
        try {
            System.out.println(gradeDeSeriesProxy.getSerie("Game of Thrones"));
            System.out.println(gradeDeSeriesProxy.getSerie("Locke and Key"));
            System.out.println(gradeDeSeriesProxy.getSerie("Dexter"));
        } catch(SerieNaoHabilitadaException e) {
            System.out.println(e);
        }
    }
}
