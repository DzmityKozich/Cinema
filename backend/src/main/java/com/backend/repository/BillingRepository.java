package com.backend.repository;

import com.backend.entity.Billing;
import com.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    Billing findByIdBilling(Long id);
    Billing findByBillingUser(User user);
}
