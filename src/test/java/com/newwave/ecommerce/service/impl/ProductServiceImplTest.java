package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CategoryDTO;
import com.newwave.ecommerce.repository.CategoryRepo;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.utils.BuildObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceImplTest {
    @Mock
    private CategoryServiceImpl categoryServiceImpl;
    @Mock
    private CategoryRepo categoryRepo;
    @Mock
    private ProductRepo productRepo;
    @InjectMocks
    private ProductServiceImpl productService;
    BuildObjectUtils buildObjectUtils = new BuildObjectUtils();

    @BeforeEach
    public void setup() {
        productService = new ProductServiceImpl(productRepo, categoryRepo, categoryServiceImpl);
    }

    @Test
    void addProduct() {
        CategoryDTO categoryDTO = buildObjectUtils.buildCategoryDTO();
        categoryDTO.setCategoryCode("0001");
        when(categoryServiceImpl.addCategory(any())).thenReturn(categoryDTO);
        productService.addProduct(buildObjectUtils.buildProductDTO());
        verify(productRepo, times(1)).save(any());

    }
}
