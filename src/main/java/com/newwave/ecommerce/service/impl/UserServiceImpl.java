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

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user.get());
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    public String register(UserDTO userDTO) {
        if (userRepo.findByUsername(userDTO.getUsername()).isPresent() || userRepo.findByEmail(userDTO.getEmail()) != null) {
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
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserDTO.builder()
                .username(user.get().getUsername())
                .email(user.get().getEmail())
                .build();
    }

    @Override
    public String updateUser(UserDTO userDTO) {
        Optional<User> user = userRepo.findByUsername(userDTO.getUsername());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User newUser = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        return "User success updated: " + userRepo.save(newUser).getUsername();
    }

    @Override
    public String deleteUser(String username, String password) {
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        userRepo.delete(user.get());
        return "User success deleted: " + username;
    }

}
