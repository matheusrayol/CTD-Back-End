package com.digitalhouse.clinicaprofessor.service.impl;

import com.digitalhouse.clinicaprofessor.entity.CategoryEntity;
import com.digitalhouse.clinicaprofessor.entity.dto.CategoryDTO;
import com.digitalhouse.clinicaprofessor.repository.CategoryRepository;
import com.digitalhouse.clinicaprofessor.service.ICommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICommerceService<CategoryDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity(categoryDTO);
        categoryRepository.create(categoryEntity);
        return categoryDTO;
    }

    @Override
    public CategoryDTO getById(int id) {
        return new CategoryDTO(categoryRepository.getById(id));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return null;
    }

    @Override
    public String delete(int id) {
        return null;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, int id) {
        return null;
    }

    public int getByName(String name) {
        return categoryRepository.getByName(name);
    }

}
