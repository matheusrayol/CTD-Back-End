package com.matheusrayol.avaliacaobackend.service;

import com.matheusrayol.avaliacaobackend.dao.ConfiguracaoJDBC;
import com.matheusrayol.avaliacaobackend.dao.impl.FilialDaoImpl;
import com.matheusrayol.avaliacaobackend.model.Filial;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilialServiceTest {

    private FilialService filialService = new FilialService(new FilialDaoImpl(new ConfiguracaoJDBC()));

    @Test
    public void salvarFilial1() {

        Filial filial = new Filial("Hotel Transilvania", "Rua das Flores", "123", "São Paulo", "SP", true);
        filialService.salvar(filial);

        assertTrue(filial.getId() != null);

    }

    @Test
    public void salvarFilial2() {

        Filial filial = new Filial("San Diego Express", "Avenida Barbacena", "41", "Belo Horizonte", "MG", false);
        filialService.salvar(filial);

        assertTrue(filial.getId() != null);
    }

    @Test
    public void salvarFilial3() {

        Filial filial = new Filial("Hotel Ibis Budget", "Rua das Acácias", "333", "São Paulo", "SP", true);
        filialService.salvar(filial);

        assertTrue(filial.getId() != null);
    }

    @Test
    public void salvarFilial4() {

        Filial filial = new Filial("Ho-Tel", "Rua A", "111B", "São Paulo", "SP", true);
        filialService.salvar(filial);

        assertTrue(filial.getId() != null);
    }

    @Test
    public void salvarFilial5() {

        Filial filial = new Filial("Hotel Transilvania", "Rua das Flores", "123", "Belo Horizonte", "MG", true);
        filialService.salvar(filial);

        assertTrue(filial.getId() != null);
    }

}