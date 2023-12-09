package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.CustomerRequest;
import com.enigma.shopeymarth.dto.CustomerResponse;
import com.enigma.shopeymarth.entity.Customer;
import com.enigma.shopeymarth.entity.Store;
import com.enigma.shopeymarth.repository.CustomerRepository;
import com.enigma.shopeymarth.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .mobilePhone(customerRequest.getMobilePhone())
                .email(customerRequest.getEmail())
                .build();
        customerRepository.save(customer);
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .phone(customer.getMobilePhone())
                .email(customer.getEmail())
                .build();
    }
    @Override
    public CustomerResponse getById(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            CustomerRequest customerRequest = CustomerRequest.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .address(customer.getAddress())
                    .mobilePhone(customer.getMobilePhone())
                    .email(customer.getEmail())
                    .build();
            return CustomerResponse.builder()
                    .id(customerRequest.getId())
                    .name(customerRequest.getName())
                    .address(customerRequest.getAddress())
                    .phone(customerRequest.getMobilePhone())
                    .email(customerRequest.getEmail())
                    .build();
        }
        return null;
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerRequest> customerRequests = new ArrayList<>();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        customers.stream().forEach(customer -> customerRequests.add(CustomerRequest.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .mobilePhone(customer.getMobilePhone())
                .email(customer.getEmail())
                .build()));
        customerRequests.stream().forEach(customerRequest -> customerResponses.add(CustomerResponse.builder()
                .id(customerRequest.getId())
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .phone(customerRequest.getMobilePhone())
                .email(customerRequest.getEmail())
                .build()));
        return customerResponses;
    }

    @Override
    public CustomerResponse update(CustomerResponse customerResponse) {
        CustomerResponse CurrentCustomerResponse = getById(customerResponse.getId());
        if (CurrentCustomerResponse != null) {
            Customer customer = Customer.builder()
                    .id(customerResponse.getId())
                    .name(customerResponse.getName())
                    .address(customerResponse.getAddress())
                    .mobilePhone(customerResponse.getPhone())
                    .email(customerResponse.getEmail())
                    .build();
            customerRepository.save(customer);
            return CustomerResponse.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .address(customer.getAddress())
                    .phone(customer.getMobilePhone())
                    .email(customer.getEmail())
                    .build();
        }
        return null;
    }

    @Override
    public void delete(String id) {
        CustomerResponse customerResponse = getById(id);
        if (customerResponse != null) {
            Customer customer = Customer.builder()
                    .id(customerResponse.getId())
                    .name(customerResponse.getName())
                    .address(customerResponse.getAddress())
                    .mobilePhone(customerResponse.getPhone())
                    .email(customerResponse.getEmail())
                    .build();
            customerRepository.delete(customer);
        }
    }
}
