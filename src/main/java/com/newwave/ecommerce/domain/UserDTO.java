package com.newwave.ecommerce.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private List<String> permissions;

}
