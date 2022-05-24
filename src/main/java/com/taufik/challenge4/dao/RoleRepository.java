package com.taufik.challenge4.dao;

import com.taufik.challenge4.model.ERole;
import com.taufik.challenge4.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
