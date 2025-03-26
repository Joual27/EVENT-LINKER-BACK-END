package org.youcode.EventLinkerAPI.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentDAO extends JpaRepository<Payment , Long> {
    Optional<Payment> findByApplication_Id(Long applicationId);
}
