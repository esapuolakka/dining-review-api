package com.example.dining.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.dining.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
  Optional<User> findUserByUserName(String name);
  boolean existsByUserName(String username);
}
