package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.product.response.ProductProductPriceStoreResponse;
import com.enigma.shopeymarth.dto.product.request.ProductAndProductPriceRequest;
import com.enigma.shopeymarth.dto.product.response.ProductAndProductPriceResponse;
import com.enigma.shopeymarth.dto.store.StoreResponse;
import com.enigma.shopeymarth.entity.Product;
import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.ProductRepository;
import com.enigma.shopeymarth.service.ProductPriceService;
import com.enigma.shopeymarth.service.ProductService;
import com.enigma.shopeymarth.service.StoreService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Page<ProductProductPriceStoreResponse> getAllByNameOrPrice(String name, Long maxPrice, Integer page, Integer size) {
        //Spesification untuk menentukan kriteria pencarian, disini criteria pencarian di tandai dengan root, root yang dimaksud adalah entiti product
        Specification<Product> specification = ((root, query, criteriaBuilder) -> {
            //Join digunakan untuk merelasikan antara product dan product price
            Join<Product, ProductPrice> productPrices = root.join("productPrice");
            //Predicate digunakan untuk menggunaka LIKE dimana nanti kita akan menggunakan kondisi pencarian parameter
            //Disini kita akan mencari nama product atau harga yang sama atau harga dibawahnya, makanya menggunakan lessThanOrEqualsTo
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(productPrices.get("price"), maxPrice));
            }
            //Kode return mengembalikan query dimana pada dasarnya kita membangun klausa where yang sudah ditentukkan dari predicate atau kriteria
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        });
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(specification, pageable);
        //Ini digunakan untuk menyimpan response product yang baru
        List<ProductProductPriceStoreResponse> productProductPriceStoreResponses = new ArrayList<>();
        for (Product product : products.getContent()) {
            //For disini digunakan untuk mengiterasi daftar product yang di simpan dalam object
            Optional<ProductPrice> productPrice = product.getProductPrice()// Optimal disini digunakan untuk mencari harga yang aktif
                    .stream().filter(ProductPrice::getIsActive).findFirst();

            if (productPrice.isEmpty()) continue;// Kondisi ini digunakan untuk memeriksa aoakah productpricenya kosong atau tidak. kalau tidak maka akan di skip

            Store store = productPrice.get().getStore();
            productProductPriceStoreResponses.add(ProductProductPriceStoreResponse.builder()
                            .id(product.getId())
                            .productName(product.getName())
                            .description(product.getDescription())
                            .price(productPrice.get().getPrice())
                            .stock(productPrice.get().getStock())
                            .store(StoreResponse.builder()
                                    .id(store.getId())
                                    .storeName(store.getName())
                                    .address(store.getAddress())
                                    .noSiup(store.getNoSiup())
                                    .phone(store.getMobilePhone())
                                    .build())
                    .build());
        }
        return new PageImpl<>(productProductPriceStoreResponses, pageable, products.getTotalElements());
    }
}
