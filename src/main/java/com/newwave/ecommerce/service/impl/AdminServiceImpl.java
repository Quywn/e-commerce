package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.service.AdminService;

import java.util.Optional;

public class AdminServiceImpl implements AdminService {
//    //admin
//    @Override
//    public String removeProductByName(String productName) {
//        Optional<Product> product = productRepo.findByProductName(productName);
//
//        if(product.isEmpty()) {
//            return "Product not found";
//        }
//        try {
//            productRepo.delete(product.get());
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//        return "Product removed: "+ product.get().getProductName();
//
//    }

//    @Override
//    public ProductDTO addProduct(ProductDTO product) {
//        Product productE = Product.builder()
//                .productName(product.getProductName())
//                .quantity(product.getQuantity())
//                .price(product.getPrice())
//                .imageUrl(product.getImageUrl())
//                .build();
//
//        Product p = productRepo.save(productE);
//        return ProductDTO.builder()
//                .productName(p.getProductName())
//                .quantity(p.getQuantity())
//                .price(p.getPrice())
//                .imageUrl(p.getImageUrl())
//                .build();
//    }


}
