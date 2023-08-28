package org.maveric.currencyexchange.service;

import org.maveric.currencyexchange.payload.request.LoginRequest;
import org.maveric.currencyexchange.payload.response.JwtAuthenticationResponse;

public interface IAuthenticationService {

    JwtAuthenticationResponse signin(LoginRequest request);
}
