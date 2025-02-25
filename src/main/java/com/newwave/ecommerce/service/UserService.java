package com.newwave.ecommerce.service;

import com.newwave.ecommerce.domain.UserDTO;

public interface UserService {
    String signUp(UserDTO user);
    UserDTO updateEmailByUsername(UserDTO email);
    UserDTO updatePasswordByUsername(UserDTO password);
}
