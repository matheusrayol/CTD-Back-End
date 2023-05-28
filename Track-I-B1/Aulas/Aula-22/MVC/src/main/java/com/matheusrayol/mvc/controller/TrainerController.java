package com.matheusrayol.mvc.controller;

import com.matheusrayol.mvc.model.Trainer;
import com.matheusrayol.mvc.service.impl.TrainerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    private TrainerServiceImpl trainerService;

    @GetMapping
    public List<Trainer> getTrainer() {
        return trainerService.listTrainer();
    }



}
