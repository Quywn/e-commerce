package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.UserDTO;

public interface UserService {
    String register(UserDTO user);
    UserDTO getUser(String username);
    String updateUser(UserDTO userDTO);
    String deleteUser(String username, String password);

}
