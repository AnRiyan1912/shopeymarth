package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.repository.ProductPriceRepository;
import com.enigma.shopeymarth.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
