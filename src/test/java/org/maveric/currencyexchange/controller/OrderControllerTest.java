package org.maveric.currencyexchange.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maveric.currencyexchange.payload.request.OrderRequest;
import org.maveric.currencyexchange.payload.response.OrderResponse;
import org.maveric.currencyexchange.repository.ITransactionRepository;
import org.maveric.currencyexchange.service.IOrderService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private IOrderService orderService;

    @Mock
    private ITransactionRepository transactionRepository;

    private OrderController orderController;
    @BeforeEach()
    public void setUp() {
        this.orderController = new OrderController(orderService);
    }

    @Test
    public void constructorTest(){
        OrderController orderController1=new OrderController(orderService);
        Assertions.assertNotNull(orderController1);
    }


    @Test
    public void fetchAllTransactionsTest(){

        List<OrderResponse> orderResponseList = new ArrayList<OrderResponse>();
        OrderResponse orderResponse = new OrderResponse();
        orderResponseList.add(orderResponse);
        orderResponseList.add(orderResponse);

        Mockito.when(orderService.fetchAllTransactions(0,1)).thenReturn(orderResponseList);

        ResponseEntity<List<OrderResponse>> actualResponse = orderController.fetchAllTransactions(0,1);

        Assertions.assertEquals(200,actualResponse.getStatusCode().value());
        Assertions.assertEquals(2,actualResponse.getBody().size());
    }

    @Test
    public void createTransactionTest(){
        OrderRequest orderRequest=new OrderRequest();
        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setTime(Date.valueOf(LocalDate.now()));
        Mockito.when(orderService.createTransaction(orderRequest)).thenReturn(orderResponse);

        ResponseEntity <OrderResponse> actualResponse = orderController.createTransaction(orderRequest);

        Assertions.assertEquals(200,actualResponse.getStatusCode().value());
        Assertions.assertEquals(Date.valueOf(LocalDate.now()).toString(),actualResponse.getBody().getTime().toString());
    }
}