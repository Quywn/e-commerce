package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.CartDTO;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.CartRepo;
import com.newwave.ecommerce.repository.ProductRepo;
import com.newwave.ecommerce.utils.BuildObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringBootTest
public class CartServiceImplTest {
    @InjectMocks
    private CartServiceImpl cartService;
    @Mock
    private CartRepo cartRepo;
    @Mock
    private ProductRepo productRepo;

    private final BuildObjectUtils buildObjectUtils = new BuildObjectUtils();
    @Test
    void getCartByUserTestHappyCase() {
        Optional<CartDTO> expected = buildObjectUtils.buildCartDTOOptional();
        when(cartRepo.findCartByUsername("usernameTest")).thenReturn(buildObjectUtils.buildCartOptional());
        CartDTO actual = cartService.getCartByUser("usernameTest");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getCartByUserTestCaseNotFound() {
        assertThrows(NotFoundException.class, () -> {
            when(cartRepo.findCartByUsername(anyString()))
                    .thenReturn(Optional.empty());
            cartService.getCartByUser("usernameTest");
        });
    }

    @Test
    void addProductToCartTestHappyCase() {
        String expect = "Add product successfully";
        when(productRepo.findByProductName(anyString())).thenReturn(buildObjectUtils.buildProductOptonal());
        when(cartRepo.findCartByUsername(anyString())).thenReturn(buildObjectUtils.buildCartOptional());
        String actual = cartService.addProductToCart(buildObjectUtils.buildProductDTO(), "usernameTest");
        verify(cartRepo, times(1)).save(any());
        assertEquals(expect, actual);
    }

}
