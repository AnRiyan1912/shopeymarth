package com.enigma.shopeymarth.repository;

import com.enigma.shopeymarth.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OderDetailRepository extends JpaRepository<OrderDetail, String> {

}
