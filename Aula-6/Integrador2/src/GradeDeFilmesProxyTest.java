import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeDeFilmesProxyTest {

    GradeDeFilmesProxy proxy = new GradeDeFilmesProxy(new GradeDeFilmes());

    @Test
    void testeGetFilmeArgentina() throws FilmeNaoHabilitadoException {
        proxy.setIp(new Ip(40, 23, 23, 25));
        Filme filme = proxy.getFilme("The White Tiger");

        assertEquals("The White Tiger", filme.getNome());
        assertEquals("Argentina", filme.getPais());
        assertEquals("www.wtiger.com", filme.getLink());
    }

    @Test
    void testeGetFilmeBrasil() throws FilmeNaoHabilitadoException {
        proxy.setIp(new Ip(93, 23, 23, 25));
        Filme filme = proxy.getFilme("His House");

        assertEquals("His House", filme.getNome());
        assertEquals("Brasil", filme.getPais());
        assertEquals("www.hhouse.com", filme.getLink());
    }

    @Test
    void testeGetFilmeColombia() throws FilmeNaoHabilitadoException {
        proxy.setIp(new Ip(118, 23, 23, 25));
        Filme filme = proxy.getFilme("Over the Moon");

        assertEquals("Over the Moon", filme.getNome());
        assertEquals("Colombia", filme.getPais());
        assertEquals("www.omoon.com", filme.getLink());
    }

    @Test
    void testGetFilmeErro() {
        proxy.setIp(new Ip(2, 23, 23, 25));

        FilmeNaoHabilitadoException thrown = assertThrows(
                FilmeNaoHabilitadoException.class,
                () -> proxy.getFilme("Over the Moon")
        );

        assertTrue(thrown.getMessage().contains("Over the Moon não disponível no(a) Argentina"));
    }
}