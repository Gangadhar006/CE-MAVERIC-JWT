package org.maveric.currencyexchange.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maveric.currencyexchange.payload.request.AccountRequest;
import org.maveric.currencyexchange.payload.response.AccountResponse;
import org.maveric.currencyexchange.service.IAccountService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private IAccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void createAccount() {
        long customerId = 1L;
        AccountRequest accountRequest = new AccountRequest();
        AccountResponse accountResponse = new AccountResponse();
        when(accountService.createAccount(customerId, accountRequest)).thenReturn(accountResponse);
        ResponseEntity<AccountResponse> response = accountController.createAccount(customerId, accountRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(accountResponse, response.getBody());
    }

    @Test
    void updateAccount() {
        long customerId = 1L;
        String accountNumber = "1234567890";
        AccountRequest accountRequest = new AccountRequest();
        AccountResponse accountResponse = new AccountResponse();
        when(accountService.updateAccount(customerId, accountNumber, accountRequest)).thenReturn(accountResponse);
        ResponseEntity<AccountResponse> response = accountController.updateAccount(customerId, accountNumber, accountRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountResponse, response.getBody());
    }

    @Test
    void findAllAccounts() {
        long customerId = 1L;
        List<AccountResponse> accountResponses = Collections.singletonList(new AccountResponse());
        when(accountService.findAllAccounts(customerId)).thenReturn(accountResponses);
        ResponseEntity<List<AccountResponse>> response = accountController.findAllAccounts(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountResponses, response.getBody());
    }

    @Test
    void findAccount() {
        long customerId = 1L;
        String accountNumber = "1234567890";
        AccountResponse accountResponse = new AccountResponse();
        when(accountService.findAccount(customerId, accountNumber)).thenReturn(accountResponse);
        ResponseEntity<AccountResponse> response = accountController.findAccount(customerId, accountNumber);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountResponse, response.getBody());
    }

    @Test
    void deleteAccount() {
        long customerId = 1L;
        String accountNumber = "1234567890";
        when(accountService.deleteAccount(customerId, accountNumber)).thenReturn("Account deleted successfully");
        ResponseEntity<String> response = accountController.deleteAccount(customerId, accountNumber);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Account deleted successfully", response.getBody());
    }
}