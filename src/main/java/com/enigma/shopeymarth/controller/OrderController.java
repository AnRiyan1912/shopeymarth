package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.constant.AppPath;
import com.enigma.shopeymarth.dto.order.OrderRequest;
import com.enigma.shopeymarth.dto.order.OrderResponse;
import com.enigma.shopeymarth.dto.response.CommonResponse;
import com.enigma.shopeymarth.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.VALUE_ORDER)
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createNewOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Order Successfully")
                        .data(orderResponse)
                        .build());
    }
}
