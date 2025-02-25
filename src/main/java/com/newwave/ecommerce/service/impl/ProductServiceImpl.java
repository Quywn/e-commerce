package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductDTO> getAllProducts() {
         productRepo.findAll();
         return null;
    }
    @Override
    public ProductDTO addProduct(ProductDTO product) {
        if (productRepo.findByProductName(product.getProductName()) != null) {
            System.out.println("Product exists");

        }
        Product productE = Product.builder()
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
        Product p = productRepo.save(productE);

        return ProductDTO.builder()
                .productName(p.getProductName())
                .quantity(p.getQuantity())
                .price(p.getPrice())
                .imageUrl(p.getImageUrl())
                .build();
    }

    @Override
    public String removeProductByName(String productName) {
        if (productRepo.findByProductName(productName) != null) {
            System.out.println("Product Not Found");
        }
        try {
            productRepo.deleteProductByProductName(productName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
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

    @Override
    public List<ProductDTO> getProductListA_Z() {
        List<Product> productList = productRepo.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        ProductDTO productDTO;
        for (Product product : productList) {
            productDTO = ProductDTO.builder()
                    .productName(product.getProductName())
                    .quantity(product.getQuantity())
                    .price(product.getPrice())
                    .imageUrl(product.getImageUrl())
                    .build();
            productDTOList.add(productDTO);
        }

        return productDTOList.stream().sorted().toList();
    }
}
