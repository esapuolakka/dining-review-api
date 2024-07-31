package com.example.dining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.dining.model.User;
import com.example.dining.service.WelcomeService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class WelcomeController {

  @Autowired
  private final WelcomeService welcomeService;

  public WelcomeController(WelcomeService welcomeService) {
    this.welcomeService = welcomeService;
  }
  
  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @PostMapping("/login")
  public ResponseEntity<Void> login(@RequestBody User loginRequest, HttpServletResponse response) {
    User loggedInUser = welcomeService.loginUser(loginRequest);

    if (loggedInUser.getId() != null) {
        response.setHeader("Location", "/user");
        return new ResponseEntity<>(HttpStatus.FOUND);
    } else {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }

  @GetMapping("/register")
  public String register() {
    return "this is the register page";
  }

  @PostMapping("/register")
  public ResponseEntity<User> createUser(@RequestBody User newUser) {

    User createdUser = welcomeService.createUser(newUser);
    if (createdUser == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or existing username");
    }
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }
}
