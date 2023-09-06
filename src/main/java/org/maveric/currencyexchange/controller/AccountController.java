package org.maveric.currencyexchange.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.maveric.currencyexchange.payload.request.AccountRequest;
import org.maveric.currencyexchange.payload.response.AccountResponse;
import org.maveric.currencyexchange.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/account")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "${corsAllowedOrigin}")
public class AccountController {
    private IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/create/{customerId}")
    public ResponseEntity<AccountResponse> createAccount(@PathVariable long customerId,
                                                         @Valid @RequestBody AccountRequest accountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccount(customerId, accountRequest));
    }

    @PutMapping(value = "/update/{customerId}/{accountNumber}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable long customerId,
                                                         @PathVariable String accountNumber,
                                                         @Valid @RequestBody AccountRequest accountRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.updateAccount(customerId, accountNumber, accountRequest));
    }

    @GetMapping(value = "/fetchAll/{customerId}")
    public ResponseEntity<List<AccountResponse>> findAllAccounts(@PathVariable long customerId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.findAllAccounts(customerId));
    }

    @GetMapping(value = "/fetch/{customerId}/{accountNumber}")
    public ResponseEntity<AccountResponse> findAccount(@PathVariable long customerId,@PathVariable String accountNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.findAccount(customerId,accountNumber));
    }

    @DeleteMapping(value = "/delete/{customerId}/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable("customerId") long customerId,
                                                @PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.deleteAccount(customerId, accountNumber));
    }
}