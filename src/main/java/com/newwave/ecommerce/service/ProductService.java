package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    //admin
    void addProduct(ProductDTO productDTO);
    String removeProductByName(String productName);

    //user
    //tìm kiếm sản phẩm
    ProductDTO getProductByName(String name);
//    List<Product> arrangeProductListA_Z(List<Product> productList);
    //phân trang sản phẩm theo giá tiền 0-5, 5-10, >10
}