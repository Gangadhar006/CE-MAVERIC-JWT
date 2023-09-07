package org.maveric.currencyexchange.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maveric.currencyexchange.payload.request.ExchangeRates;
import org.maveric.currencyexchange.service.IExchangeRatesService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ExchangeRatesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IExchangeRatesService exchangeService;

    @InjectMocks
    private ExchangeRatesController exchangeRatesController;

    @Test
    public void getLatestExchangeRates() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(exchangeRatesController).build();
        when(exchangeService.getLatestExchangeRates("USD")).thenReturn(new ExchangeRates());

        mockMvc.perform(get("/exchange-rates/USD"))
                .andExpect(status().isOk());
    }
}