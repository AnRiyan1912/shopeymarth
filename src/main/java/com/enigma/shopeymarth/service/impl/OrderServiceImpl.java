package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.customer.CustomerResponse;
import com.enigma.shopeymarth.dto.order.OrderRequest;
import com.enigma.shopeymarth.dto.order.OrderResponse;
import com.enigma.shopeymarth.dto.orderDetail.OrderDetailResponse;
import com.enigma.shopeymarth.dto.product.response.ProductProductPriceStoreResponse;
import com.enigma.shopeymarth.dto.store.StoreResponse;
import com.enigma.shopeymarth.entity.Customer;
import com.enigma.shopeymarth.entity.Order;
import com.enigma.shopeymarth.entity.OrderDetail;
import com.enigma.shopeymarth.entity.ProductPrice;
import com.enigma.shopeymarth.repository.OrderRepository;
import com.enigma.shopeymarth.service.CustomerService;
import com.enigma.shopeymarth.service.OrderService;
import com.enigma.shopeymarth.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductPriceService productPriceService;
    @Override
    public OrderResponse createNewOrder(OrderRequest orderRequest) {
        // TODO 1: Validate customer
        CustomerResponse customerResponse = customerService.getById(orderRequest.getCustomerId());

        //TODO 2: Convert orderDetailRequest to OrderDetail
        List<OrderDetail> orderDetails = orderRequest.getOrderDetails().stream().map(orderDetailRequest -> {
            //TODO 3: Validate Product Price
            ProductPrice productPrice = productPriceService.getById(orderDetailRequest.getProductPriceId());
            return OrderDetail.builder()
                    .productPrice(productPrice)
                    .quantity(orderDetailRequest.getQuantity())
                    .build();
        }).toList();

        //TODO 4: Create New Order
        Order order = Order.builder()
                .customer(Customer.builder()
                        .id(customerResponse.getId())
                        .build())
                .transDate(LocalDateTime.now())
                .orderDetails(orderDetails)
                .build();
        orderRepository.saveAndFlush(order);

        List<OrderDetailResponse> orderDetailResponses = order.getOrderDetails().stream().map(orderDetail -> {
            //TODO 5: Set order from orderDetail after creating new order
            orderDetail.setOrder(order);
            System.out.println(order);
            //TODO 6: Change the stock from the purchase quantity
            ProductPrice currentProductPrice = orderDetail.getProductPrice();
            currentProductPrice.setStock(currentProductPrice.getStock() - orderDetail.getQuantity());
            productPriceService.update(currentProductPrice);
            return OrderDetailResponse.builder()
                    .orderDetailId(orderDetail.getId())
                    .quantity(orderDetail.getQuantity())
                    //TODO 7: Convert product to productResponse(productPrice)
                    .product(ProductProductPriceStoreResponse.builder()
                            .id(currentProductPrice.getProduct().getId())
                            .productName(currentProductPrice.getProduct().getName())
                            .description(currentProductPrice.getProduct().getDescription())
                            .stock(currentProductPrice.getStock())
                            .price(currentProductPrice.getPrice())
                            //TODO 8: Convert store to storeResponse(productPrice
                            .store(StoreResponse.builder()
                                    .id(currentProductPrice.getStore().getId())
                                    .storeName(currentProductPrice.getStore().getName())
                                    .noSiup(currentProductPrice.getStore().getNoSiup())
                                    .address(currentProductPrice.getStore().getAddress())
                                    .phone(currentProductPrice.getStore().getMobilePhone())
                                    .build())
                            .build())
                    .build();
        }).toList();
        //TODO 9: Convert customer to customer response
//        CustomerResponse currentCustomer = customerService.getById(order.getCustomer().getId());

        //TODO 10: RETURN OrderResponnse

        return OrderResponse.builder()
                .orderId(order.getId())
                .transDate(order.getTransDate())
                .customer(customerResponse)
                .orderDetails(orderDetailResponses)
                .build();
    }

    @Override
    public OrderResponse getOrderById(String id) {
        return null;
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        return null;
    }
}
