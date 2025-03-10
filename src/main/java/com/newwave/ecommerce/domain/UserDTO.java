package com.newwave.ecommerce.domain;

import com.newwave.ecommerce.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    private String username;
    @NotNull
    private String email;
    private String password;
    private List<Role> roles;
}
