package com.enigma.shopeymarth.repository;

import com.enigma.shopeymarth.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
//    @Query(value="select * from store s where s.isActive= true", nativeQuery=true)
//    List<Store> getAllStoreActive();
}
