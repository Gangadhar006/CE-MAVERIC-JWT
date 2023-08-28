package org.maveric.currencyexchange.repository;

import org.maveric.currencyexchange.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICredentialsRepository extends JpaRepository<Credentials,Long> {
    Optional<Credentials> findByEmail(String email);
}
