package com.matheusrayol.exerciciomvc.controller;

import com.matheusrayol.exerciciomvc.model.Veiculo;
import com.matheusrayol.exerciciomvc.service.impl.VeiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/listaveiculos")
public class TrainerController {

    @Autowired
    private VeiculoServiceImpl veiculoService;

    @GetMapping
    public List<Veiculo> getVeiculo() {
        return veiculoService.listVeiculo();
    }
}
