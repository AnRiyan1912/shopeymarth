package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.entity.Product;
import com.enigma.shopeymarth.entity.ProductPrice;

import java.util.List;

public interface ProductPriceService {
    ProductPrice create(ProductPrice productPrice);
    List<ProductPrice> getAll();
}
