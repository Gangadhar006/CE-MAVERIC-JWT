package org.maveric.currencyexchange.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.maveric.currencyexchange.constants.SecurityConstants.*;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(PERMIT_ALL_URLS)
                        .allowedOrigins("${corsAllowedOrigin}")
                        .allowedMethods(CORS_ALLOWED_METHODS)
                        .allowCredentials(true);
            }
        };
    }
}