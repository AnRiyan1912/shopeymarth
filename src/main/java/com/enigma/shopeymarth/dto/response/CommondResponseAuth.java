package com.enigma.shopeymarth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommondResponseAuth<T> {
   private Integer statusCode;
   private String message;
   private T data;
}
