package org.maveric.currencyexchange.payload.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.maveric.currencyexchange.enums.GenderType;

import static org.maveric.currencyexchange.constants.ValidationConstants.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @NotBlank(message = FIRST_NAME_MESSAGE)
    @Pattern(regexp = FIRST_NAME_PATTERN, message = NAME_PATTERN_MESSAGE)
    private String firstName;
    @NotBlank(message = LAST_NAME_MESSAGE)
    @Pattern(regexp = LAST_NAME_PATTERN, message = NAME_PATTERN_MESSAGE)
    private String lastName;
    @NotNull
    @Past(message = DOB_MESSAGE)
    private LocalDate dob;
    @NotBlank
    @Pattern(regexp = EMAIL_PATTERN, message = EMAIL_PATTERN_MESSAGE)
    private String email;
    @NotNull(message = GENDER_MESSAGE)
    private GenderType gender;

    @Size(min = PHONE_LENGTH, max = PHONE_LENGTH, message = PHONE_MESSAGE)
    @Pattern(regexp = PHONE_PATTERN, message = PHONE_PATTERN_MESSAGE)
    private String phone;
    @NotBlank(message = PASSWORD_MESSAGE)
    @Size(min = PASSWORD_LENGTH, message = PASSWORD_PATTERN_MESSAGE)
    private String password;
}
