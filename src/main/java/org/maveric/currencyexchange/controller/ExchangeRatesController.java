package org.maveric.currencyexchange.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.maveric.currencyexchange.payload.request.ExchangeRates;
import org.maveric.currencyexchange.service.IExchangeRatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange-rates")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "${corsAllowedOrigin}")
public class ExchangeRatesController {
    private IExchangeRatesService exchangeService;

    public ExchangeRatesController(IExchangeRatesService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/{baseCurrency}")
    public ResponseEntity<ExchangeRates> getLatestExchangeRates(@PathVariable("baseCurrency") String baseCurrency) {
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.getLatestExchangeRates(baseCurrency));
    }
}