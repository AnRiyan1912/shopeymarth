package com.enigma.shopeymarth.service.impl;

import com.enigma.shopeymarth.dto.customer.CustomerRequest;
import com.enigma.shopeymarth.dto.customer.CustomerResponse;
import com.enigma.shopeymarth.entity.Customer;
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
        return ResponseCustomer(customer);
    }

    @Override
    public CustomerResponse createNewCustomer(Customer request) {
        Customer customer = customerRepository.saveAndFlush(request);
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .phone(customer.getMobilePhone())
                .build();
    }

    @Override
    public CustomerResponse getById(String id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return ResponseCustomer(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        customers.stream().forEach(customer -> customerResponses.add(ResponseCustomer(customer)));
        return customerResponses;
    }

    @Override
    public CustomerResponse update(CustomerRequest customerRequest) {
        CustomerResponse CurrentCustomerResponse = getById(customerRequest.getId());
        if (CurrentCustomerResponse != null) {
            Customer customer = Customer.builder()
                    .id(customerRequest.getId())
                    .name(customerRequest.getName())
                    .address(customerRequest.getAddress())
                    .mobilePhone(customerRequest.getMobilePhone())
                    .email(customerRequest.getEmail())
                    .build();
            customerRepository.save(customer);
            return ResponseCustomer(customer);
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
    private static CustomerResponse ResponseCustomer(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .phone(customer.getMobilePhone())
                .email(customer.getEmail())
                .build();
    }

}
