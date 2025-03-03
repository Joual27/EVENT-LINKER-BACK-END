package org.youcode.EventLinkerAPI.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.user.User;

import java.util.Optional;

@Repository
public interface ReviewDAO extends JpaRepository<Review , Long> {
    Optional<Review> findByReviewerAndRevieweeAndApplication(User reviewer , User reviwee , Application application);

    int countByReviewee(User reviewee);

    @Query("SELECT COALESCE(AVG(r.rating) , 0) FROM Review r WHERE r.reviewee = :reviewee")
    double getAvgRatingByReviewee(@Param("reviewee") User reviewee);

    Page<Review> findByReviewee(Pageable pageable , User reviewee);
}
