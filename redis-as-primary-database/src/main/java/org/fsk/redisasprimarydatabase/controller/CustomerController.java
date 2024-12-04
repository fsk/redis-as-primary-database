package org.fsk.redisasprimarydatabase.controller;

import lombok.RequiredArgsConstructor;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.CreateCustomerRequest;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.DeleteCustomerRequest;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.GetCustomerRequest;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.UpdateCustomerRequest;
import org.fsk.redisasprimarydatabase.dto.response.customerresponse.CustomerResponse;
import org.fsk.redisasprimarydatabase.dto.response.customerresponse.DeleteCustomerResponse;
import org.fsk.redisasprimarydatabase.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody UpdateCustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }

    @DeleteMapping
    public ResponseEntity<DeleteCustomerResponse> deleteCustomer(@RequestBody DeleteCustomerRequest request) {
        return ResponseEntity.ok(customerService.deleteCustomer(request));
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> getCustomer(@RequestBody GetCustomerRequest request) {
        return ResponseEntity.ok(customerService.getCustomer(request));
    }
}