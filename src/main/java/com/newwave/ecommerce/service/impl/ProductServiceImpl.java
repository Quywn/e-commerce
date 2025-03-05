package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.entity.User;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return productDTOList;
    }


    @Override
    public ProductDTO getProductByName(String name) {
        Optional<Product> product = productRepo.findByProductName(name);

        if(product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return ProductDTO.builder()
                .productName(product.get().getProductName())
                .quantityStock(product.get().getQuantityStock())
                .price(product.get().getPrice())
                .imageUrl(product.get().getImageUrl())
                .build();

    }

    @Override
    public List<ProductDTO> getProductListA_Z() {
        List<ProductDTO> productDTOList = getAllProducts();
        return productDTOList.stream().sorted().toList();
    }

    @Override
    public List<ProductDTO> getProductsPage(int page, int pageSize) {
        List<ProductDTO> list = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productPage = productRepo.findAll(pageable);
        productPage.stream().toList().forEach(product -> {
            productDTO.setProductName(product.getProductName());
            productDTO.setQuantityStock(product.getQuantityStock());
            productDTO.setPrice(product.getPrice());
            productDTO.setImageUrl(product.getImageUrl());
            list.add(productDTO);
        });
        return list;
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

    @Override
    public List<ProductDTO> getProductsByCategory(String categoryName, User admin) {
        //todo
        return List.of();
    }
}
