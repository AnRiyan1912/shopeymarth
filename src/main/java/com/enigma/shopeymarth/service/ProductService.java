package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.product.ProductProductPriceStoreResponse;
import com.enigma.shopeymarth.dto.product.ProductAndProductPriceRequest;
import com.enigma.shopeymarth.dto.product.ProductAndProductPriceResponse;
import com.enigma.shopeymarth.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(String id);
    List<Product> getAll();
    Product update(Product product);
    void deleteById(String id);
    List<ProductProductPriceStoreResponse> getAllProductWithProductPriceAndStore();
    ProductAndProductPriceResponse createProductAndProductPrice(ProductAndProductPriceRequest productAndProductPriceRequest);
}
