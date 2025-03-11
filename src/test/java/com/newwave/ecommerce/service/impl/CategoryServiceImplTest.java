package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CategoryDTO;
import com.newwave.ecommerce.repository.CategoryRepo;
import com.newwave.ecommerce.utils.BuildObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CategoryServiceImplTest {
    @Mock
    private CategoryRepo categoryRepo;
    @InjectMocks
    private CategoryServiceImpl categoryService;
    BuildObjectUtils buildObjectUtils = new BuildObjectUtils();

    @Test
    void addCategory() {
        CategoryDTO expect = new CategoryDTO();
        expect.setCategoryName("categoryNameTest");
        expect.setCategoryCode("0001");
        CategoryDTO actual = categoryService.addCategory(buildObjectUtils.buildCategoryDTO());
        verify(categoryRepo, times(1)).save(any());
        Assertions.assertEquals(expect, actual);
    }
}
