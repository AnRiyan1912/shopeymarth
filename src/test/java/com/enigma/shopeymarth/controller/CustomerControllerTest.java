package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.dto.customer.CustomerRequest;
import com.enigma.shopeymarth.dto.customer.CustomerResponse;
import com.enigma.shopeymarth.entity.Customer;
import com.enigma.shopeymarth.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerControllerTest {
    @InjectMocks
    private  CustomerController customerController;
    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();
        CustomerResponse customerResponse = new CustomerResponse();

        when(customerService.create(customerRequest)).thenReturn(customerResponse);

        CustomerResponse actualResponse = customerController.createCustomer(customerRequest);

        assertEquals(customerResponse, actualResponse);
    }

    @Test
    void getById() {
        String customerId = "12";
        CustomerResponse expectedResponse = new CustomerResponse();

        when(customerService.getById(customerId)).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerController.getById(customerId);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getAllCustomer() {
        List<CustomerResponse> expectedResponse = new ArrayList<>();

        when(customerService.getAll()).thenReturn(expectedResponse);

        List<CustomerResponse> actualResponse = customerController.getAllCustomer();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();
        CustomerResponse expectedResponse = new CustomerResponse();

        when(customerService.update(customerRequest)).thenReturn(expectedResponse);

        CustomerResponse actualResponse = customerController.updateCustomer(customerRequest);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void deleteCustomer() {
        String customerId = "1";
        customerController.deleteCustomer(customerId);
        verify(customerService).delete(customerId);
    }
}