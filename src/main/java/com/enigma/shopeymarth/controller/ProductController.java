package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.constant.AppPath;
import com.enigma.shopeymarth.dto.product.request.ProductAndProductPriceRequest;
import com.enigma.shopeymarth.dto.product.response.ProductAndProductPriceResponse;
import com.enigma.shopeymarth.dto.product.response.ProductProductPriceStoreResponse;
import com.enigma.shopeymarth.dto.response.CommonResponse;
import com.enigma.shopeymarth.dto.response.PagingResponse;
import com.enigma.shopeymarth.entity.Product;
import com.enigma.shopeymarth.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.VALUE_PRODUCT)
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
    public ResponseEntity<CommonResponse<Product>> getProductById (@PathVariable String id) {
        Product product =  productService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<Product>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Succesfully get produtc by id")
                        .data(product)
                        .build());
    }

    @GetMapping("/v1")
    public ResponseEntity<?> getAllProductPage(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "maxPrice", required = false) Long maxPrice,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size
    ) {
        Page<ProductProductPriceStoreResponse> productProductPriceStoreResponses = productService.getAllByNameOrPrice(name, maxPrice, page, size);
        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(page)
                .totalPage(productProductPriceStoreResponses.getTotalPages())
                .size(size)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Succesfully get product")
                        .data(productProductPriceStoreResponses.getContent())
                        .pagingResponse(pagingResponse)
                        .build());
    }
}
