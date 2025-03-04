package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.entity.User;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.service.AdminService;

import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    private final ProductRepo productRepo;

    public AdminServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //admin
    @Override
    public String removeProductByName(String productName, User admin) {
        //todo: xác thực
        Optional<Product> product = productRepo.findByProductName(productName);

        if(product.isEmpty()) {
            return "Product not found";
        }
        try {
            productRepo.delete(product.get());
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Product removed: "+ product.get().getProductName();

    }

    @Override
    public ProductDTO addProduct(ProductDTO product, User admin) {
        //todo: xác thực
        Product productE = Product.builder()
                .productName(product.getProductName())
                .quantityStock(product.getQuantityStock())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();

        Product p = productRepo.save(productE);
        return ProductDTO.builder()
                .productName(p.getProductName())
                .quantityStock(p.getQuantityStock())
                .price(p.getPrice())
                .imageUrl(p.getImageUrl())
                .build();
    }
}
