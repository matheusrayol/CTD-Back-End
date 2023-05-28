package com.matheusrayol.medicamentos.service;

import com.matheusrayol.medicamentos.dao.IDao;
import com.matheusrayol.medicamentos.model.Medicamento;

public class MedicamentoService {

    private IDao<Medicamento> medicamentoIDao;

    public MedicamentoService(IDao<Medicamento> medicamentoIDao) {
        this.medicamentoIDao = medicamentoIDao;
    }

    public Medicamento salvar(Medicamento medicamento) {
        return medicamentoIDao.salvar(medicamento);
    }
}