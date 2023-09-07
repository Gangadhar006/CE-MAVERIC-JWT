package org.maveric.currencyexchange.serviceimpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maveric.currencyexchange.entity.Credentials;
import org.maveric.currencyexchange.exception.InvalidCredentialsException;
import org.maveric.currencyexchange.repository.ICredentialsRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserServiceImpl userService;

    @Mock
    private ICredentialsRepository credentialsRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(credentialsRepo);
    }

    @Test
    public void userDetailsServiceWithEmailExistsTest() {
        String email = "test@example.com";
        Credentials credentials = Credentials.builder().email(email).password("password").build();
        when(credentialsRepo.findByEmail(email)).thenReturn(Optional.of(credentials));

        UserDetailsService userDetailsService = userService.userDetailsService();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        verify(credentialsRepo, times(1)).findByEmail(email);
        assert userDetails.getUsername().equals(email);
    }

    @Test
    public void UserDetailsServiceWithEmailNotExistsTest() {
        String email = "nonexistent@example.com";
        when(credentialsRepo.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> {
            UserDetailsService userDetailsService = userService.userDetailsService();
            userDetailsService.loadUserByUsername(email);
        });
    }
}
