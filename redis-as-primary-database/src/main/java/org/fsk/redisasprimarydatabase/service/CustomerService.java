package org.fsk.redisasprimarydatabase.service;


import org.fsk.redisasprimarydatabase.domain.Customer;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.CreateCustomerRequest;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.UpdateCustomerRequest;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.DeleteCustomerRequest;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.GetCustomerRequest;
import org.fsk.redisasprimarydatabase.dto.response.customerresponse.CustomerResponse;
import org.fsk.redisasprimarydatabase.dto.response.customerresponse.DeleteCustomerResponse;
import org.fsk.redisasprimarydatabase.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {
    
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private String generateCustomerId() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Customer customer = Customer.builder()
                .customerId(generateCustomerId())
                .identityNumber(request.identityNumber())
                .customerName(request.customerName())
                .customerSurname(request.customerSurname())
                .customerEmail(request.customerEmail())
                .customerPhone(request.customerPhone())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();

        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer created successfully: {}", savedCustomer);
        
        return new CustomerResponse(
                savedCustomer.getCustomerId(),
                savedCustomer.getIdentityNumber(),
                savedCustomer.getCustomerName(),
                savedCustomer.getCustomerSurname(),
                savedCustomer.getCustomerEmail(),
                savedCustomer.getCustomerPhone(),
                savedCustomer.getCreatedAt(),
                savedCustomer.getIsActive()
        );
    }

    @Transactional
    public CustomerResponse updateCustomer(UpdateCustomerRequest request) {
        Customer existingCustomer = customerRepository.findById(request.customerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        existingCustomer.setCustomerName(request.customerName());
        existingCustomer.setCustomerSurname(request.customerSurname());
        existingCustomer.setCustomerEmail(request.customerEmail());
        existingCustomer.setCustomerPhone(request.customerPhone());
        
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        log.info("Customer updated successfully: {}", updatedCustomer);
        
        return new CustomerResponse(
                updatedCustomer.getCustomerId(),
                updatedCustomer.getIdentityNumber(),
                updatedCustomer.getCustomerName(),
                updatedCustomer.getCustomerSurname(),
                updatedCustomer.getCustomerEmail(),
                updatedCustomer.getCustomerPhone(),
                updatedCustomer.getCreatedAt(),
                updatedCustomer.getIsActive()
        );
    }

    @Transactional
    public DeleteCustomerResponse deleteCustomer(DeleteCustomerRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        customer.setIsActive(false);
        Customer deletedCustomer = customerRepository.save(customer);
        
        log.info("Customer deleted successfully. CustomerId: {}", request.customerId());
        return new DeleteCustomerResponse(deletedCustomer.getCustomerId(), "Customer deleted successfully");
    }

    public CustomerResponse getCustomer(GetCustomerRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        log.info("Customer retrieved successfully: {}", customer);
        
        return new CustomerResponse(
                customer.getCustomerId(),
                customer.getIdentityNumber(),
                customer.getCustomerName(),
                customer.getCustomerSurname(),
                customer.getCustomerEmail(),
                customer.getCustomerPhone(),
                customer.getCreatedAt(),
                customer.getIsActive()
        );
    }

    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId)
            .orElseThrow(() -> {
                log.error("Customer not found. CustomerId: {}", customerId);
                return new RuntimeException("Customer not found");
            });
    }
}
