package com.newwave.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    private int roleId;
    private String roleName;
}
