package org.maveric.currencyexchange.controller;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maveric.currencyexchange.payload.request.OrderRequest;
import org.maveric.currencyexchange.payload.response.OrderResponse;
import org.maveric.currencyexchange.repository.ITransactionRepository;
import org.maveric.currencyexchange.service.IOrderService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    private IOrderService orderService;

    @Mock
    private ITransactionRepository transactionRepository;

    private OrderController orderController;
    @Before()
    public void setUp() {
        this.orderController = new OrderController(orderService);
    }

    @Test
    public void constructorTest(){
        OrderController orderController1=new OrderController(orderService);
        Assert.assertNotNull(orderController1);
    }


    @Test
    public void fetchAllTransactionsTest(){

        List<OrderResponse> orderResponseList = new ArrayList<OrderResponse>();
        OrderResponse orderResponse = new OrderResponse();
        orderResponseList.add(orderResponse);
        orderResponseList.add(orderResponse);

        Mockito.when(orderService.fetchAllTransactions(0,1)).thenReturn(orderResponseList);

        ResponseEntity<List<OrderResponse>> actualResponse = orderController.fetchAllTransactions(0,1);

        Assert.assertEquals(200,actualResponse.getStatusCode().value());
        Assert.assertEquals(2,actualResponse.getBody().size());
    }

    @Test
    public void createTransactionTest(){
        OrderRequest orderRequest=new OrderRequest();
        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setTime(Date.valueOf(LocalDate.now()));
        Mockito.when(orderService.createTransaction(orderRequest)).thenReturn(orderResponse);

        ResponseEntity <OrderResponse> actualResponse = orderController.createTransaction(orderRequest);

        Assert.assertEquals(200,actualResponse.getStatusCode().value());
        Assert.assertEquals(Date.valueOf(LocalDate.now()).toString(),actualResponse.getBody().getTime().toString());
    }

}
