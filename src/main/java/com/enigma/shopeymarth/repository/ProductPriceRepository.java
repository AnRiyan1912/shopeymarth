package com.enigma.shopeymarth.repository;

import com.enigma.shopeymarth.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {
    Optional<ProductPrice> findByProductIdAndIsActive(String productId, Boolean active);
}
