package org.youcode.EventLinkerAPI.announcement.specifications;


import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.youcode.EventLinkerAPI.AnnouncementSkill.AnnouncementSkill;
import org.youcode.EventLinkerAPI.announcement.Announcement;
import org.youcode.EventLinkerAPI.event.Event;
import org.youcode.EventLinkerAPI.skill.Skill;

public class AnnouncementSpecification {

    public static Specification<Announcement> withSearchQuery(String searchTerm) {
        return (Root<Announcement> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                return cb.conjunction();
            }

            String likePattern = "%" + searchTerm.toLowerCase() + "%";

            Join<Announcement, Event> eventJoin = root.join("event", JoinType.LEFT);
            Join<Announcement, AnnouncementSkill> announcementSkillJoin = root.join("announcementSkills", JoinType.LEFT);
            Join<AnnouncementSkill, Skill> skillJoin = announcementSkillJoin.join("skill", JoinType.LEFT);

            return cb.or(
                    cb.like(cb.lower(root.get("title")), likePattern),
                    cb.like(cb.lower(root.get("description")), likePattern),
                    cb.like(cb.lower(eventJoin.get("title")), likePattern),
                    cb.like(cb.lower(skillJoin.get("name")), likePattern)
            );
        };
    }
}