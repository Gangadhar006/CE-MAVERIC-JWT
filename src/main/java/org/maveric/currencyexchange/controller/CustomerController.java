package org.maveric.currencyexchange.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.maveric.currencyexchange.payload.request.CustomerRequest;
import org.maveric.currencyexchange.payload.response.CustomerResponse;
import org.maveric.currencyexchange.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.maveric.currencyexchange.constants.ApiEndpointConstants.*;
import static org.maveric.currencyexchange.constants.SecurityConstants.*;

import java.util.List;

@RestController
@RequestMapping(value = CUSTOMER_URL_PREFIX)
@SecurityRequirement(name = SECURITY_SCHEME_NAME)
public class CustomerController {

    private ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = CUSTOMER_CREATE_URL)
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequest));
    }

    @PutMapping(value = CUSTOMER_UPDATE_URL)
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable long customerId,
            @Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(customerId, customerRequest));

    }

    @GetMapping(value = CUSTOMER_FETCHALL_URL)
    public ResponseEntity<List<CustomerResponse>> findAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomers());
    }

    @GetMapping(value = CUSTOMER_FETCHBY_CUSTOMERID_URL)
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomer(customerId));
    }

    @DeleteMapping(value = CUSTOMER_DELETE_URL)
    public ResponseEntity<String> deleteCustomer(@PathVariable long customerId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customerService.deleteCustomer(customerId));
    }
}