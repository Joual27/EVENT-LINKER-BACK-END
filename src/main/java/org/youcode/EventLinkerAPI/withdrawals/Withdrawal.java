package org.youcode.EventLinkerAPI.withdrawals;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.youcode.EventLinkerAPI.shared.utils.BaseEntity;
import org.youcode.EventLinkerAPI.withdrawals.enums.WithdrawalStatus;
import org.youcode.EventLinkerAPI.worker.Worker;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Withdrawal extends BaseEntity {
    private double amount ;
    private LocalDateTime requestedOn ;
    private WithdrawalStatus status;
    private String stripePayoutId;

    @ManyToOne
    @JoinColumn(name = "WORKER_ID")
    private Worker worker;
}
