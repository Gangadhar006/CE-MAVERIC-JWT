package org.maveric.currencyexchange.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.maveric.currencyexchange.payload.request.ExchangeRates;
import org.maveric.currencyexchange.service.IExchangeRatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange-rates")
@SecurityRequirement(name = "bearerAuth")
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