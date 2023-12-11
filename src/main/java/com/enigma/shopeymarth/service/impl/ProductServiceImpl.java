package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.product.ProductProductPriceStoreResponse;
import com.enigma.shopeymarth.dto.product.ProductAndProductPriceRequest;
import com.enigma.shopeymarth.dto.product.ProductAndProductPriceResponse;
import com.enigma.shopeymarth.dto.store.StoreResponse;
import com.enigma.shopeymarth.entity.Product;
import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.ProductRepository;
import com.enigma.shopeymarth.service.ProductPriceService;
import com.enigma.shopeymarth.service.ProductService;
import com.enigma.shopeymarth.service.StoreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;
    private final StoreService storeService;


    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(String id) {


        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll() {

        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        Product currentProduct = getById(product.getId());
        if (currentProduct != null){
            productRepository.save(product);
            return product;
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
    public List<ProductProductPriceStoreResponse> getAllProductWithProductPriceAndStore() {
        List<Product> productList = productRepository.findAll();
        return   productList.stream().map(product -> ProductProductPriceStoreResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .productPrice(product.getProductPrice())
                .build()).collect(Collectors.toList());
    };

    @Transactional(rollbackOn = Exception.class)
    @Override
    public ProductAndProductPriceResponse createProductAndProductPrice(ProductAndProductPriceRequest productAndProductPriceRequest) {
        StoreResponse storeResponse = storeService.getById(productAndProductPriceRequest.getStoreId().getId());

        Product product = Product.builder()
                .name(productAndProductPriceRequest.getProductName())
                .description(productAndProductPriceRequest.getDescription())
                .build();
        productRepository.saveAndFlush(product);

        ProductPrice productPrice = ProductPrice.builder()
                .price(productAndProductPriceRequest.getPrice())
                .stock(productAndProductPriceRequest.getStock())
                .isActive(true)
                .store(Store.builder()
                        .id(storeResponse.getId())
                        .build())
                .product(product)
                .build();

        productPriceService.create(productPrice);

        return ProductAndProductPriceResponse.builder()
                .id(product.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .store(StoreResponse.builder()
                        .id(storeResponse.getId())
                        .storeName(storeResponse.getStoreName())
                        .address(storeResponse.getAddress())
                        .noSiup(storeResponse.getNoSiup())
                        .phone(storeResponse.getPhone())
                        .isActive(storeResponse.getIsActive())
                        .build())
                .build();
    }
}
