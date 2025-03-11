package com.newwave.ecommerce.utils;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.CategoryDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Cart;
import com.newwave.ecommerce.entity.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BuildObjectUtils {

    public Optional<CartDTO> buildCartDTOOptional() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUsername("usernameTest");
        cartDTO.setCartId(1L);
        cartDTO.setOrderedProducts(buildOrderProducts());
        return Optional.of(cartDTO);
    }

    public Optional<Cart> buildCartOptional() {
        return Optional.of(Cart.builder()
                .cartId(1L)
                .username("usernameTest")
                .orderProducts(buildOrderProducts())
                .build());
    }

    Map<Product, Integer> buildOrderProducts() {
        Map<Product, Integer> orderProducts = new HashMap<>();
        orderProducts.put(Product.builder()
                .price(1.0)
                .quantityOrdered(1)
                .quantityStock(100)
                .imageUrl("imageUrlTest")
                .productName("productNameTest")
                .build(), 1);
        return orderProducts;
    }

    public ProductDTO buildProductDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName("categoryNameTest");
        ProductDTO product = new ProductDTO();
        product.setProductName("productNameTest");
        product.setPrice(1.0);
        product.setCategory(categoryDTO);
        product.setQuantityStock(100);
        product.setQuantityOrdered(1);
        product.setImageUrl("imageUrlTest");
        return product;
    }

    public Optional<Product> buildProductOptonal() {
        Product product = Product.builder()
                .productName("productNameTest")
                .price(1.0)
                .quantityStock(100)
                .quantityOrdered(1)
                .imageUrl("imageUrlTest")
                .build();
        return Optional.of(product);
    }


    public CategoryDTO buildCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName("categoryNameTest");
        return categoryDTO;
    }
}
