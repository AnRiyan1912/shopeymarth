package com.enigma.shopeymarth.dto.product.request;

import com.enigma.shopeymarth.dto.product.response.ProductAndProductPriceResponse;
import com.enigma.shopeymarth.dto.store.StoreRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductAndProductPriceRequest {
    private String productId;
    @NotBlank(message = "product name id required")
    private String productName;
    @NotBlank(message = "product description is required")
    private String description;
    @NotBlank(message = "product price is required")
    @Min(value = 0, message = "Price must be greater than equal 0")
    private Long price;
    @NotBlank(message = "product stock is required")
    @Min(value = 0, message = "stck must be greate than equal 0")
    private Integer stock;
    @NotBlank(message = "storeId is required")
    private StoreRequest storeId;
}
