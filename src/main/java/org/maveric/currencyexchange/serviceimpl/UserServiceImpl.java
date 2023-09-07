package org.maveric.currencyexchange.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.maveric.currencyexchange.exception.InvalidCredentialsException;
import org.maveric.currencyexchange.repository.ICredentialsRepository;
import org.maveric.currencyexchange.service.IUserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final ICredentialsRepository credentialsRepo;

    @Override
    public UserDetailsService userDetailsService() {
        return email -> credentialsRepo.findByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);
    }
}