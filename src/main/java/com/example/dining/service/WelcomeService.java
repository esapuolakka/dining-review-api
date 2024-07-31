package com.example.dining.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dining.model.User;
import com.example.dining.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WelcomeService {
  
  @Autowired
  private final UserRepository userRepository;

  public WelcomeService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  public User loginUser(User userToLogin) {
    Optional<User> userOptional = userRepository.findUserByUserName(userToLogin.getUserName());

    if (userOptional.isPresent()) {
        return userOptional.get();
    } else {
        return new User();
    }
}
  public User createUser(User newUser) {
    if (newUser.getUserName() == null || newUser.getUserName().isEmpty()) {
      return null;
    }
    if (userRepository.existsByUserName(newUser.getUserName())) {
      return null;
    }
    User createdUser = userRepository.save(newUser);
    return createdUser;
  }
}
