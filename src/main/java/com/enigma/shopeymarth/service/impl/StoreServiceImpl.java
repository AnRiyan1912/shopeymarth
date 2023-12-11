package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.store.StoreRequest;
import com.enigma.shopeymarth.dto.store.StoreResponse;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.StoreRepository;
import com.enigma.shopeymarth.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public Store create(Store store) {
        Store currentStore = store;
        currentStore.setIsActive(true);
        return storeRepository.save(currentStore);
    }

    @Override
    public StoreResponse getById(String id) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store != null) {
            return StoreResponse.builder()
                    .id(store.getId())
                    .noSiup(store.getNoSiup())
                    .storeName(store.getName())
                    .address(store.getAddress())
                    .phone(store.getMobilePhone())
                    .isActive(store.getIsActive())
                    .build();
        }
        return null;
    }

    @Override
    public List<Store> getALl()  {
        return storeRepository.findAll();
    }

    @Override
    public Store update(Store store) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public StoreResponse create(StoreRequest storeRequest) {
        Store store = Store.builder()
                .name(storeRequest.getName())
                .noSiup(storeRequest.getNoSiup())
                .address(storeRequest.getAddress())
                .mobilePhone(storeRequest.getMobilePhone())
                .isActive(true)
                .build();
        storeRepository.save(store);
        return StoreResponse.builder()
                .storeName(store.getName())
                .noSiup(store.getNoSiup())
                .phone(store.getMobilePhone())
                .address(store.getAddress())
                .build();
    }
}
