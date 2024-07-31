package com.example.dining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dining.model.User;
import com.example.dining.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> userProfile(@PathVariable Long id) {
    User user = userService.findUserById(id);
    return ResponseEntity.ok(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    User user = userService.updateUser(id, updatedUser);
    return ResponseEntity.ok(user);
  }

}
