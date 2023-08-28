package org.maveric.currencyexchange.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExchangeRates {
    private Map<String, Double> rates;
}

