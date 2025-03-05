package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.UserDTO;
import com.newwave.ecommerce.entity.User;
import com.newwave.ecommerce.repository.UserRepo;
import com.newwave.ecommerce.secure.UserPrincipal;
import com.newwave.ecommerce.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    public String register(UserDTO userDTO) {
        if (userRepo.findByUsername(userDTO.getUsername()) != null || userRepo.findByEmail(userDTO.getEmail()) != null) {
            return "Username/email is already in use";
        }
        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        return "Success saved user +" + userRepo.save(user);
    }

    @Override
    public UserDTO getUser(String username) {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO deleteUser(String username, String password) {
        return null;
    }

}
