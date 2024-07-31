package com.example.dining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dining.model.Review;
import com.example.dining.model.ReviewStatus;
import com.example.dining.repository.ReviewRepository;
import java.util.List;

@Service
public class ReviewService {
 
  @Autowired
  private final ReviewRepository reviewRepository;

  @Autowired
  private final RestaurantService restaurantService;

  public ReviewService(ReviewRepository reviewRepository, RestaurantService restaurantService) {
    this.reviewRepository = reviewRepository;
    this.restaurantService = restaurantService;
  }

  public Review createReview(Review newReview) {
    if (newReview.getPeanutScore() == null || newReview.getEggScore() == null || newReview.getDairyScore() == null) {
      return null;
    }
    if (newReview.getPeanutScore() < 1 || newReview.getPeanutScore() > 5 ||
        newReview.getEggScore() < 1 || newReview.getEggScore() > 5 ||
        newReview.getDairyScore() < 1 || newReview.getDairyScore() > 5) {
      return null;
    }
    restaurantService.calculateAndUpdateRestaurantScore(newReview.getRestaurant().getId());
    return reviewRepository.save(newReview);
  }

  public List<Review> getAllApprovedReviewsForRestaurant(Long restaurantId) {
    return reviewRepository.findByRestaurantIdAndReviewStatus(restaurantId, ReviewStatus.APPROVED);
  }
}
