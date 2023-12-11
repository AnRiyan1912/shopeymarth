package com.enigma.shopeymarth.dto.producprice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductPriceRequest {
    private String id;
    private Integer stock;
    private Boolean isActive;
    private Long price;
}
