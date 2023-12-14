package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.customer.CustomerRequest;
import com.enigma.shopeymarth.dto.customer.CustomerResponse;
import com.enigma.shopeymarth.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest customerRequest);
    CustomerResponse createNewCustomer(Customer customer);
    CustomerResponse getById(String id);
    List<CustomerResponse> getAll();
    CustomerResponse update(CustomerRequest customerRequest);
    void delete(String id);

}
