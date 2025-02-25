package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO removeProductByName(String productName);
    ProductDTO getProductByName(String name);
    List<ProductDTO> getProductListA_Z();
}