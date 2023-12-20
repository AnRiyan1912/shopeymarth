package com.enigma.shopeymarth.repository;

import com.enigma.shopeymarth.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<Files, Long> {
    Files findByName(String name);
}
