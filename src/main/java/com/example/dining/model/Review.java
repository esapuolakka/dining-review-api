package com.example.dining.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="review")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Review {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private Long id;

  @ManyToOne
  @JoinColumn(name="restaurant_id", nullable=false)
  private Restaurant restaurant;

  @ManyToOne
  @JoinColumn(name="user_id", nullable=false)
  private User user;

  @Column(name="description")
  private String description;

  @Column(name="peanut_score")
  private Integer peanutScore;

  @Column(name="egg_score")
  private Integer eggScore;

  @Column(name="dairy_score")
  private Integer dairyScore;

  @Enumerated(EnumType.STRING)
  @Column(name="review_status")
  private ReviewStatus reviewStatus;
}
