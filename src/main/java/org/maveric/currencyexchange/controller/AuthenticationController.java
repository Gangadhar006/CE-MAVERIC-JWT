package org.maveric.currencyexchange.controller;

import lombok.RequiredArgsConstructor;
import org.maveric.currencyexchange.payload.request.LoginRequest;
import org.maveric.currencyexchange.payload.response.JwtAuthenticationResponse;
import org.maveric.currencyexchange.service.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.maveric.currencyexchange.constants.ApiEndpointConstants.*;

@RestController
@RequestMapping(AUTHENTICATE_URL_PREFIX)
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @PostMapping(value = AUTHENTICATE_LOGIN_URL)
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
