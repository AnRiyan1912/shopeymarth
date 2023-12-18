package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.product.request.ProductAndProductPriceRequest;
import com.enigma.shopeymarth.dto.product.response.ProductAndProductPriceResponse;
import com.enigma.shopeymarth.dto.store.StoreRequest;
import com.enigma.shopeymarth.dto.store.StoreResponse;
import com.enigma.shopeymarth.entity.Product;
import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.repository.ProductRepository;
import com.enigma.shopeymarth.service.ProductPriceService;
import com.enigma.shopeymarth.service.ProductService;
import com.enigma.shopeymarth.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductPriceService productPriceService = mock(ProductPriceService.class);
    private final StoreService storeService = mock(StoreService.class);
    private final ProductService productService = new ProductServiceImpl(
            productRepository, productPriceService , storeService
    );
    @BeforeEach
    public void setUp() {
        //reset mock behavior
        reset(productRepository, productPriceService, storeService);
    }
    @Test
    void createProductAndProductPrice() {
        //store request
        StoreResponse dummyStore = new StoreResponse();
        dummyStore.setId("store1");
        dummyStore.setStoreName("toko ayam");
        dummyStore.setNoSiup("12345");

        //behavior store response
        when(storeService.getById(anyString())).thenReturn(dummyStore);

        //save and flush product
        Product productSave = new Product();
        productSave.setName("wafer coklat");
        productSave.setDescription("coklat panjang");
        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(productSave);

        //data dummy request
        ProductAndProductPriceRequest dummyRequest = mock(ProductAndProductPriceRequest.class);
        when(dummyRequest.getStoreId()).thenReturn(StoreRequest.builder()
                        .id("store1")
                .build());
        when(dummyRequest.getProductName()).thenReturn(productSave.getName());
        when(dummyRequest.getDescription()).thenReturn(productSave.getDescription());
        when(dummyRequest.getPrice()).thenReturn(120000l);
        when(dummyRequest.getStock()).thenReturn(20);

        //call method create
        ProductAndProductPriceResponse productAndProductPriceResponse = productService.createProductAndProductPrice(dummyRequest);

        //validate response
        assertNotNull(productAndProductPriceResponse);

        //validate that the product price was set correct
        assertEquals(productSave.getName(), productAndProductPriceResponse.getProductName());

        //validate the store to check wa correct
        assertEquals(dummyStore.getId(), productAndProductPriceResponse.getStore().getId());

        verify(storeService).getById(anyString());
        verify(productRepository).saveAndFlush(any(Product.class));
        verify(productPriceService).create(any(ProductPrice.class));
    }
}