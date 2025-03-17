package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CategoryDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Category;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.exception.AlreadyExistsException;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.CategoryRepo;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList) {
            productDTOList.add(buildProductDTO(product));
        }

        return productDTOList;
    }


    @Override
    public ProductDTO getProductByName(String name) {
        Optional<Product> product = productRepo.findByProductName(name);

        if(product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return buildProductDTO(product.get());
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
            buildProductDTO(product);
            list.add(productDTO);
        });

        return list;
    }

    @Override
    public String removeProductByName(String productName) {
        try {
            productRepo.deleteProductByProductName(productName);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "Product removed success";

    }

    @Override
    public ProductDTO addProduct(ProductDTO product) {

        if (productRepo.findByProductName(product.getProductName()).isPresent()) {
            throw new AlreadyExistsException("Product already exists");
        }

        Product productE = buildProduct(product);
        productRepo.save(productE);

        return buildProductDTO(productE);
    }


    @Override
    public List<ProductDTO> getProductsByCategory(String categoryName) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> productList = productRepo.findByCategory_CategoryName(categoryName);

        for (Product product : productList) {
            productDTOList.add(buildProductDTO(product));
        }

        return productDTOList;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO product) {

        if (productRepo.findByProductName(product.getProductName()).isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        Product productE = buildProduct(product);
        productRepo.deleteProductByProductName(product.getProductName());
        productRepo.save(productE);

        return buildProductDTO(productE);
    }

    private Product buildProduct(ProductDTO productDTO) {
        Optional<Category> category = categoryRepo.findByCategoryName(productDTO.getCategory().getCategoryName());

        if (category.isEmpty()) {
            throw new NotFoundException("Category not found. Please first add category: "
                    + productDTO.getCategory().getCategoryName());
        }

        return Product.builder()
                .productCode(generateProductCode(category.get()))
                .productName(productDTO.getProductName())
                .category(category.get())
                .quantityStock(productDTO.getQuantityStock())
                .price(productDTO.getPrice())
                .imageUrl(productDTO.getImageUrl())
                .build();
    }

    private ProductDTO buildProductDTO(Product product) {
        return ProductDTO.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .quantityStock(product.getQuantityStock())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }

    //format: {product_code}_xxxxxx | xxxxxx : [000001 - 999999]
    private String generateProductCode(Category category) {
        long count = productRepo.countByCategory(category);
        return category.getCategoryCode() + "_" + String.format("%06d", (int)(count + 1));
    }
}
