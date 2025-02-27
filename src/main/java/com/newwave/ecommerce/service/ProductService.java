package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductByName(String name);
    List<ProductDTO> getProductListA_Z();
}