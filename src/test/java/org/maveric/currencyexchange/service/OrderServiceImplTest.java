package org.maveric.currencyexchange.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maveric.currencyexchange.entity.Account;
import org.maveric.currencyexchange.entity.Customer;
import org.maveric.currencyexchange.entity.Transaction;
import org.maveric.currencyexchange.enums.CurrencyType;
import org.maveric.currencyexchange.payload.request.OrderRequest;
import org.maveric.currencyexchange.payload.response.OrderResponse;
import org.maveric.currencyexchange.repository.*;
import org.maveric.currencyexchange.serviceimpl.CustomerServiceImpl;
import org.maveric.currencyexchange.serviceimpl.ExchangeRatesServiceImpl;
import org.maveric.currencyexchange.serviceimpl.OrderServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private ITransactionRepository transactionRepo;

    @Mock
    private ICustomerRepository customerRepo;

    @Mock
    private IAccountRepository accountRepo;

    @Mock
    private ICredentialsRepository CredentialsRepo;

    @Mock
    private IRolesRepository rolesRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Authentication authentication;

    private IExchangeRatesService exchangeService = new ExchangeRatesServiceImpl("https://open.er-api.com/v6/latest/");

    private ModelMapper mapper = new ModelMapper();

    @InjectMocks
    private OrderServiceImpl orderService;

    private CustomerServiceImpl customerService;
    @Before()
    public void setUp() {
        this.orderService = new OrderServiceImpl(transactionRepo,customerRepo, accountRepo,mapper,exchangeService);
        customerService = new CustomerServiceImpl(mapper,customerRepo,CredentialsRepo,rolesRepo,passwordEncoder);
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void fetchAllTransactionsTest(){
        Transaction transaction= new Transaction();
        List<Transaction> transactionList = new ArrayList<Transaction>();
        transactionList.add(transaction);
        transactionList.add(transaction);
        Page<Transaction> transactionPage = new PageImpl<>(transactionList);
        when(transactionRepo.findByCustomerId(Mockito.any(),Mockito.any(Pageable.class))).thenReturn(transactionPage);
        when(customerRepo.findByEmail(Mockito.any())).thenReturn(Optional.of(new Customer()));
        List<OrderResponse> orderResponseList = orderService.fetchAllTransactions(0,1);
        Assert.assertEquals(2,orderResponseList.size());
    }

    @Test
    public void createTransactionTest(){
        Account sourceAccount = new Account();
        sourceAccount.setId(1L);
        sourceAccount.setAccountNumber("123");
        sourceAccount.setCurrency(CurrencyType.INR);
        sourceAccount.setAmount(BigDecimal.valueOf(100));

        Account destinationAccount = new Account();
        destinationAccount.setId(2L);
        destinationAccount.setAccountNumber("456");
        destinationAccount.setCurrency(CurrencyType.USD);
        destinationAccount.setAmount(BigDecimal.valueOf(0));

        List<Account> accountList = new ArrayList();
        accountList.add(sourceAccount);
        accountList.add(destinationAccount);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Habi");

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setSrcAccount("123");
        orderRequest.setDestAccount("456");
        orderRequest.setAmount(100);

        Transaction transaction = mapper.map(orderRequest, Transaction.class);


        Mockito.when(accountRepo.findByCustomerId(1L)).thenReturn(accountList);
        Mockito.when(accountRepo.findByAccountNumber("123")).thenReturn(Optional.of(sourceAccount));
        Mockito.when(accountRepo.findByAccountNumber("456")).thenReturn(Optional.of(destinationAccount));
        Mockito.when(transactionRepo.save(Mockito.any())).thenAnswer(args->args.getArgument(0));
        when(customerRepo.findByEmail(Mockito.any())).thenReturn(Optional.of( customer));
        authentication.setAuthenticated(true);
        OrderResponse actualResponse = orderService.createTransaction(orderRequest);
        System.out.println(actualResponse);
        Assert.assertEquals("INR/USD",actualResponse.getCurrencyPair());
        Assert.assertEquals((double) 100,actualResponse.getAmount(),0);
    }

}
