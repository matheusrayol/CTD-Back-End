package com.matheusrayol.aulaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AulaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AulaSpringApplication.class, args);
    }

    @GetMapping
    public String Ola() {
        return "Hello World";
    }

}
