package com.newwave.ecommerce.common_model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer categoryId;
    private String categoryName;
    private String categoryDesc;
    private List<Product> productList;
}
