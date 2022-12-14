package com.digitalhouse.clinicaprofessor.repository;

import com.digitalhouse.clinicaprofessor.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CategoryRepository {

    private static Map<Integer, CategoryEntity>  categoryMap = new HashMap<>();
    private static int idCategory = 1;

    public CategoryEntity create(CategoryEntity categoryEntity) {
        categoryEntity.setId(idCategory++);
        categoryMap.put(categoryEntity.getId(), categoryEntity);
        return categoryEntity;
    }

    public CategoryEntity getById(int id) {
        return categoryMap.get(id);
    }

    public int getByName(String name) {
        for (CategoryEntity categoryEntity : categoryMap.values()) {
            if (categoryEntity.getName().equalsIgnoreCase(name)) {
                return categoryEntity.getId();
            }
        }
        return 0;
    }

}
