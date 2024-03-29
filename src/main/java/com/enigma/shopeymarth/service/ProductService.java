package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.product.response.ProductProductPriceStoreResponse;
import com.enigma.shopeymarth.dto.product.request.ProductAndProductPriceRequest;
import com.enigma.shopeymarth.dto.product.response.ProductAndProductPriceResponse;
import com.enigma.shopeymarth.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(String id);
    List<Product> getAll();
    Product update(Product product);
    void deleteById(String id);
    List<ProductProductPriceStoreResponse> getAllProductWithProductPriceAndStore();
    ProductAndProductPriceResponse createProductAndProductPrice(ProductAndProductPriceRequest productAndProductPriceRequest);
    Page<ProductProductPriceStoreResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size);
}
