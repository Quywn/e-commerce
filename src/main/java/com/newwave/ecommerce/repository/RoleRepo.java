package com.newwave.ecommerce.repository;

import com.newwave.ecommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByRoleName(String roleName);
}
