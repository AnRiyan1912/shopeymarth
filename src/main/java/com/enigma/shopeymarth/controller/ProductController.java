package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.dto.product.ProductAndProductPriceRequest;
import com.enigma.shopeymarth.dto.product.ProductAndProductPriceResponse;
import com.enigma.shopeymarth.dto.product.ProductProductPriceStoreResponse;
import com.enigma.shopeymarth.dto.response.CommonResponse;
import com.enigma.shopeymarth.entity.Product;
import com.enigma.shopeymarth.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductAndProductPriceRequest productAndProductPriceRequest) {
        ProductAndProductPriceResponse productResponse = productService.createProductAndProductPrice(productAndProductPriceRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ProductAndProductPriceResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully create product")
                        .data(productResponse)
                        .build())
                ;
    }
    @GetMapping
    public List<ProductProductPriceStoreResponse> getAllProduct() {
        return productService.getAllProductWithProductPriceAndStore();
    }

    @GetMapping("/{id}")
    public Product getProductById (@PathVariable String id) {
        return productService.getById(id);
    }
}
