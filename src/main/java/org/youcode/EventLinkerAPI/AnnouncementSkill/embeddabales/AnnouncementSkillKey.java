package org.youcode.EventLinkerAPI.AnnouncementSkill.embeddabales;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class AnnouncementSkillKey {
    Long skillId ;
    Long announcementId;
}
