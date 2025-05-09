package com.newwave.ecommerce.service;

import com.newwave.ecommerce.dto.UserDTO;

import java.util.List;

public interface UserService {
    String register(UserDTO user);
    UserDTO getUser(String username);
    String updateUser(UserDTO userDTO);
    String deleteUser(String username);
    String createAdmin(UserDTO userDTO);
    List<UserDTO> getAllUsers();

}
