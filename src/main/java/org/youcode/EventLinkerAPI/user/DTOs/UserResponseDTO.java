package org.youcode.EventLinkerAPI.user.DTOs;

import lombok.*;
import org.youcode.EventLinkerAPI.skill.Skill;
import org.youcode.EventLinkerAPI.event.Event;
import org.youcode.EventLinkerAPI.user.User;
import org.youcode.EventLinkerAPI.worker.Worker;
import org.youcode.EventLinkerAPI.organizer.Organizer;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private String role;

    private Boolean isOrganization;
    private Double balance;
    private List<Skill> skills;

    private String organizationName;
    private List<Event> events;

    public static UserResponseDTO fromUser(User user) {
        UserResponseDTO.UserResponseDTOBuilder builder = UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsernameField())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .role(user.getUserRole());

        if (user instanceof Worker worker) {
            builder.isOrganization(worker.isOrganization())
                    .balance(worker.getBalance())
                    .skills(worker.getSkills());
        } else if (user instanceof Organizer organizer) {
            builder.organizationName(organizer.getOrganizationName())
                    .events(organizer.getEvents());
        }

        return builder.build();
    }
}