package com.example.dining.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dining.exception.ResourceNotFoundException;
import com.example.dining.model.Restaurant;
import com.example.dining.model.Review;
import com.example.dining.model.ReviewStatus;
import com.example.dining.model.User;
import com.example.dining.repository.RestaurantRepository;
import com.example.dining.repository.ReviewRepository;
import com.example.dining.repository.UserRepository;

@Service
public class AdminService {

  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final ReviewRepository reviewRepository;
  @Autowired
  private final RestaurantRepository restaurantRepository;

  public AdminService(UserRepository userRepository, ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
    this.userRepository = userRepository;
    this.reviewRepository = reviewRepository;
    this.restaurantRepository = restaurantRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public List<Review> getAllReviews() {
    return reviewRepository.findAll();
  }

  public List<Restaurant> getAllRestaurants() {
    return restaurantRepository.findAll();
  }

  public List<Review> getReviewsByStatus(ReviewStatus status) {
    return reviewRepository.findByReviewStatus(status);
  }

  public Review getReviewById(Long id) {
    return reviewRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
  }

  public Review updateReviewStatus(Long id, ReviewStatus status) {
    Review review = reviewRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + id));
    review.setReviewStatus(status);
    return reviewRepository.save(review);
  }

  public List<Review> getApprovedReviewsForRestaurant(Long restaurantId) {
    return reviewRepository.findByRestaurantIdAndReviewStatus(restaurantId, ReviewStatus.APPROVED);
  }
}

