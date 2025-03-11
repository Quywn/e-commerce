package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CategoryDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Category;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.CategoryRepo;
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
    private final CategoryRepo categoryRepo;
    private final CategoryServiceImpl categoryService;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, CategoryServiceImpl categoryService) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.categoryService = categoryService;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        ProductDTO productDTO;
        for (Product product : productList) {
            productDTO = ProductDTO.builder()
                    .productCode(product.getProductCode())
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
                .productCode(product.get().getProductCode())
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
            productDTO.setProductCode(product.getProductCode());
            list.add(productDTO);
        });
        return list;
    }

    @Override
    public String removeProductByName(String productName) {
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
    public ProductDTO addProduct(ProductDTO product) {
        Product productE = Product.builder()
                .productCode(generateProductCode(product.getCategory()))
                .productName(product.getProductName())
                .quantityStock(product.getQuantityStock())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();

        productRepo.save(productE);
        return ProductDTO.builder()
                .productCode(productE.getProductCode())
                .productName(productE.getProductName())
                .quantityStock(productE.getQuantityStock())
                .price(productE.getPrice())
                .imageUrl(productE.getImageUrl())
                .build();
    }


    @Override
    public List<ProductDTO> getProductsByCategory(String categoryName) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> productList = productRepo.findByCategory_CategoryName(categoryName);
        for (Product product : productList) {
            productDTOList.add(ProductDTO.builder()
                    .productCode(product.getProductCode())
                    .productName(product.getProductName())
                    .quantityStock(product.getQuantityStock())
                    .price(product.getPrice())
                    .imageUrl(product.getImageUrl())
                    .build());
        }
        return productDTOList;
    }

    //format: {product_code}_xxxxxx | xxxxxx : [000001 - 999999]
    private String generateProductCode(CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepo.findByCategoryName(categoryDTO.getCategoryName());
        long count;
        if (category.isEmpty()) {
            CategoryDTO newCategory = categoryService.addCategory(categoryDTO);
            count = 0;
            return newCategory.getCategoryCode() + "_" + String.format("%06d", (int)(count + 1));
        }
        count = productRepo.countByCategory(category.get());
        return category.get().getCategoryCode() + "_" + String.format("%06d", (int)(count + 1));
    }
}
