package com.example.dining.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dining.model.Review;
import com.example.dining.model.ReviewStatus;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByReviewStatus(ReviewStatus status);
  List<Review> findByRestaurantIdAndReviewStatus(Long restaurantId, ReviewStatus reviewStatus);
}
