package org.maveric.currencyexchange.service;

import org.maveric.currencyexchange.payload.request.ExchangeRates;

public interface IExchangeRatesService {
    ExchangeRates getLatestExchangeRates(String baseCurrency);
}
