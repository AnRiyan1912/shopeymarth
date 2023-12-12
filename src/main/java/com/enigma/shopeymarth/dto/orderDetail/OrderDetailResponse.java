package com.enigma.shopeymarth.dto.orderDetail;

import com.enigma.shopeymarth.dto.product.ProductProductPriceStoreResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderDetailResponse {
    private String orderDetailId;
    private ProductProductPriceStoreResponse product;
    private Integer quantity;
}
