package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.CategoryDTO;
import com.newwave.ecommerce.service.CategoryService;
import com.newwave.ecommerce.service.impl.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/admin/category")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.addCategory(categoryDTO), HttpStatus.CREATED);
    }

    @GetMapping("/user/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/user/category")
    public ResponseEntity<CategoryDTO> getCategory(String categoryName) {
        return new ResponseEntity<>(categoryService.getCategory(categoryName), HttpStatus.OK);
    }

    @DeleteMapping("/admin/category")
    public ResponseEntity<String> deleteCategory(String categoryName) {
        return new ResponseEntity<>(categoryService.deleteCategory(categoryName), HttpStatus.OK);
    }

}
