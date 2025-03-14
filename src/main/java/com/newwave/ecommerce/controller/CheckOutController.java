package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.service.impl.CheckOutServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckOutController {
    private final CheckOutServiceImpl checkOutService;

    public CheckOutController(CheckOutServiceImpl checkOutService) {
        this.checkOutService = checkOutService;
    }

    @PostMapping("/user/checkout")
    public ResponseEntity<String> checkOut(@RequestParam String username) {
        return new ResponseEntity<>(checkOutService.checkOutCart(username), HttpStatus.OK);
    }
}
