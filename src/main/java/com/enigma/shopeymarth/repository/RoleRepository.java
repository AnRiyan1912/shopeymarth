package com.enigma.shopeymarth.repository;

import com.enigma.shopeymarth.constant.ERole;
import com.enigma.shopeymarth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
