package com.example.dining.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.dining.model.Restaurant;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
  Optional<Restaurant> findByName(String name);

  @Query("SELECT r FROM Restaurant r WHERE " +
    "(:name IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
    "(:zip IS NULL OR LOWER(r.zip) LIKE LOWER(CONCAT('%', :zip, '%'))) AND " +
    "(:state IS NULL OR LOWER(r.state) LIKE LOWER(CONCAT('%', :state, '%'))) AND " +
    "(:country IS NULL OR LOWER(r.country) LIKE LOWER(CONCAT('%', :country, '%')))")
  List<Restaurant> findByCriteria(@Param("name") String name,
                                  @Param("zip") String zip,
                                  @Param("state") String state,
                                  @Param("country") String country);
}
