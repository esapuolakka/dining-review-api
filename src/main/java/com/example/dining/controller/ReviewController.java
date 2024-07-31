package com.example.dining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.dining.model.Review;
import com.example.dining.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

  @Autowired
  private final ReviewService reviewService;


  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }
  
  @PostMapping("/add")
  public ResponseEntity<Review> createReview(@RequestBody Review newReview) {
    
    Review createdReview = reviewService.createReview(newReview);
    if (createdReview == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create review. Please ensure all required fields are provided");
    }
    return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
  }
}
