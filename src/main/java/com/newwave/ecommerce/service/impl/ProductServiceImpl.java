package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
    public ProductDTO getProductByName(String name) {
        Optional<Product> product = productRepo.findByProductName(name);

        if(product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return product.map(value -> ProductDTO.builder()
                .productName(value.getProductName())
                .quantityStock(value.getQuantityStock())
                .price(value.getPrice())
                .imageUrl(value.getImageUrl())
                .build()).orElse(null);

    }

    @Override
    public List<ProductDTO> getProductListA_Z() {
        List<Product> productList = productRepo.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        ProductDTO productDTO;
        for (Product product : productList) {
            productDTO = ProductDTO.builder()
                    .productName(product.getProductName())
                    .quantityStock(product.getQuantityStock())
                    .price(product.getPrice())
                    .imageUrl(product.getImageUrl())
                    .build();
            productDTOList.add(productDTO);
        }

        return productDTOList.stream().sorted().toList();
    }
}
