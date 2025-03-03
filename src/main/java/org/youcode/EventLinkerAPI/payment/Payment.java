package org.youcode.EventLinkerAPI.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.shared.utils.BaseEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment extends BaseEntity {
    private double amount ;
    private String status;
    private LocalDateTime processedOn;
    private String currency;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPLICATION_ID")
    private Application application;

}
