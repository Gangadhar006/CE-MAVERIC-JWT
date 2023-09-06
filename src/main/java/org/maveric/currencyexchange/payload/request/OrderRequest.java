package org.maveric.currencyexchange.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.maveric.currencyexchange.payload.response.CustomerResponse;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotBlank(message = "Source account can't be Blank")
    private String srcAccount;

    @NotBlank(message = "Destination account can't be Blank")
    private String destAccount;

    @Positive(message = "Amount must be a positive number and Not Blank")
    private double amount;
}