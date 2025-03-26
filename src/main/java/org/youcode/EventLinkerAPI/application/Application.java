package org.youcode.EventLinkerAPI.application;

import jakarta.persistence.*;
import lombok.*;
import org.youcode.EventLinkerAPI.announcement.Announcement;
import org.youcode.EventLinkerAPI.application.enums.ApplicationStatus;
import org.youcode.EventLinkerAPI.payment.Payment;
import org.youcode.EventLinkerAPI.review.Review;
import org.youcode.EventLinkerAPI.shared.utils.BaseEntity;
import org.youcode.EventLinkerAPI.worker.Worker;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Application extends BaseEntity {
    private double price;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
    private String letter ;
    @ManyToOne(fetch =FetchType.EAGER)
    @ToString.Exclude
    @JoinColumn(name = "ANNOUNCEMENT_ID")
    private Announcement announcement;

    @ManyToOne(fetch =FetchType.EAGER)
    @ToString.Exclude
    @JoinColumn(name = "APPLICANT_ID" )
    private Worker applicant ;

    @OneToOne(mappedBy = "application")
    @ToString.Exclude
    private Review review;

    @OneToOne(mappedBy = "application" , cascade = CascadeType.ALL , fetch =FetchType.EAGER )
    @ToString.Exclude
    private Payment payment;
}
