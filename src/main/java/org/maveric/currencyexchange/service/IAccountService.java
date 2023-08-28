package org.maveric.currencyexchange.service;

import org.maveric.currencyexchange.payload.request.AccountRequest;
import org.maveric.currencyexchange.payload.response.AccountResponse;

import java.util.List;

public interface IAccountService {
    AccountResponse createAccount(long customerId, AccountRequest accountDto);

    AccountResponse updateAccount(long customerId, String accountNumber, AccountRequest accountDto);

    String deleteAccount(long customerId, String accountNumber);

    List<AccountResponse> findAllAccounts(long id);

    AccountResponse findAccount(long customerId, String accountNumber);
}
