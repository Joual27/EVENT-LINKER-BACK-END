package org.youcode.EventLinkerAPI.organizer;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.application.DTOs.ApplicationResponseDTO;
import org.youcode.EventLinkerAPI.application.enums.ApplicationStatus;
import org.youcode.EventLinkerAPI.application.interfaces.ApplicationService;
import org.youcode.EventLinkerAPI.applicationEvents.events.ApplicationConfirmedEvent;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.exceptions.UnsupportedActionException;
import org.youcode.EventLinkerAPI.organizer.interfaces.OrganizerService;

@AllArgsConstructor
@Service
public class OrganizerServiceImp implements OrganizerService {
    private final OrganizerDAO organizerDAO;
    private final ApplicationService applicationService;
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public Organizer getOrganizerEntityById(Long id) {
        return organizerDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Organizer found with given ID"));
    }

    @Override
    public ApplicationResponseDTO updateApplicationStatus(String action, Long applicationId) {
        Application existingApplication = applicationService.getApplicationEntityById(applicationId);
        Application updatedApplication = updateApplicationStatusBasedOnAction(existingApplication , action);
        return applicationService.getApplicationWithUpdatedStatus(updatedApplication);
    }

    private Application updateApplicationStatusBasedOnAction(Application application , String action){
        switch (action){
            case "accept" :
                if (!application.getStatus().equals(ApplicationStatus.PENDING)){
                    throw new UnsupportedActionException("You can only mark PENDING applications as ACCEPTED !");
                }
                application.setStatus(ApplicationStatus.ACCEPTED);
                return application;
            case "confirm":
                if (!application.getStatus().equals(ApplicationStatus.ACCEPTED)){
                    throw new UnsupportedActionException("You can only mark Accepted applications as completed !");
                }
                application.setStatus(ApplicationStatus.CONFIRMED);
                applicationEventPublisher.publishEvent(new ApplicationConfirmedEvent(this , application , application.getPayment().getAmount()));
                return application;
            case "reject" :
                if (!application.getStatus().equals(ApplicationStatus.PENDING)){
                    throw new UnsupportedActionException("You can only mark PENDING applications as REJECTED !");
                }
                application.setStatus(ApplicationStatus.REFUSED);
                return application;
            default:
                throw new UnsupportedActionException("The given action in URL ain't supported !");
        }
    }

}
