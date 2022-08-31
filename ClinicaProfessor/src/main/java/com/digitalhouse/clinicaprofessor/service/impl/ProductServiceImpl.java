package com.digitalhouse.clinicaprofessor.service.impl;

import com.digitalhouse.clinicaprofessor.entity.ProductEntity;
import com.digitalhouse.clinicaprofessor.entity.dto.ProductDTO;
import com.digitalhouse.clinicaprofessor.repository.ProductRepository;
import com.digitalhouse.clinicaprofessor.service.ICommerceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ICommerceService<ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        ProductEntity productEntity = mapperToDTOEntity(productDTO);
        productEntity.setCategoryId(categoryService.getByName(productDTO.getCategory()));
        if (productEntity.getCategoryId() != 0) productRepository.create(productEntity);
        return productDTO;

    }

    @Override
    public ProductDTO getById(int id) {
        ProductEntity productEntity = productRepository.getById(id);
        ProductDTO productDTO = mapperEntityToDTO(productEntity);
        productDTO.setCategory(categoryService.getById(productEntity.getCategoryId()).getName());
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> productEntities = productRepository.getAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (ProductEntity productEntity : productEntities) {
            ProductDTO productDTO = mapperEntityToDTO(productEntity);
            productDTO.setCategory(categoryService.getById(productEntity.getCategoryId()).getName());
            productDTOList.add(productDTO);
        }
        return productDTOList;

    }

    @Override
    public String delete(int id) {
        return productRepository.delete(id);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO, int id) {
        ProductEntity productEntity = mapperToDTOEntity(productDTO);
        String categoryName = productDTO.getCategory();
        int categoryId = categoryService.getByName(categoryName);
        productEntity.setCategoryId(categoryId);
        productEntity.setId(id);

        if (productEntity.getCategoryId() != 0)
            productRepository.create(productEntity);

        return productDTO;

    }

    private ProductEntity mapperToDTOEntity(ProductDTO productDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductEntity productEntity = objectMapper.convertValue(productDTO, ProductEntity.class);
        return productEntity;
    }

    private ProductDTO mapperEntityToDTO(ProductEntity productEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDTO productDTO = objectMapper.convertValue(productEntity, ProductDTO.class);
        return productDTO;
    }

}

