package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.dto.OrderDTO;
import com.newwave.ecommerce.dto.UserDTO;
import com.newwave.ecommerce.entity.Orders;
import com.newwave.ecommerce.service.impl.OrderServiceImpl;
import com.newwave.ecommerce.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserServiceImpl userService;
    private final OrderServiceImpl orderServiceImpl;

    public UserController(UserServiceImpl userService, OrderServiceImpl orderServiceImpl) {
        this.userService = userService;
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
    }

    @GetMapping("/user/profile")
    public ResponseEntity<UserDTO> getProfile(String username) {
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        return new ResponseEntity<>(userService.deleteUser(username), HttpStatus.OK);
    }

    @GetMapping("/user/history")
    public ResponseEntity<List<OrderDTO>> getHistory(@RequestParam String username) {
        return new ResponseEntity<>(orderServiceImpl.getOrdersByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/root/createAdmin")
    public ResponseEntity<String> createAdmin(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createAdmin(userDTO));
    }
    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

}
