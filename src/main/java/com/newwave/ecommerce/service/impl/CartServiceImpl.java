package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.common.Utils;
import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.domain.ProductDTO;
import com.newwave.ecommerce.entity.Cart;
import com.newwave.ecommerce.entity.Product;
import com.newwave.ecommerce.exception.InsufficientStockException;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.CartRepo;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.service.CartService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    public final CartRepo cartRepo;
    public final ProductRepo productRepo;
    Utils utils = new Utils();

    public CartServiceImpl(CartRepo cartRepo, ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    @Override
    public String addProductToCart(ProductDTO product, String username) {
        this.utils.checkAuthentication(username);
        Optional<Product> productE = productRepo.findByProductName(product.getProductName());
        if (productE.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        if (productE.get().getQuantityStock() < product.getQuantityOrdered()) {
            throw new InsufficientStockException("Insufficient stock");
        }

        if (cartRepo.findCartByUsername(username).isEmpty()) {
            createCart(productE.get(), username, product.getQuantityOrdered());
            return "Add product successfully";
        }

        Cart c = cartRepo.findCartByUsername(username).get();
        Map<Product, Integer> orderProducts = c.getOrderProducts();
        orderProducts.put(productE.get(), product.getQuantityOrdered());
        c.setOrderProducts(orderProducts);
        cartRepo.save(c);
        return "Add product successfully";
    }


    @Override
    public String removeProductFromCart(String productName, String username) {
        this.utils.checkAuthentication(username);
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

        return "Delete product successfully";
    }

    public Double getCartTotal(CartDTO cart) {
//        this.utils.checkAuthentication(cart.getUsername());
//        double cartTotal = 0.0;
//        Map<Product, Integer> productsOrderList = cart.getOrderedProducts();
//        for (Map.Entry<Product, Integer> entry : productsOrderList.entrySet()) {
//            Product product = entry.getKey();
//            Integer quantityOrdered = entry.getValue();
//            cartTotal += product.getQuantityOrdered() * quantityOrdered;
//        }
        return 0.0;

    }

    @Override
    public CartDTO getCartByUser(String username) {
        this.utils.checkAuthentication(username);
        if (cartRepo.findCartByUsername(username).isEmpty()) {
            throw new NotFoundException("Cart not found by username: " + username);
        } else {
            Cart c = cartRepo.findCartByUsername(username).get();
            Map<ProductDTO, Integer> productDTOIntegerMap = new HashMap<>(Map.of());
            //todo check value
            c.getOrderProducts().forEach((product, value) -> {
                productDTOIntegerMap.put(buildProductDTO(product), value);
            });

            CartDTO cartDTO = new CartDTO();
            cartDTO.setCartId(c.getCartId());
            cartDTO.setUsername(c.getUsername());
            cartDTO.setOrderedProducts(productDTOIntegerMap);
            return cartDTO;
        }
    }

    @Override
    public CartDTO clearCartByUser(String username) {
        this.utils.checkAuthentication(username);
        Optional<Cart> cartOptional = cartRepo.findCartByUsername(username);
        if (cartOptional.isEmpty()) {
            throw new NotFoundException("Cart not found by username: " + username);
        }
        Cart c = new Cart();
        c.setUsername(username);
        c.setCartId(cartOptional.get().getCartId());
        cartRepo.save(c);
        return getCartByUser(username);
    }

    private void createCart(Product productE, String username, int quantityOrdered) {
        Cart cart = new Cart();
        cart.setUsername(username);
        productE.setQuantityOrdered(quantityOrdered);
        Map<Product, Integer> orderProducts = new HashMap<>();
        orderProducts.put(productE, quantityOrdered);
        cart.setOrderProducts(orderProducts);
        cartRepo.save(cart);
    }

    private ProductDTO buildProductDTO(Product product) {
        return ProductDTO.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .quantityStock(product.getQuantityStock())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }}
