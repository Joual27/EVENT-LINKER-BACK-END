package org.youcode.EventLinkerAPI.review;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.shared.utils.BaseEntity;
import org.youcode.EventLinkerAPI.user.User;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review extends BaseEntity {
    private int rating ;
    private String comment ;
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPLICATION_ID")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "REVIEWER_ID")
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "REVIEWEE_ID")
    private User reviewee;
}
