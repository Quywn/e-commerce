package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Cart;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.exception.InsufficientStockException;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.CartRepo;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.service.CartService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    public final CartRepo cartRepo;
    public final ProductRepo productRepo;

    public CartServiceImpl(CartRepo cartRepo, ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    @Override
    public CartDTO addProductToCart(ProductDTO product, String username) {
        if (productRepo.findByProductName(product.getProductName()).isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        if (productRepo.findByProductName(product.getProductName()).get().getQuantityStock()
                < product.getQuantityOrdered()) {
            throw new InsufficientStockException("Insufficient stock");
        }

        if (cartRepo.findCartByUsername(username).isEmpty()) {
            createCart(product, username);
        }

        Cart c = cartRepo.findCartByUsername(username).get();
        Map<Product, Integer> orderProducts = c.getOrderProducts();
        orderProducts.put(Product.builder()
                        .productName(product.getProductName())
                        .quantityOrdered(product.getQuantityOrdered())
                        .quantityStock(product.getQuantityStock())
                        .imageUrl(product.getImageUrl())
                        .price(product.getPrice())
                        .build(), product.getQuantityOrdered());
        c.setOrderProducts(orderProducts);
        cartRepo.save(c);
        return getCartByUser(username).get();
    }


    @Override
    public CartDTO removeProductFromCart(String productName, String username) {
        Optional<Cart> cartOptional = cartRepo.findCartByUsername(username);
        if (cartOptional.isEmpty()) {
            throw new NotFoundException("Cart not found by username: " + username);
        }
        Map<Product, Integer> orderProducts = cartOptional.get().getOrderProducts();
        orderProducts.forEach((product, value) -> {
            if (product.getProductName().equals(productName)) {
                orderProducts.remove(product);
            }
        });
        Cart cart = Cart.builder()
                .cartId(cartOptional.get().getCartId())
                .username(username)
                .orderProducts(orderProducts)
                .build();

        cartRepo.save(cart);

        return getCartByUser(username).get();
    }

    public Double getCartTotal(CartDTO cart) {
        double cartTotal = 0.0;
        Map<Product, Integer> productsOrderList = cart.getOrderedProducts();
        for (Map.Entry<Product, Integer> entry : productsOrderList.entrySet()) {
            Product product = entry.getKey();
            Integer quantityOrdered = entry.getValue();
            cartTotal += product.getQuantityOrdered() * quantityOrdered;
        }
        return cartTotal;

    }

    @Override
    public Optional<CartDTO> getCartByUser(String username) {
        if (cartRepo.findCartByUsername(username).isEmpty()) {
            throw new NotFoundException("Cart not found by username: " + username);
        } else {
            Cart c = cartRepo.findCartByUsername(username).get();
            CartDTO cartDTO = new CartDTO();
            cartDTO.setCartId(c.getCartId());
            cartDTO.setUsername(c.getUsername());
            cartDTO.setOrderedProducts(c.getOrderProducts());
            return Optional.of(cartDTO);
        }
    }

    @Override
    public CartDTO clearCartByUser(String username) {
        Optional<Cart> cartOptional = cartRepo.findCartByUsername(username);
        if (cartOptional.isEmpty()) {
            throw new NotFoundException("Cart not found by username: " + username);
        }
        Cart c = new Cart();
        c.setUsername(username);
        c.setCartId(cartOptional.get().getCartId());
        cartRepo.save(c);
        return getCartByUser(username).get();
    }

    private void createCart(ProductDTO productDTO, String username) {
        Cart cart = new Cart();
        cart.setUsername(username);
        Map<Product, Integer> orderProducts = new HashMap<>();
        Product product = Product.builder()
                .imageUrl(productDTO.getImageUrl())
                .price(productDTO.getPrice())
                .quantityOrdered(productDTO.getQuantityOrdered())
                .quantityStock(productDTO.getQuantityStock())
                .productName(productDTO.getProductName())
                .build();
        orderProducts.put(product, product.getQuantityOrdered());
        cart.setOrderProducts(orderProducts);
        cartRepo.save(cart);
    }

}
