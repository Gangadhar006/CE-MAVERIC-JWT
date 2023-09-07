package org.maveric.currencyexchange.serviceimpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maveric.currencyexchange.payload.request.ExchangeRates;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.maveric.currencyexchange.constants.SecurityConstants.EXCHANGE_RATES_API_URL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeRatesServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExchangeRatesServiceImpl exchangeRatesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        exchangeRatesService = new ExchangeRatesServiceImpl(EXCHANGE_RATES_API_URL);
    }

    @Test
    void getLatestExchangeRatesTest() {
        String baseCurrency = "USD";

        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.85);
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setRates(rates);

        when(restTemplate.getForObject(any(String.class), eq(ExchangeRates.class)))
                .thenReturn(exchangeRates);

        ExchangeRates result = exchangeRatesService.getLatestExchangeRates(baseCurrency);

        verify(restTemplate, times(1))
                .getForObject(EXCHANGE_RATES_API_URL.concat(baseCurrency), ExchangeRates.class);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getRates().size());
        Assertions.assertEquals(0.85, result.getRates().get("EUR"));
    }
}