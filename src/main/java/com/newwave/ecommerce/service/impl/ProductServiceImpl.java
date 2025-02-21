package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    @Override
    public void addProduct(ProductDTO product) {
        if (productRepo.findByProductName(product.getProductName()) != null) {
            return;
        }
        Product productE = Product.builder()
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
        productRepo.save(productE);
    }

    @Override
    public String removeProductByName(String productName) {
        if (productRepo.findByProductName(productName) != null) {
            return "Product Not Found";
        }
        try {
            productRepo.delete(productRepo.findByProductName(productName));
        } catch (Exception e) {
            return "Lỗi: " + e.getMessage();
        }
        return "Success";
    }

    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productRepo.findByProductName(name);
        if (product == null) {
            return null;
        }
        return ProductDTO.builder()
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }


//    @Override
//    public List<Product> arrangeProductListA_Z(List<Product> productList) {
//        return List.of();
//    }
}
