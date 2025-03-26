package org.youcode.EventLinkerAPI.message;

import jakarta.persistence.*;
import lombok.*;
import org.youcode.EventLinkerAPI.DM.DM;
import org.youcode.EventLinkerAPI.shared.utils.BaseEntity;
import org.youcode.EventLinkerAPI.user.User;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "DM_ID")
    @EqualsAndHashCode.Exclude
    private DM dm;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @EqualsAndHashCode.Exclude
    private User user;

    private LocalDateTime sentAt;
    private boolean delivered;
    private LocalDateTime deliveredAt;
    private LocalDateTime seenAt;
    private String content ;
}
