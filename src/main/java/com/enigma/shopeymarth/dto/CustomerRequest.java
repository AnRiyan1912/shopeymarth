package com.enigma.shopeymarth.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerRequest {
    private String id;
    private String name;
    private String address;
    private String mobilePhone;
    private String email;
}
