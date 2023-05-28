package com.matheusrayol.avaliacaobackend.service;

import com.matheusrayol.avaliacaobackend.dao.IDao;
import com.matheusrayol.avaliacaobackend.model.Filial;

public class FilialService {

    private IDao<Filial> filialIDao;

    public FilialService(IDao<Filial> filialIDao) {
        this.filialIDao = filialIDao;
    }

    public Filial salvar(Filial filial) {
        return filialIDao.salvar(filial);
    }

}
