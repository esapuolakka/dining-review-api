package com.example.dining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.dining.repository.RestaurantRepository;
import com.example.dining.repository.ReviewRepository;
import com.example.dining.model.Review;
import com.example.dining.model.ReviewStatus;
import com.example.dining.model.Restaurant;

@Service
public class RestaurantService {
  
  @Autowired
  private final RestaurantRepository restaurantRepository;

  @Autowired
  private final ReviewRepository reviewRepository;

  public RestaurantService(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository) {
    this.restaurantRepository = restaurantRepository;
    this.reviewRepository = reviewRepository;
  }

  public Restaurant getRestaurantById(Long restaurantId) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId)
      .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));
    return restaurant;
  }


  public Restaurant createNewRestaurant(Restaurant restaurant) {
    if (restaurant.getName() == null || restaurant.getName().isEmpty()) {
      throw new IllegalArgumentException("Restaurant name cannot be empty");
    }
    if (restaurant.getAddress() == null || restaurant.getAddress().isEmpty()) {
      throw new IllegalArgumentException("Restaurant address cannot be empty");
    }
    if (restaurant.getZip() == null || restaurant.getZip().isEmpty()) {
      throw new IllegalArgumentException("Restaurant zip code cannot be empty");
    }
    if (restaurant.getCountry() == null || restaurant.getCountry().isEmpty()) {
      throw new IllegalArgumentException("Restaurant country cannot be empty");
    }

    Optional<Restaurant> restaurantOptional = restaurantRepository.findByName(restaurant.getName());
    if (restaurantOptional.isPresent()) {
      throw new IllegalArgumentException("Restaurant name already exists");
    }
    return restaurantRepository.save(restaurant);
  }


  public void calculateAndUpdateRestaurantScore(Long restaurantId) {
    List<Review> approvedReviews = reviewRepository.findByRestaurantIdAndReviewStatus(restaurantId, ReviewStatus.APPROVED);

    if (approvedReviews.isEmpty()) {
      return;
    }

    double totalPeanutScore = 0.0;
    double totalEggScore = 0.0;
    double totalDairyScore = 0.0;

    for (Review review : approvedReviews) {
      totalPeanutScore += (review.getPeanutScore() != null ? review.getPeanutScore() : 0);
      totalEggScore += (review.getEggScore() != null ? review.getEggScore() : 0);
      totalDairyScore += (review.getDairyScore() != null ? review.getDairyScore() : 0);
    }

    double avgPeanutScore = totalPeanutScore / approvedReviews.size();
    double avgEggScore = totalEggScore / approvedReviews.size();
    double avgDairyScore = totalDairyScore / approvedReviews.size();
    double overallScore = (avgPeanutScore + avgEggScore + avgDairyScore) / 3; 

    Restaurant restaurant = restaurantRepository.findById(restaurantId)
      .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

    restaurant.setPeanutScore(avgPeanutScore);
    restaurant.setEggScore(avgEggScore);
    restaurant.setDairyScore(avgDairyScore);
    restaurant.setOverallScore(overallScore);

    restaurantRepository.save(restaurant);
  }

  public List<Restaurant> searchRestaurants(String name, String zip, String state, String country) {
    return restaurantRepository.findByCriteria(name, zip, state, country);
  }

}
