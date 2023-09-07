package org.maveric.currencyexchange.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.maveric.currencyexchange.payload.request.AccountRequest;
import org.maveric.currencyexchange.payload.response.AccountResponse;
import org.maveric.currencyexchange.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.maveric.currencyexchange.constants.ApiEndpointConstants.*;
import static org.maveric.currencyexchange.constants.SecurityConstants.*;

import java.util.List;

@RestController
@RequestMapping(value = ACCOUNT_URL_PREFIX)
@SecurityRequirement(name = SECURITY_SCHEME_NAME)
public class AccountController {
    private IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = ACCOUNT_CREATE_URL)
    public ResponseEntity<AccountResponse> createAccount(@PathVariable long customerId,
                                                         @Valid @RequestBody AccountRequest accountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccount(customerId, accountRequest));
    }

    @PutMapping(value = ACCOUNT_UPDATE_URL)
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable long customerId,
                                                         @PathVariable String accountNumber,
                                                         @Valid @RequestBody AccountRequest accountRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.updateAccount(customerId, accountNumber, accountRequest));
    }

    @GetMapping(value = ACCOUNT_FETCHBY_CUSTOMERID_URL)
    public ResponseEntity<List<AccountResponse>> findAllAccounts(@PathVariable long customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.findAllAccounts(customerId));
    }

    @GetMapping(value = ACCOUNT_FETCHBY_ACCOUNT_NUMBER_URL)
    public ResponseEntity<AccountResponse> findAccount(@PathVariable long customerId, @PathVariable String accountNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.findAccount(customerId, accountNumber));
    }

    @DeleteMapping(value = ACCOUNT_DELETE_URL)
    public ResponseEntity<String> deleteAccount(@PathVariable long customerId,
                                                @PathVariable String accountNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.deleteAccount(customerId, accountNumber));
    }
}