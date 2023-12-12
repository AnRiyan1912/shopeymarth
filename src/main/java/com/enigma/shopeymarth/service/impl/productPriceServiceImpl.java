package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.repository.ProductPriceRepository;
import com.enigma.shopeymarth.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class productPriceServiceImpl implements ProductPriceService {
    private final ProductPriceRepository productPriceRepository;
    @Override
    public ProductPrice create(ProductPrice productPrice) {
      return productPriceRepository.save(productPrice);
    }

    @Override
    public List<ProductPrice> getAll() {
        return productPriceRepository.findAll();
    }

    @Override
    public ProductPrice update(ProductPrice productPrice) {
        ProductPrice currentProductPrice = getById(productPrice.getId());
        if (currentProductPrice != null) {
            return productPriceRepository.save(productPrice);
        }
        return null;

    }

    @Override
    public ProductPrice findProductPriceIsActive(String productId, Boolean active) {
        return productPriceRepository.findByProductIdAndIsActive(productId, active).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
    }

    @Override
    public ProductPrice getById(String id) {
        return productPriceRepository.findById(id).orElse(null);
    }
}
