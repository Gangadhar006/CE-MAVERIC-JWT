package org.maveric.currencyexchange.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.maveric.currencyexchange.entity.Credentials;
import org.maveric.currencyexchange.payload.request.LoginRequest;
import org.maveric.currencyexchange.payload.response.JwtAuthenticationResponse;
import org.maveric.currencyexchange.repository.ICredentialsRepository;
import org.maveric.currencyexchange.service.IAuthenticationService;
import org.maveric.currencyexchange.service.IJwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final ICredentialsRepository credentialsRepo;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signin(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Credentials credential = credentialsRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(credential);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}