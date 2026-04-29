package com.banking.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponse createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        return CustomerMapper.toResponse(savedCustomer);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }
}
