package org.maveric.currencyexchange.serviceimpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maveric.currencyexchange.entity.Account;
import org.maveric.currencyexchange.entity.Customer;
import org.maveric.currencyexchange.payload.request.AccountRequest;
import org.maveric.currencyexchange.payload.response.AccountResponse;
import org.maveric.currencyexchange.repository.IAccountRepository;
import org.maveric.currencyexchange.repository.ICustomerRepository;
import org.maveric.currencyexchange.utils.AccountNumberGenerator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private IAccountRepository accountRepo;

    @Mock
    private ICustomerRepository customerRepo;

    @Mock
    private AccountNumberGenerator accountNumberGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateAccountTest() {
        Customer customer = new Customer();
        customer.setId(1L);

        Account account = new Account();
        account.setAmount(BigDecimal.valueOf(123456));
        account.setCustomer(customer);

        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
        when(accountRepo.save(any(Account.class))).thenReturn(account);

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAmount(account.getAmount());

        AccountResponse result = accountService.updateAccountUtil(1L, "123456", accountRequest);

        verify(accountRepo, times(1)).save(account);

        assertNotNull(result);
        assertEquals(account.getAccountNumber(), result.getAccountNumber());
    }
}