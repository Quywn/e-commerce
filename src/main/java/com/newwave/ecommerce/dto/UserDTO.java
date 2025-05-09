package com.newwave.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    private String username;
    @NotNull
    private String email;
    private String phone;
    private String address;
    private String oldPassword;
    private String newPassword;
    private List<RoleDTO> roles;
}
