package com.enigma.shopeymarth.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class StoreRequest {
    private String id;
    private String noSiup;
    private String name;
    private String address;
    private String mobilePhone;
    private Boolean isActive;
}
