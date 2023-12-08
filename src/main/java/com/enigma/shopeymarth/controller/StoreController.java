package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.constant.AppPath;
import com.enigma.shopeymarth.dto.StoreRequest;
import com.enigma.shopeymarth.dto.StoreResponse;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.VALUE_STORE)
public class StoreController {
    private final StoreService storeService;

    public Store createStore(@RequestBody Store store) {
        return storeService.create(store);
    }

    @GetMapping(value = "/{id}")
    public Store getByIdStore(@PathVariable String id) {
        return storeService.getById(id);
    }

    @GetMapping
    public List<Store> getAllStore() {
        return storeService.getALl();
    }
    @PutMapping
    public Store updateStore(@RequestBody Store store) {
        return storeService.update(store);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStore(@PathVariable String id) {
        storeService.delete(id);
        System.out.printf("success delete");
    }

    @PostMapping(value = "/v1")
    public StoreResponse create(@RequestBody StoreRequest storeRequest) {
        return storeService.create(storeRequest);
    }
}
