package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.User;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductByName(String name);
    List<ProductDTO> getProductListA_Z();
    List<ProductDTO> getProductsPage(int page, int pageSize);
    String removeProductByName(String productName, User admin);
    ProductDTO addProduct(ProductDTO product, User admin);
    List<ProductDTO> getProductsByCategory(String categoryName, User admin);
}