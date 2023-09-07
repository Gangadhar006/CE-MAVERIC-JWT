package org.maveric.currencyexchange.serviceimpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maveric.currencyexchange.entity.Credentials;
import org.maveric.currencyexchange.payload.request.LoginRequest;
import org.maveric.currencyexchange.payload.response.JwtAuthenticationResponse;
import org.maveric.currencyexchange.repository.ICredentialsRepository;
import org.maveric.currencyexchange.service.IJwtService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private ICredentialsRepository credentialsRepository;

    @Mock
    private IJwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void signinTest() {
        LoginRequest request = new LoginRequest("test@example.com", "password");
        Credentials testCredentials = new Credentials();
        testCredentials.setEmail("test@example.com");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(credentialsRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testCredentials));
        when(jwtService.generateToken(testCredentials)).thenReturn("testToken");

        JwtAuthenticationResponse response = authenticationService.signin(request);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(credentialsRepository).findByEmail("test@example.com");
        verify(jwtService).generateToken(testCredentials);

        Assertions.assertNotNull(response.getToken());
        Assertions.assertEquals("testToken", response.getToken());
    }
}
