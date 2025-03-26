package org.youcode.EventLinkerAPI.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.youcode.EventLinkerAPI.application.enums.ApplicationStatus;
import org.youcode.EventLinkerAPI.event.mapper.EventMapper;
import org.youcode.EventLinkerAPI.exceptions.EntityNotFoundException;
import org.youcode.EventLinkerAPI.organizer.DTOs.OrganizerStatsResponseDTO;
import org.youcode.EventLinkerAPI.organizer.Organizer;
import org.youcode.EventLinkerAPI.review.Review;
import org.youcode.EventLinkerAPI.review.mapper.ReviewMapper;
import org.youcode.EventLinkerAPI.shared.utils.interfaces.FileUploadService;
import org.youcode.EventLinkerAPI.user.DTOs.UpdateProfileDTO;
import org.youcode.EventLinkerAPI.user.DTOs.UserResponseDTO;
import org.youcode.EventLinkerAPI.user.DTOs.UserStatsResponseDTO;
import org.youcode.EventLinkerAPI.user.interfaces.UserService;
import org.youcode.EventLinkerAPI.worker.DTOs.WorkerStatsResponseDTO;
import org.youcode.EventLinkerAPI.worker.Worker;



@AllArgsConstructor
@Service
    public class UserServiceImp implements UserService {
    private final UserDAO userDAO;
    private final ReviewMapper reviewMapper;
    private final EventMapper eventMapper;
    private final FileUploadService fileUploadService;


    @Override
    public User getUserEntityById(Long id){
        return userDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found with given ID !"));
    }

    @Override
    public UserResponseDTO getUserData(Long id) {
        User user = getUserEntityById(id);
        return UserResponseDTO.fromUser(user , reviewMapper , eventMapper);
    }

    @Override
    public UserStatsResponseDTO getUserStats(Long id) {
        User existingUser = getUserEntityById(id);
        if (existingUser.getUserRole().equals("ORGANIZER")) {
            return getOrganizerStats((Organizer) existingUser);
        } else{
            return getWorkerStats((Worker) existingUser);
        }
    }

    @Override
    public UserResponseDTO updateProfile(UpdateProfileDTO data) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (data.profileImg() != null && !data.profileImg().isEmpty()){
            String imgUrl = fileUploadService.uploadImage(data.profileImg());
            loggedInUser.setProfileImgUrl(imgUrl);
        }
        if (data.bio() != null && !data.bio().isEmpty()){
            loggedInUser.setBio(data.bio());
        }
        User updatedUser = userDAO.save(loggedInUser);
        return UserResponseDTO.fromUser(updatedUser , reviewMapper , eventMapper);
    }

    private OrganizerStatsResponseDTO getOrganizerStats(Organizer organizer) {
        int numberOfCreatedEvents = organizer.getEvents().size();
        int numberOfReviews = organizer.getReceivedReviews().size();
        double avgReview = getAvgReviews(organizer);
        return new OrganizerStatsResponseDTO(numberOfCreatedEvents, numberOfReviews, avgReview);
    }

    private WorkerStatsResponseDTO getWorkerStats(Worker worker) {
        int completedJobs = (int) worker.getApplications().stream()
                .filter(application -> application.getStatus() == ApplicationStatus.CONFIRMED)
                .count();
        int numberOfReviews = worker.getReceivedReviews().size();
        double avgReview = getAvgReviews(worker);
        return new WorkerStatsResponseDTO(completedJobs, numberOfReviews, avgReview);
    }

    private double getAvgReviews(User user){
        System.out.println("REVIEWS :" + user.getReceivedReviews());
        double sum = 0;
        for (Review r : user.getReceivedReviews()){
            sum += r.getRating();
        }
        if (sum > 0){
            return sum / user.getReceivedReviews().size();
        }
        return 0;
    }

}