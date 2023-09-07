package org.maveric.currencyexchange.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.maveric.currencyexchange.constants.ValidationConstants.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotBlank(message = SRCACCOUNT_MESSAGE)
    private String srcAccount;
    @NotBlank(message = DESTACCOUNT_MESSAGE)
    private String destAccount;
    @Positive(message = AMOUNT_POSITIVE_MESSAGE)
    private double amount;
}