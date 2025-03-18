package com.newwave.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_code")
    private String categoryCode;
    @Column(name = "category_name")
    private String categoryName;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;
}
