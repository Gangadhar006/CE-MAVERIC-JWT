package org.maveric.currencyexchange.utils;

import org.maveric.currencyexchange.entity.Account;
import org.maveric.currencyexchange.exception.AccountNotFoundException;
import org.maveric.currencyexchange.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

import static org.maveric.currencyexchange.constants.AppConstants.*;

@Service
public class AccountNumberGenerator {
    private final IAccountRepository accountRepository;

    @Autowired
    public AccountNumberGenerator(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String generateUniqueAccountNumber(long id) {
        Random random = new Random();
        String accountNumber;
        do {
            int randomNumber = random.nextInt(RANDOM_ACCOUNT_MAX) + RANDOM_ACCOUNT_MIN;
            Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
            accountNumber = ACCOUNT_PREFIX.concat(account.getCurrency().name().concat(String.valueOf(randomNumber)));
        } while (accountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }
}