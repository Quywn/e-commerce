package com.newwave.ecommerce.common_model;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer UserId;
    private String userName;
    private String password;
    private List<String> permissions;
    private String email;
    private String phone;
    private String address;
    private String state;
}
