package org.maveric.currencyexchange.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    String extractEmail(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
