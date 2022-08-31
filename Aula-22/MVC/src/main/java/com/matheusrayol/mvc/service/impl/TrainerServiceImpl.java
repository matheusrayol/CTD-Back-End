package com.matheusrayol.mvc.service.impl;

import com.matheusrayol.mvc.model.Trainer;
import com.matheusrayol.mvc.service.ITrainerService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements ITrainerService {

    @Override
    public List<Trainer> listTrainer() {
        return Arrays.asList(new Trainer("Matheus"), new Trainer("Rayol"));

    }

}

