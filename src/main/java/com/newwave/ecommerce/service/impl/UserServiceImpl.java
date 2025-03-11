package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.domain.RoleDTO;
import com.newwave.ecommerce.domain.UserDTO;
import com.newwave.ecommerce.entity.Role;
import com.newwave.ecommerce.entity.User;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.RoleRepo;
import com.newwave.ecommerce.repository.UserRepo;
import com.newwave.ecommerce.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    public String register(UserDTO userDTO) {
        if (userRepo.findByUsername(userDTO.getUsername()).isPresent() || userRepo.findByEmail(userDTO.getEmail()) != null) {
            return "Username/email is already in use";
        }
        List<Role> rolesEntity = new ArrayList<>();
        for (RoleDTO role : userDTO.getRoles()) {
            Optional<Role> roleEntity = roleRepo.findRoleByRoleName(role.getRoleName());
            if (roleEntity.isEmpty()) {
                throw new NotFoundException("Role with name " + role.getRoleName() + " not exists");
            } else {
                rolesEntity.add(roleEntity.get());
            }
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(rolesEntity)
                .build();
        try {
            userRepo.save(user);
        } catch (Exception e) {
            return "Save failed. " + e.getMessage();
        }
        return "Success saved user: " + userDTO.getUsername();
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
