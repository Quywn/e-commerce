package com.newwave.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String state;
}
