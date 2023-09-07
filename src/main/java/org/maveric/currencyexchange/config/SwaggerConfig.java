package org.maveric.currencyexchange.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import static org.maveric.currencyexchange.constants.SecurityConstants.*;
import static org.maveric.currencyexchange.constants.AppConstants.*;


@OpenAPIDefinition(info = @Info(title = API_TITLE, version = API_VERSION, description = API_DESCRIPTION))
@SecurityScheme(
        name = SECURITY_SCHEME_NAME,
        type = SecuritySchemeType.HTTP,
        scheme = BEARER,
        bearerFormat = SECURITY_BEARER_FORMAT
)
public class SwaggerConfig {

}