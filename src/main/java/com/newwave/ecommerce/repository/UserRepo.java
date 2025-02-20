package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    public User findByUsername(String username);
}
