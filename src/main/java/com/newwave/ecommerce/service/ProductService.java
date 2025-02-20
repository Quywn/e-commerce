package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    //admin
    public boolean addProduct(Product product);
    public boolean removeProduct(Product product);

    //user
    //tìm kiếm sản phẩm
    public ProductDTO getProductByName(String name);
    public List<Product> arrangeProductList(List<Product> productList);
    //phân trang sản phẩm theo giá tiền 0-5, 5-10, >10
}