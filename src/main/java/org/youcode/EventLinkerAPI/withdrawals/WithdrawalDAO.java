package org.youcode.EventLinkerAPI.withdrawals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalDAO extends JpaRepository<Withdrawal , Long > {
}
