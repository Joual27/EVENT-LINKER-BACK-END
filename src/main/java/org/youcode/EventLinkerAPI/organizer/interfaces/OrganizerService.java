package org.youcode.EventLinkerAPI.organizer.interfaces;

import org.youcode.EventLinkerAPI.application.DTOs.ApplicationResponseDTO;
import org.youcode.EventLinkerAPI.organizer.Organizer;

public interface OrganizerService {
    Organizer getOrganizerEntityById(Long id);
    ApplicationResponseDTO updateApplicationStatus(String action , Long applicationId);
}
