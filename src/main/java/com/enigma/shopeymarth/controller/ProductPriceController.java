package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.constant.AppPath;
import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.VALUE_PRODUCT_PRICE)
public class ProductPriceController {
    private final ProductPriceService productPriceService;

    @GetMapping("/{id}")
    public ProductPrice getProductPriceById(@PathVariable String id) {
        return productPriceService.getById(id);
    }
}
