package org.maveric.currencyexchange.service;

import org.maveric.currencyexchange.payload.request.OrderRequest;
import org.maveric.currencyexchange.payload.response.OrderResponse;

import java.util.List;

public interface IOrderService {
    List<OrderResponse> fetchAllTransactions(int page, int size);

    OrderResponse createTransaction(OrderRequest orderRequest);
}
