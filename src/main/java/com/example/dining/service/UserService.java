package com.example.dining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dining.model.User;
import com.example.dining.repository.UserRepository;
import com.example.dining.exception.ResourceNotFoundException;

@Service
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
  }

  public User findUserByUserName(String name) {
    return userRepository.findUserByUserName(name)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with name " + name));
  }

  public User createNewUser(User newUser) {
    if (userRepository.existsByUserName(newUser.getUserName())) {
      throw new IllegalArgumentException("Username already exists");
    }
    return userRepository.save(newUser);
  }

  public User updateUser(Long id, User updatedUser) {
    return userRepository.findById(id).map(user -> {

      user.setUserName(updatedUser.getUserName());
      user.setCity(updatedUser.getCity());
      user.setState(updatedUser.getState());
      user.setZipcode(updatedUser.getZipcode());
      user.setPeanutAllergy(updatedUser.isPeanutAllergy());
      user.setEggAllergy(updatedUser.isEggAllergy());
      user.setDairyAllergy(updatedUser.isDairyAllergy());

      return userRepository.save(user);
    }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
  }

}
