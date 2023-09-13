package org.maveric.currencyexchange.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.maveric.currencyexchange.constants.SecurityConstants.*;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Value("${corsAllowedOrigin}")
    private String corsAllowedOrigin;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(PERMIT_ALL_URLS)
                .allowedOrigins(corsAllowedOrigin)
                .allowedMethods(CORS_ALLOWED_METHODS)
                .allowCredentials(true);
    }
}