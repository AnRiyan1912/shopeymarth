package com.enigma.shopeymarth.dto.product.response;

import com.enigma.shopeymarth.dto.store.StoreResponse;
import com.enigma.shopeymarth.entity.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductProductPriceStoreResponse {
    private String id;
    private String productName;
    private String description;
    private Long price;
    private Integer stock;
    private StoreResponse store;
}
