package com.newwave.ecommerce.service.impl;

import com.newwave.ecommerce.dto.RoleDTO;
import com.newwave.ecommerce.dto.UserDTO;
import com.newwave.ecommerce.entity.Role;
import com.newwave.ecommerce.entity.User;
import com.newwave.ecommerce.exception.NotFoundException;
import com.newwave.ecommerce.repository.RoleRepo;
import com.newwave.ecommerce.repository.UserRepo;
import com.newwave.ecommerce.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public String register(UserDTO userDTO) {
        if (userRepo.findByUsername(userDTO.getUsername()).isPresent() ||
                userRepo.findByEmail(userDTO.getEmail()) != null) {
            return "Username/email is already in use";
        }

        List<Role> rolesEntity = new ArrayList<>();

        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
            // Gán role mặc định là USER nếu không có role được truyền vào
            Optional<Role> defaultRole = roleRepo.findRoleByRoleName("USER");
            if (defaultRole.isEmpty()) {
                throw new NotFoundException("Default role USER not exists");
            }
            rolesEntity.add(defaultRole.get());
        } else {
            for (RoleDTO role : userDTO.getRoles()) {
                Optional<Role> roleEntity = roleRepo.findRoleByRoleName(role.getRoleName());
                if (roleEntity.isEmpty()) {
                    throw new NotFoundException("Role with name " + role.getRoleName() + " not exists");
                }
                rolesEntity.add(roleEntity.get());
            }
        }

        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getNewPassword()))
                .roles(rolesEntity)
                .address(userDTO.getAddress())
                .phone(userDTO.getPhone())
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

        User u = user.get();
        return UserDTO.builder()
                .username(u.getUsername())
                .email(u.getEmail())
                .address(u.getAddress())
                .phone(u.getPhone())
                .roles(u.getRoles().stream().map(r ->
                        RoleDTO.builder().roleName(r.getRoleName()).build()).toList())
                .build();
    }

    @Override
    public String updateUser(UserDTO userDTO) {
        Optional<User> optionalUser = userRepo.findByUsername(userDTO.getUsername());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = optionalUser.get();

        // Cập nhật thông tin
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());

        // Nếu yêu cầu đổi mật khẩu
        if (userDTO.getNewPassword() != null && !userDTO.getNewPassword().isBlank()) {
            if (userDTO.getOldPassword() == null || userDTO.getOldPassword().isBlank()) {
                throw new IllegalArgumentException("Cần nhập mật khẩu hiện tại để đổi mật khẩu.");
            }

            if (!passwordEncoder.matches(userDTO.getOldPassword(), user.getPassword())) {
                throw new IllegalArgumentException("Mật khẩu hiện tại không đúng.");
            }

            user.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));
        }

        userRepo.save(user);
        return "User updated successfully: " + user.getUsername();
    }

    @Override
    public String deleteUser(String username) {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        userRepo.delete(user.get());
        return "User success deleted: " + username;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();

        return users.stream().map(u -> UserDTO.builder()
                        .username(u.getUsername())
                        .email(u.getEmail())
                        .address(u.getAddress())
                        .phone(u.getPhone())
                        .roles(u.getRoles().stream().map(r ->
                                RoleDTO.builder().roleName(r.getRoleName()).build()).toList())
                        .build())
                .toList();
    }

    @Override
    public String createAdmin(UserDTO userDTO) {
        if (userRepo.findByUsername(userDTO.getUsername()).isPresent() ||
                userRepo.findByEmail(userDTO.getEmail()) != null) {
            return "Username/email is already in use";
        }

        Optional<Role> adminRole = roleRepo.findRoleByRoleName("ADMIN");
        if (adminRole.isEmpty()) {
            throw new NotFoundException("Role ADMIN not exists");
        }

        User admin = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getNewPassword()))
                .roles(List.of(adminRole.get()))
                .address(userDTO.getAddress())
                .phone(userDTO.getPhone())
                .build();

        try {
            userRepo.save(admin);
        } catch (Exception e) {
            return "Save failed. " + e.getMessage();
        }

        return "Admin user created: " + userDTO.getUsername();
    }

}

