package org.youcode.EventLinkerAPI.AnnouncementSkill;

import jakarta.persistence.*;
import lombok.*;
import org.youcode.EventLinkerAPI.AnnouncementSkill.embeddabales.AnnouncementSkillKey;
import org.youcode.EventLinkerAPI.announcement.Announcement;
import org.youcode.EventLinkerAPI.skill.Skill;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AnnouncementSkill {
    @EmbeddedId
    private AnnouncementSkillKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("announcementId")
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    @ToString.Exclude
    private Skill skill;

    private boolean acceptsNonOrganizations;
}
