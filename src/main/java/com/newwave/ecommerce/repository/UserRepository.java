package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.User;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {
    public User findByUsername(String username);
}
