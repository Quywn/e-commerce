package com.newwave.ecommerce.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private List<String> permissions;
}
