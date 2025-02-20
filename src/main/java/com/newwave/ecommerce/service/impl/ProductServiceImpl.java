package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
//ToDo:
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public boolean removeProduct(Product product) {
        return false;
    }

    @Override
    public ProductDTO getProductByName(String name) {
        return null;
    }

    @Override
    public List<Product> arrangeProductList(List<Product> productList) {
        return List.of();
    }
}
