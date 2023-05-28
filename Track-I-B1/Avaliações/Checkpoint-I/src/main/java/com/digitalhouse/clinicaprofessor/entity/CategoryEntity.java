package com.digitalhouse.clinicaprofessor.entity;

import com.digitalhouse.clinicaprofessor.entity.dto.CategoryDTO;

public class CategoryEntity {

    private int id;

    private String name;

    public CategoryEntity(CategoryDTO categoryDTO) {
        this.name = categoryDTO.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
