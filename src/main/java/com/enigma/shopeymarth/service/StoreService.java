package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.store.StoreRequest;
import com.enigma.shopeymarth.dto.store.StoreResponse;
import com.enigma.shopeymarth.entity.Store;

import java.util.List;

public interface StoreService {
    Store create(Store store);
    StoreResponse getById(String id);
    List<Store> getALl();
    Store update(Store store);
    void delete(String id);
    StoreResponse create(StoreRequest storeRequest);
}
