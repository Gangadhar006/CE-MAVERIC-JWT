package org.maveric.currencyexchange.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.maveric.currencyexchange.payload.request.ExchangeRates;
import org.maveric.currencyexchange.service.IExchangeRatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.maveric.currencyexchange.constants.ApiEndpointConstants.*;
import static org.maveric.currencyexchange.constants.SecurityConstants.*;

@RestController
@RequestMapping(value = EXCHANGE_RATES_URL_PREFIX)
@SecurityRequirement(name = SECURITY_SCHEME_NAME)
public class ExchangeRatesController {
    private IExchangeRatesService exchangeService;

    public ExchangeRatesController(IExchangeRatesService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping(value = EXCHANGE_RATES_BASECURRENCY_URL)
    public ResponseEntity<ExchangeRates> getLatestExchangeRates(@PathVariable String baseCurrency) {
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.getLatestExchangeRates(baseCurrency));
    }
}