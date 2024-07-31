package com.example.dining.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dining.model.Restaurant;
import com.example.dining.model.Review;
import com.example.dining.model.User;
import com.example.dining.service.AdminService;
import com.example.dining.model.ReviewStatus;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired
  private final AdminService adminService;

  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  @GetMapping("/home")
  public String home() {
    return "admin home";
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = adminService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/reviews")
  public ResponseEntity<List<Review>> getAllReviews() {
    List<Review> reviews = adminService.getAllReviews();
    return new ResponseEntity<>(reviews, HttpStatus.OK);
  }

  @GetMapping("/restaurants")
  public ResponseEntity<List<Restaurant>> getAllRestaurants() {
    List<Restaurant> restaurants = adminService.getAllRestaurants();
    return new ResponseEntity<>(restaurants, HttpStatus.OK);
  }

  @GetMapping("/reviews/status/{status}")
  public ResponseEntity<List<Review>> getAllReviewsWithStatus(@PathVariable("status") ReviewStatus status) {
    List<Review> reviews = adminService.getReviewsByStatus(status);
    return new ResponseEntity<>(reviews, HttpStatus.OK);
  }

  @GetMapping("/reviews/{id}")
  public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
    Review review = adminService.getReviewById(id);
    return new ResponseEntity<>(review, HttpStatus.OK);
  }

  @PutMapping("/reviews/{id}/update_status")
  public ResponseEntity<Review> updateReviewStatus(@PathVariable Long id, @RequestBody String status) {
    try {
      ReviewStatus reviewStatus = ReviewStatus.valueOf(status.toUpperCase());
      Review updatedReview = adminService.updateReviewStatus(id, reviewStatus);
      return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
