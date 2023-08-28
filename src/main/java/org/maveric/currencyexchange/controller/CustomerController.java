package org.maveric.currencyexchange.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.maveric.currencyexchange.payload.request.CustomerRequest;
import org.maveric.currencyexchange.payload.response.CustomerResponse;
import org.maveric.currencyexchange.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
@SecurityRequirement(name = "bearerAuth")

public class CustomerController {

    private ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequest));
    }

    @PutMapping(value = "/update/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable long customerId,
            @Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(customerId, customerRequest));

    }

    @GetMapping(value = "/fetchAll")
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomers());
    }

    @GetMapping(value = "/fetch/{customerId}")
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomer(customerId));
    }

    @DeleteMapping(value = "/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long customerId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customerService.deleteCustomer(customerId));
    }
}