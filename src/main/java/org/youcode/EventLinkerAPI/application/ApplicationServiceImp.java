package org.youcode.EventLinkerAPI.application;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.announcement.Announcement;
import org.youcode.EventLinkerAPI.announcement.enums.AnnouncementStatus;
import org.youcode.EventLinkerAPI.announcement.interfaces.AnnouncementService;
import org.youcode.EventLinkerAPI.application.DTOs.ApplicationResponseDTO;
import org.youcode.EventLinkerAPI.application.DTOs.CreateApplicationDTO;
import org.youcode.EventLinkerAPI.application.DTOs.UpdateApplicationDTO;
import org.youcode.EventLinkerAPI.application.enums.ApplicationStatus;
import org.youcode.EventLinkerAPI.application.interfaces.ApplicationService;
import org.youcode.EventLinkerAPI.application.mapper.ApplicationMapper;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.exceptions.UnacceptedAnnouncementStatusException;
import org.youcode.EventLinkerAPI.exceptions.UnpayableApplicationStatusException;
import org.youcode.EventLinkerAPI.worker.Worker;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ApplicationServiceImp implements ApplicationService {
    private final AnnouncementService announcementService;
    private final ApplicationDAO applicationDAO;
    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationResponseDTO saveApplication(CreateApplicationDTO data) {
        Announcement existingAnnouncement = announcementService.getAnnouncementEntityById(data.announcementId());
        assertAnnouncementIsPending(existingAnnouncement);
        Worker applicant = (Worker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Application applicationToCreate = applicationMapper.toEntity(data);
        applicationToCreate.setAnnouncement(existingAnnouncement);
        applicationToCreate.setApplicant(applicant);
        applicationToCreate.setCreatedAt(LocalDateTime.now());
        applicationToCreate.setStatus(ApplicationStatus.PENDING);
        Application createdApplication = applicationDAO.save(applicationToCreate);
        return applicationMapper.toResponseDTO(createdApplication);
    }

    @Override
    public ApplicationResponseDTO updateApplication(UpdateApplicationDTO data, Long id) {
        Application existingApplication = getApplicationEntityById(id);
        assertIsWorkerApplication(existingApplication , "You can Only update ur owm applications !");
        existingApplication.setLetter(data.letter());
        existingApplication.setPrice(3000);
        Application updatedApplication = applicationDAO.save(existingApplication);
        return applicationMapper.toResponseDTO(updatedApplication);
    }

    @Override
    public Page<ApplicationResponseDTO> getAllApplications(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page , size);
        Worker applicant = (Worker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Application> applications = applicationDAO.findAllByApplicant_Id(pageRequest , applicant.getId());
        return applications.map(applicationMapper::toResponseDTO);
    }

    @Override
    public ApplicationResponseDTO getApplicationById(Long id) {
        Application existingApplication = getApplicationEntityById(id);
        assertIsWorkerApplication(existingApplication , "You can Only see ur own applications !");
        return applicationMapper.toResponseDTO(existingApplication);
    }


    @Transactional
    @Override
    public ApplicationResponseDTO deleteApplication(Long id) {
        Application existingApplication = getApplicationEntityById(id);
        assertIsWorkerApplication(existingApplication , "You can Only delete ur own applications !");
        applicationDAO.delete(existingApplication);
        return applicationMapper.toResponseDTO(existingApplication);
    }

    @Override
    public Application getApplicationEntityById(Long id){
        return applicationDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Application Found with given ID ! "));
    }

    @Override
    public void verifyPayabilityOfApplication(Application application) {
        if (!application.getStatus().equals(ApplicationStatus.CONFIRMED)){
            throw new UnpayableApplicationStatusException("You can only pay CONFIRMED applications !");
        }
    }

    @Override
    public ApplicationResponseDTO getApplicationWithUpdatedStatus(Application application) {
        Application updatedApplication = applicationDAO.save(application);
        return applicationMapper.toResponseDTO(updatedApplication);
    }

    private void assertAnnouncementIsPending(Announcement announcement){
        if (!announcement.getStatus().equals(AnnouncementStatus.ACTIVE)){
            throw new UnacceptedAnnouncementStatusException("You can only apply for announcements with status 'Active' !");
        }
    }

    private void assertIsWorkerApplication(Application application , String message){
        Worker applicant = (Worker) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!application.getApplicant().getId().equals(applicant.getId())){
            throw new AccessDeniedException(message);
        }
    }

}
