package com.matheusrayol.exerciciomvc.service.impl;

import com.matheusrayol.exerciciomvc.model.Veiculo;
import com.matheusrayol.exerciciomvc.service.IVeiculoService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VeiculoServiceImpl implements IVeiculoService {

    @Override
    public List<Veiculo> listVeiculo() {
        return Arrays.asList(new Veiculo("VW", "Branco"), new Veiculo("BMW", "Preto"));
    }
}
