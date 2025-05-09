package com.newwave.ecommerce.service;

import com.newwave.ecommerce.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductByName(String name);
    List<ProductDTO> getProductListA_Z();
    List<ProductDTO> getProductsPage(int page, int pageSize);
    String removeProductByName(String productName);
    ProductDTO addProduct(ProductDTO product);
    List<ProductDTO> getProductsByCategory(String categoryName);
    ProductDTO updateProduct(ProductDTO product);
    List<ProductDTO> searchProducts(String keyword);
}