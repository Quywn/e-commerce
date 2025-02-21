package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.UserDTO;
import com.newwave.ecommerce.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserServiceImpl userService;
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody UserDTO user) {
        try {
            userService.signUp(user);
        } catch (Exception e) {
            return "Signup failed. Lỗi: "+ e.getMessage();
        }
        return "Signup successful";
    }
}
