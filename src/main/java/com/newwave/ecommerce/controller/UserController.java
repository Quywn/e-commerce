package com.newwave.ecommerce.controller;

import com.newwave.ecommerce.domain.UserDTO;
import com.newwave.ecommerce.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
//    @PostMapping("/updateMail")
//    public ResponseEntity<UserDTO> updateMail(@RequestBody UserDTO user) {
//        return new ResponseEntity<>(userService.updateEmailByUsername(user), HttpStatus.OK);
//    }
//
//    @PostMapping("/updatePass")
//    public ResponseEntity<UserDTO> updatePass(@RequestBody UserDTO user) {
//        return new ResponseEntity<>(userService.updatePasswordByUsername(user), HttpStatus.OK);
//    }

}
