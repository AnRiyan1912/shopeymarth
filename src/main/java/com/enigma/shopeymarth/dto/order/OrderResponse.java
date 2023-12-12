package com.enigma.shopeymarth.dto.order;

import com.enigma.shopeymarth.dto.customer.CustomerResponse;
import com.enigma.shopeymarth.dto.orderDetail.OrderDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
    private String orderId;
    private LocalDateTime transDate;
    private CustomerResponse customer;
    private List<OrderDetailResponse> orderDetails;
}
