package com.example.dining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.example.dining.model.Restaurant;
import com.example.dining.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

  @Autowired
  private final RestaurantService restaurantService;

  public RestaurantController(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @GetMapping("/")
  public String restaurant() {
    return "hello restaurant";
  }

  @GetMapping("/{id}")
  public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
    Restaurant restaurant = restaurantService.getRestaurantById(id);
    return ResponseEntity.ok(restaurant);
  }

  @PostMapping("/add")
  public ResponseEntity<Restaurant> createNewRestaurant(@RequestBody Restaurant restaurant) {
    Restaurant newRestaurant = restaurantService.createNewRestaurant(restaurant);
    return ResponseEntity.ok(newRestaurant);
  }

  @GetMapping("/search")
  public List<Restaurant> getSearchRestaurants(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String zip,
    @RequestParam(required = false) String state,
    @RequestParam(required = false) String country) {
      
      return restaurantService.searchRestaurants(name, zip, state, country);
  }
}
