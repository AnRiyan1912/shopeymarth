package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.store.StoreRequest;
import com.enigma.shopeymarth.dto.store.StoreResponse;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.StoreRepository;
import com.enigma.shopeymarth.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class StoreServiceImplTest {
    private final StoreRepository storeRepository = mock(StoreRepository.class);
    private final StoreService storeService = new StoreServiceImpl(storeRepository);
    @Test
    void itsShouldReturnStoreWhenCreateNewStore() {
        StoreRequest storeRequestDummy = new StoreRequest();
        storeRequestDummy.setId("123");
        storeRequestDummy.setName("Toko serba serbi");
        storeRequestDummy.setAddress("Jalan bunga melati");

        //Data db, request, response
        StoreResponse storeResponseDummy = storeService.create(storeRequestDummy);

        verify(storeRepository).save(any(Store.class));
//        assertEquals(storeRequestDummy.getName(), storeResponseDummy.getStoreName());
        assertEquals(storeRequestDummy.getAddress(), storeResponseDummy.getAddress());
    }

    @Test
    void itsShouldReturnAllStoreWhenCallGetAll() {
        List<Store> storeListDummy = new ArrayList<>();
        storeListDummy.add(new Store("1", "123213", "berkah amal", "jalan bunga melati", "018312sds3", true));
        storeListDummy.add(new Store("2", "12321222", "berkah amal sela", "jalan bunga melati", "232", true));
        storeListDummy.add(new Store("3", "12321322", "berkah amal dd", "jalan bunga melati", "01831222223", true));

        when(storeRepository.findAll()).thenReturn(storeListDummy);
        List<StoreResponse> storeResponseListDummy = storeService.getallStore();

        for (int i = 0; i < storeListDummy.size(); i++) {
            assertEquals(storeListDummy.get(i).getAddress(), storeResponseListDummy.get(i).getAddress());
        }

    }

    @Test
    void isShouldGetDataStoreOneWhenGetById() {
        String storeId = "1";
        Store storeDummy = new Store("1", "2342342", "Jaya Puh", "Ragunan sini", "0812312",true);

        when(storeRepository.findById(storeId)).thenReturn(Optional.of(storeDummy));

        StoreResponse storeResponse = storeService.getById(storeId);

        verify(storeRepository).findById(storeId);
//        assertEquals(storeId, storeResponse.getId());
        assertNotNull(storeResponse);
//        assertEquals("1", storeResponse.getId());

;
    }

    @Test
    void isShouldGetNewDataStoreWhenUpdateStore() {

        StoreRequest storeRequestDummy = mock(StoreRequest.class);
        when(storeRequestDummy.getId()).thenReturn("1");
        when(storeRequestDummy.getName()).thenReturn("toko atom");

        Store storeDummy = new Store("1","2132323", "Toko serba serbi", "Jalan bunga melati", "0812321",true);

        when(storeRepository.findById("1")).thenReturn(Optional.of(storeDummy));

        Store updateStore = new Store();
        updateStore.setId("1");
        updateStore.setName("toko atom");

        when(storeRepository.save(any(Store.class))).thenReturn(updateStore);

        StoreResponse storeResponseDummy = storeService.update(storeRequestDummy);


        assertNotNull(storeResponseDummy);
        assertEquals(storeRequestDummy.getName(), storeResponseDummy.getStoreName());


    }



}