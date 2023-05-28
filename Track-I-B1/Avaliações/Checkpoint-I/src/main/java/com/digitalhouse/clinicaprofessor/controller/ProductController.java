package com.digitalhouse.clinicaprofessor.controller;

import com.digitalhouse.clinicaprofessor.entity.dto.ProductDTO;
import com.digitalhouse.clinicaprofessor.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        ResponseEntity responseEntity = null;

        if (productDTO.getTitle() != null) {
            ProductDTO productDTO1 = productService.create(productDTO);
            responseEntity = new ResponseEntity<>(productDTO1, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("Title not filled", HttpStatus.BAD_REQUEST);
        }

        return productService.create(productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable int id) {

        ResponseEntity responseEntity = null;
        ProductDTO productDTO = productService.getById(id);

        if (productDTO != null) {
            responseEntity = new ResponseEntity<>(productDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
        }

        return productService.getById(id);
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        return productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductDTO productDTO, @PathVariable int id) {
        return productService.update(productDTO, id);
    }

}
