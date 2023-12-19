package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.dto.product.request.ProductAndProductPriceRequest;
import com.enigma.shopeymarth.dto.product.response.ProductAndProductPriceResponse;
import com.enigma.shopeymarth.dto.product.response.ProductProductPriceStoreResponse;
import com.enigma.shopeymarth.dto.response.CommonResponse;
import com.enigma.shopeymarth.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void createProduct() {
        //data dummy
        ProductAndProductPriceRequest productRequest = new ProductAndProductPriceRequest();
        productRequest.setProductName("Wafer coklat");
        productRequest.setPrice(100L);

        //data response
        ProductAndProductPriceResponse productResponse = new ProductAndProductPriceResponse();
        productResponse.setId("12");
        productResponse.setProductName("Wafer coklat");
        productResponse.setPrice(100L);

        when(productService.createProductAndProductPrice(productRequest)).thenReturn(productResponse);

        ResponseEntity<?> responseEntity = productController.createProduct(productRequest);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        CommonResponse<ProductAndProductPriceResponse> commonResponse = (CommonResponse<ProductAndProductPriceResponse>) responseEntity.getBody();

        assertEquals(HttpStatus.CREATED.value(), commonResponse.getStatusCode());

        assertEquals("Successfully create product", commonResponse.getMessage());

        assertEquals(productRequest.getProductName(), commonResponse.getData().getProductName());

        assertEquals(productRequest.getPrice(), commonResponse.getData().getPrice());
    }

    @Test
    void getAllProduct() {
        List<ProductProductPriceStoreResponse> productResponse = new ArrayList<>();

//        when(productService.getAll()).thenReturn(productResponse);
    }

    @Test
    void getProductById() {
    }

    @Test
    void getAllProductPage() {
    }
};