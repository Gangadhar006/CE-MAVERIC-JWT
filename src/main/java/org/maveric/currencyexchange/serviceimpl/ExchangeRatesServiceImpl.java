package org.maveric.currencyexchange.serviceimpl;

import org.maveric.currencyexchange.payload.request.ExchangeRates;
import org.maveric.currencyexchange.service.IExchangeRatesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.maveric.currencyexchange.constants.SecurityConstants.EXCHANGE_RATES_API_URL;

@Service
public class ExchangeRatesServiceImpl implements IExchangeRatesService {

    private String API_URL;

    public ExchangeRatesServiceImpl(@Value(EXCHANGE_RATES_API_URL) String API_URL) {
        this.API_URL = API_URL;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    public ExchangeRates getLatestExchangeRates(String baseCurrency) {
        return restTemplate.getForObject(API_URL.concat(baseCurrency), ExchangeRates.class);
    }
}