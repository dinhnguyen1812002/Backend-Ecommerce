package com.Project.Store.repository;

import com.Project.Store.entity.ERole;
import com.Project.Store.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
