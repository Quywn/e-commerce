package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CategoryDTO;
import com.newwave.ecommerce.entity.Category;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.CategoryRepo;
import com.newwave.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public String addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryCode(generateCategoryCode());
        return "Save success category: "+ categoryRepo.save(category);
    }
    private String generateCategoryCode() {
        long count = categoryRepo.count();
        return String.format("%04d", (int)(count + 1));  // 4 ký tự mã Category
    }

    @Override
    public CategoryDTO getCategory(String categoryName) {
        Optional<Category> category = categoryRepo.findByCategoryName(categoryName);
        if (category.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(category.get().getCategoryName());
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        List<Category> categories = categoryRepo.findAll();
        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    @Override
    public String deleteCategory(String categoryName) {
        Optional<Category> category = categoryRepo.findByCategoryName(categoryName);
        if (category.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        categoryRepo.delete(category.get());
        return "Delete completed: "+ categoryName;

    }
}
