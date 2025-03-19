package org.youcode.EventLinkerAPI.user.DTOs;

import lombok.*;
import org.youcode.EventLinkerAPI.event.DTOs.EmbeddedEventDTO;
import org.youcode.EventLinkerAPI.event.mapper.EventMapper;
import org.youcode.EventLinkerAPI.review.DTOs.EmbeddedReviewDTO;

import org.youcode.EventLinkerAPI.review.Review;
import org.youcode.EventLinkerAPI.review.mapper.ReviewMapper;
import org.youcode.EventLinkerAPI.skill.Skill;
import org.youcode.EventLinkerAPI.event.Event;
import org.youcode.EventLinkerAPI.user.User;
import org.youcode.EventLinkerAPI.worker.Worker;
import org.youcode.EventLinkerAPI.organizer.Organizer;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private String bio;
    private String profileImgUrl;
    private List<EmbeddedReviewDTO> reviews;
    private String organizationName;
    private List<EmbeddedEventDTO> events;

    public static UserResponseDTO fromUser(User user, ReviewMapper reviewMapper , EventMapper eventMapper) {
        UserResponseDTO.UserResponseDTOBuilder builder = UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsernameField())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .bio(user.getBio())
                .profileImgUrl(user.getProfileImgUrl())
                .reviews(mapReviewToDTO(user.getReceivedReviews(), reviewMapper))
                .role(user.getUserRole());

        if (user instanceof Worker worker) {
            builder.isOrganization(worker.isOrganization())
                    .balance(worker.getBalance())
                    .skills(worker.getSkills());
        } else if (user instanceof Organizer organizer) {
            builder.organizationName(organizer.getOrganizationName())
                    .events(mapEventsToDTO(organizer.getEvents(), eventMapper));
        }
        return builder.build();
    }

    public static List<EmbeddedReviewDTO> mapReviewToDTO(List<Review> reviews, ReviewMapper reviewMapper ) {
        if (reviews == null || reviews.isEmpty()) {
            return new ArrayList<>();
        }
        return reviews.stream().map(reviewMapper::toEmbeddedDTO).toList();
    }

    public static List<EmbeddedEventDTO> mapEventsToDTO(List<Event> events , EventMapper eventMapper){
        if (events == null || events.isEmpty()) {
            return new ArrayList<>();
        }
        return events.stream().map(eventMapper::toEmbeddedDTO).toList();
    }
}