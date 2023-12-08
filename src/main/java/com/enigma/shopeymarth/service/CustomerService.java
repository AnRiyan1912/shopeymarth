package com.enigma.shopeymarth.service;

import com.enigma.shopeymarth.dto.CustomerRequest;
import com.enigma.shopeymarth.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest customerRequest);
    CustomerResponse getById(String id);
    List<CustomerResponse> getAll();
    CustomerResponse update(CustomerResponse customerResponse);
    void delete(String id);

}
