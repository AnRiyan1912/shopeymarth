package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.customer.CustomerResponse;
import com.enigma.shopeymarth.entity.Customer;
import com.enigma.shopeymarth.repository.CustomerRepository;
import com.enigma.shopeymarth.repository.ProductRepository;
import com.enigma.shopeymarth.service.CustomerService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceImplTest {
    private final CustomerRepository customerRepository = mock(CustomerRepository.class);
    private final CustomerService customerService = mock(CustomerService.class);
    @BeforeEach
    public void setUp() {
        reset(customerRepository, customerService);
    }
    @Test
    void getById() {
        Customer customer = new Customer("12", "andre", "jalan", "!3123123", "ADdad@mail.com");

        String storeId = "12";

        when(customerRepository.findById(storeId)).thenReturn(Optional.of(customer));

        CustomerResponse customerResponse = customerService.getById(storeId);

        verify(customerRepository).findById(storeId);

        assertNotNull(customerResponse);


    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }
}