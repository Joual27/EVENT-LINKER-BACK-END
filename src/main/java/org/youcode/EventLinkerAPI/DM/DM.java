package org.youcode.EventLinkerAPI.DM;

import jakarta.persistence.*;
import lombok.*;
import org.youcode.EventLinkerAPI.message.Message;
import org.youcode.EventLinkerAPI.shared.utils.BaseEntity;
import org.youcode.EventLinkerAPI.user.User;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DM extends BaseEntity {
    @OneToMany(mappedBy = "dm")
    @EqualsAndHashCode.Exclude
    private List<Message> messages;
    @ManyToMany()
    @JoinTable(
            name = "dm_participants",
            joinColumns = @JoinColumn(name = "dm_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<User> users;

}
