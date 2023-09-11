package org.maveric.currencyexchange.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.maveric.currencyexchange.enums.CurrencyType;

import java.math.BigDecimal;

import static org.maveric.currencyexchange.constants.ValidationConstants.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotNull(message = AMOUNT_NOTNULL_MESSAGE)
    @Positive(message = AMOUNT_POSITIVE_MESSAGE)
    private BigDecimal amount;
    @NotNull(message = CURRENCY_TYPE_MESSAGE)
    private CurrencyType currency;
    private boolean active;
}