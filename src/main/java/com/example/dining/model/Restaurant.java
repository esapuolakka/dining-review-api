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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Data
@Entity
@Table(name="restaurant")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Restaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="id")
  private Long id;

  @Column(name="restaurant_name")
  private String name;

  @Column(name="address")
  private String address;

  @Column(name="zip")
  private String zip;

  @Column(name="state")
  private String state;

  @Column(name="country")
  private String country;

  @Column(name="overall_score")
  private double overallScore;

  @Column(name="restaurant_peanut_score")
  private double peanutScore;

  @Column(name="restaurant_egg_score")
  private double eggScore;

  @Column(name="restaurant_dairy_score")
  private double dairyScore;

  @OneToMany(mappedBy="restaurant")
  private List<Review> restaurantReviews;
}
