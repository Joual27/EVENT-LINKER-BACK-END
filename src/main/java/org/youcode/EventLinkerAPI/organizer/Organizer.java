package org.youcode.EventLinkerAPI.organizer;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.youcode.EventLinkerAPI.event.Event;
import org.youcode.EventLinkerAPI.user.User;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Organizer extends User {
    private String organizationName;
    @OneToMany(mappedBy = "organizer" , fetch = FetchType.EAGER)
    private List<Event> events;

    @Override
    public String getUserRole() {
        return "ORGANIZER";
    }
}
