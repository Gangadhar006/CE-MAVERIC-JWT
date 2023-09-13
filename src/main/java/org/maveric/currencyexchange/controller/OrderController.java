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
import static org.maveric.currencyexchange.constants.ApiEndpointConstants.*;
import static org.maveric.currencyexchange.constants.AppConstants.*;
import static org.maveric.currencyexchange.constants.SecurityConstants.*;

@RestController
@RequestMapping(value = ORDER_URL_PREFIX)
@SecurityRequirement(name = SECURITY_SCHEME_NAME)
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = ORDER_WATCHLIST_URL)
    public ResponseEntity<List<OrderResponse>> fetchAllTransactions(
            @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_SIZE) Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.fetchAllTransactions(page, size));
    }

    @PostMapping(value = ORDER_PLACE_URL)
    public ResponseEntity<OrderResponse> createTransaction(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.createTransaction(orderRequest));
    }
}