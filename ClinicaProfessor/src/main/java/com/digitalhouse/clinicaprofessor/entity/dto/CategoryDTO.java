package com.digitalhouse.clinicaprofessor.entity.dto;

import com.digitalhouse.clinicaprofessor.entity.CategoryEntity;
import org.springframework.stereotype.Service;

public class CategoryDTO {

    private String name;

    public CategoryDTO(CategoryEntity categoryEntity) {
        this.name = categoryEntity.getName();
    }

    public CategoryDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

}
