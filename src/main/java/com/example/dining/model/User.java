package com.example.dining.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name="app_user")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="uid")
  private Long id;

  @Column(name="username", unique=true, nullable=false)
  private String userName;

  @Column(name="city")
  private String city;

  @Column(name="state")
  private String state;

  @Column(name="zip")
  private String zipcode;

  @Column(name="peanut_allergy")
  private boolean peanutAllergy;

  @Column(name="egg_allergy")
  private boolean eggAllergy;

  @Column(name="dairy_allergy")
  private boolean dairyAllergy;

  @OneToMany(mappedBy="user")
  @JsonIgnore
  private List<Review> userReviews;
}
