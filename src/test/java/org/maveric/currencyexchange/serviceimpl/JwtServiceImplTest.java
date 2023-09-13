package org.maveric.currencyexchange.serviceimpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.maveric.currencyexchange.constants.SecurityConstants.*;

class JwtServiceImplTest {

    private String signingKey = TOKEN_SIGNING_KEY;

    @InjectMocks
    private JwtServiceImpl jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", signingKey);
    }

    @Test
    void testExtractEmail() {
        String token = generateTestToken();
        String email = jwtService.extractEmail(token);
        assertEquals("test@example.com", email);
    }

    @Test
    void testGenerateToken() {
        UserDetails userDetails = new User("test@example.com", "password", new ArrayList<>());
        String token = jwtService.generateToken(userDetails);
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void testIsTokenValid() {
        UserDetails userDetails = new User("test@example.com", "password", new ArrayList<>());
        String token = generateTestToken();

        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    private String generateTestToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", "test@example.com");
        claims.put("exp", new Date(System.currentTimeMillis() + 3600000));
        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(TOKEN_SIGNING_KEY)),
                        SignatureAlgorithm.HS256).compact();
    }
}
