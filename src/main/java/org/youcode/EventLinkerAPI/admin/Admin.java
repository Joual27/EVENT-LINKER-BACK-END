package org.youcode.EventLinkerAPI.admin;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.youcode.EventLinkerAPI.user.User;

@EqualsAndHashCode(callSuper = true)
@Entity
public class Admin extends User {
    @Override
    public String getUserRole() {
        return "ADMIN";
    }
}
