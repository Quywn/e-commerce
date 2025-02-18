package com.newwave.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Entity
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
//    @ElementCollection
//    @CollectionTable(name = "permissionList", joinColumns = @JoinColumn(name = "userId"))
//    private List<Permission> permissionList;
    private String email;
    private String phone;
    private String address;
    private String state;
}
