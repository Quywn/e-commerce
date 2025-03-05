package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CategoryDTO;
import com.newwave.ecommerce.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO getCategory(String categoryName) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return List.of();
    }

    @Override
    public String deleteCategory(String categoryName) {
        return "";
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        return null;
    }
}
