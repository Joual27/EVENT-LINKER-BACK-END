package org.youcode.EventLinkerAPI.application.interfaces;

import org.springframework.data.domain.Page;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.application.DTOs.ApplicationResponseDTO;
import org.youcode.EventLinkerAPI.application.DTOs.CreateApplicationDTO;
import org.youcode.EventLinkerAPI.application.DTOs.UpdateApplicationDTO;
import org.youcode.EventLinkerAPI.shared.utils.DTOs.PaginationDTO;

import java.util.List;

public interface ApplicationService {
    ApplicationResponseDTO saveApplication(CreateApplicationDTO data);
    ApplicationResponseDTO updateApplication(UpdateApplicationDTO data , Long id);
    Page<ApplicationResponseDTO> getAllApplications(int page , int size);
    ApplicationResponseDTO getApplicationById(Long id);
    ApplicationResponseDTO deleteApplication(Long id);
    Application getApplicationEntityById(Long id);
    void verifyPayabilityOfApplication(Application application);
    ApplicationResponseDTO getApplicationWithUpdatedStatus(Application application);
    PaginationDTO<List<ApplicationResponseDTO>> getAnnouncementApplications(int page, int size ,Long announcementId);
}
