package org.youcode.EventLinkerAPI.skill;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.youcode.EventLinkerAPI.AnnouncementSkill.AnnouncementSkill;
import org.youcode.EventLinkerAPI.shared.utils.BaseEntity;
import org.youcode.EventLinkerAPI.worker.Worker;
import java.util.List;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Skill extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<Worker> workers;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "skill")
    private Set<AnnouncementSkill> announcementSkills;
}
