package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.UserDTO;
import com.newwave.ecommerce.entity.User;
import com.newwave.ecommerce.repository.UserRepo;
import com.newwave.ecommerce.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String signUp(UserDTO userDTO) {
        if (userRepo.findByUsername(userDTO.getUsername()) != null || userRepo.findByEmail(userDTO.getEmail()) != null) {
            return "Username/email is already in use";
        }
        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        userRepo.save(user);
        return "Success";
    }
}
