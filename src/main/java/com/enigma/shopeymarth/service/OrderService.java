package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.order.OrderRequest;
import com.enigma.shopeymarth.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createNewOrder(OrderRequest orderRequest);
    OrderResponse getOrderById(String id);
    List<OrderResponse> getAllOrder();
}
