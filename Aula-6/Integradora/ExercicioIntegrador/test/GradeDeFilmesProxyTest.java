import application.GradeDeFilmes;
import application.GradeDeFilmesProxy;
import exceptions.FilmeNaoHabilitadoException;
import models.Filme;
import models.Ip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GradeDeFilmesProxyTest {

    GradeDeFilmesProxy proxy = new GradeDeFilmesProxy(new GradeDeFilmes());

    @Test
    void testeGetFilmeArgentina() throws FilmeNaoHabilitadoException {
        proxy.setIp(new Ip(40, 23, 23, 25));
        Filme filme = proxy.getFilme("Os 101 Dálmatas");

        assertEquals("Os 101 Dálmatas", filme.getNome());
        assertEquals("Argentina", filme.getPais());
        assertEquals("https://www.youtube.com/watch?v=kqUHl5Ht_E0", filme.getLink());
    }

    @Test
    void testeGetFilmeBrasil() throws FilmeNaoHabilitadoException {
        proxy.setIp(new Ip(93, 23, 23, 25));
        Filme filme = proxy.getFilme("Super Xuxa Contra o Baixo-Astral");

        assertEquals("Super Xuxa Contra o Baixo-Astral", filme.getNome());
        assertEquals("Brasil", filme.getPais());
        assertEquals("https://www.youtube.com/watch?v=kqUHl5Ht_E0", filme.getLink());
    }

    @Test
    void testeGetFilmeColombia() throws FilmeNaoHabilitadoException {
        proxy.setIp(new Ip(118, 23, 23, 25));
        Filme filme = proxy.getFilme("Duna");

        assertEquals("Duna", filme.getNome());
        assertEquals("Colombia", filme.getPais());
        assertEquals("https://www.youtube.com/watch?v=kqUHl5Ht_E0", filme.getLink());
    }

    @Test
    void testGetFilmeErro() {
        proxy.setIp(new Ip(2, 23, 23, 25));

        FilmeNaoHabilitadoException thrown = assertThrows(
                FilmeNaoHabilitadoException.class,
                () -> proxy.getFilme("Super Xuxa Contra o Baixo-Astral")
        );

        assertTrue(thrown.getMessage().contains("Super Xuxa Contra o Baixo-Astral não disponível no(a) Argentina"));
    }

}
