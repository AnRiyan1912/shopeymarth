package com.enigma.shopeymarth.controller;

import com.enigma.shopeymarth.constant.AppPath;
import com.enigma.shopeymarth.dto.customer.CustomerRequest;
import com.enigma.shopeymarth.dto.customer.CustomerResponse;
import com.enigma.shopeymarth.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.VALUE_CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.create(customerRequest);
    }
    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable String id){
        return customerService.getById(id);
    }
    @GetMapping
    public List<CustomerResponse> getAllCustomer() {
        return customerService.getAll();
    }
    @PutMapping
    public CustomerResponse updateCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.update(customerRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.delete(id);
    }
}
