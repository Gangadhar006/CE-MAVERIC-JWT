package org.maveric.currencyexchange.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.maveric.currencyexchange.payload.request.OrderRequest;
import org.maveric.currencyexchange.payload.response.OrderResponse;
import org.maveric.currencyexchange.service.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "${corsAllowedOrigin}")
public class OrderController {
    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/watchlist")
    public ResponseEntity<List<OrderResponse>> fetchAllTransactions(
            @RequestParam(defaultValue = "${defaultPage}") Integer page,
            @RequestParam(defaultValue = "${defaultSize}") Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.fetchAllTransactions(page, size));
    }

    @PostMapping(value = "/placeorder")
    public ResponseEntity<OrderResponse> createTransaction(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createTransaction(orderRequest));
    }
}