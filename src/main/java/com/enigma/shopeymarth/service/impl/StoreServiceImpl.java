package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.StoreRequest;
import com.enigma.shopeymarth.dto.StoreResponse;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.StoreRepository;
import com.enigma.shopeymarth.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Store getById(String id) {
        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Store> getALl()  {
        return storeRepository.findAll();
    }

    @Override
    public Store update(Store store) {
        Store currentStoreId = getById(store.getId());
        if (currentStoreId != null) {
            return storeRepository.save(store);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        Store currentStoreId = getById(id);
        currentStoreId.setIsActive(false);
        if (currentStoreId.getId() != null) {
            storeRepository.save(currentStoreId);
        }
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
