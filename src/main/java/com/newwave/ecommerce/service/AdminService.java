package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.User;

public interface AdminService {
    String removeProductByName(String productName, User admin);
    ProductDTO addProduct(ProductDTO product, User admin);

}
