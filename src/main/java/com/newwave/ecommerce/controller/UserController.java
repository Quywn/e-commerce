package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.UserDTO;
import com.newwave.ecommerce.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserServiceImpl userService;
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(String username) {
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(String username, String password) {
        return new ResponseEntity<>(userService.deleteUser(username, password), HttpStatus.OK);
    }

}
