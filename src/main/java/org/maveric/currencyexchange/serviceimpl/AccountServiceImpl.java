package org.maveric.currencyexchange.serviceimpl;

import org.maveric.currencyexchange.entity.Account;
import org.maveric.currencyexchange.entity.Customer;
import org.maveric.currencyexchange.enums.CurrencyType;
import org.maveric.currencyexchange.exception.*;
import org.maveric.currencyexchange.payload.request.AccountRequest;
import org.maveric.currencyexchange.payload.response.AccountResponse;
import org.maveric.currencyexchange.repository.IAccountRepository;
import org.maveric.currencyexchange.repository.ICustomerRepository;
import org.maveric.currencyexchange.service.IAccountService;
import org.maveric.currencyexchange.utils.AccountNumberGenerator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static org.maveric.currencyexchange.constants.AppConstants.ACCOUNT_DELETE_MESSAGE;

@Service
public class AccountServiceImpl implements IAccountService {
    private ModelMapper mapper;
    private IAccountRepository accountRepo;
    private ICustomerRepository customerRepo;
    private AccountNumberGenerator accountNumberGenerator;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AccountServiceImpl(ModelMapper mapper, IAccountRepository accountRepo, ICustomerRepository customerRepo, AccountNumberGenerator accountNumberGenerator) {
        this.accountNumberGenerator = accountNumberGenerator;
        this.accountRepo = accountRepo;
        this.customerRepo = customerRepo;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public AccountResponse createAccount(long customerId, AccountRequest accountRequest) {
        Customer customer = verfiyCustomer(customerId);
        Account account = mapper.map(accountRequest, Account.class);
        account.setCustomer(customer);

        logger.info(Boolean.toString(isCustomerHasAccountWithSameCurrency(customerId, accountRequest)));
        if (isCustomerHasAccountWithSameCurrency(customerId, accountRequest)) {
            logger.info(Boolean.toString(isCustomerHasAccountWithSameCurrency(customerId, accountRequest)));
            throw new AccountAlreadyExistsException();
        }

        account = accountRepo.save(account);
        String accountNumber = accountNumberGenerator.generateUniqueAccountNumber(account.getId());
        account.setAccountNumber(accountNumber);
        return mapper.map(account, AccountResponse.class);
    }

    private boolean isCustomerHasAccountWithSameCurrency(long customerId, AccountRequest accountRequest) {
        List<Account> accounts = accountRepo.findByCustomerId(customerId);
        CurrencyType currency = accountRequest.getCurrency();
        Stream<Account> filteredAccounts = accounts.stream().filter(account -> account.getCurrency().equals(currency));
        return filteredAccounts.count() == 0 ? false : true;
    }

    @Override
    @Transactional
    public AccountResponse updateAccount(long customerId, String accountNumber, AccountRequest accountRequest) {
        verfiyCustomer(customerId);
        return updateAccountUtil(customerId, accountNumber, accountRequest);
    }

    public AccountResponse updateAccountUtil(long customerId, String accountNumber, AccountRequest accountRequest) {
        Account account = verifyAccount(accountNumber, customerId);
        mapper.map(accountRequest, account);
        account = accountRepo.save(account);

        return mapper.map(account, AccountResponse.class);
    }

    @Override
    @Transactional
    public String deleteAccount(long customerId, String accountNumber) {
        Account account = verifyAccount(accountNumber, customerId);
        accountRepo.delete(account);
        return ACCOUNT_DELETE_MESSAGE;
    }

    @Override
    public List<AccountResponse> findAllAccounts(long customerId) {
        Customer customer = verfiyCustomer(customerId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Customer authenticatedCustomer = customerRepo.findByEmail(email).orElseThrow(CustomerNotFoundException::new);
        if (!authenticatedCustomer.getEmail().equals(customer.getEmail()))
            throw new UnauthorizedAccessException();
        List<Account> accounts = accountRepo.findByCustomerId(customerId);
        return accounts.stream()
                .map(account -> mapper.map(account, AccountResponse.class))
                .toList();
    }

    @Override
    public AccountResponse findAccount(long customerId, String accountNumber) {
        Account account = verifyAccount(accountNumber, customerId);
        return mapper.map(account, AccountResponse.class);
    }

    public Account verifyAccount(String accountNumber, long customerId) {
        Account account = accountRepo.findByAccountNumber(accountNumber).orElseThrow(AccountNotFoundException::new);

        boolean isAccountOwner = account.getCustomer().getId().equals(customerId);

        if (!isAccountOwner) {
            throw new AccountMisMatchException();
        }
        return account;
    }

    public Customer verfiyCustomer(long customerId) {
        return customerRepo.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }
}