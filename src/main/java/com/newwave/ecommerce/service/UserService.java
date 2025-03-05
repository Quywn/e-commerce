package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.UserDTO;

public interface UserService {
    String register(UserDTO user);
    UserDTO getUser(String username);
    UserDTO updateUser(UserDTO userDTO);
    UserDTO deleteUser(String username, String password);

}
