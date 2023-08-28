package org.maveric.currencyexchange.repository;

import org.maveric.currencyexchange.entity.Roles;
import org.maveric.currencyexchange.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolesRepository extends JpaRepository<Roles, Long> {
    Roles findByRole(RoleType roleType);
}
