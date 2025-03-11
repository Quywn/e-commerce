package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategory(String categoryName);
    List<CategoryDTO> getAllCategories();
    String deleteCategory(String categoryName);
}
