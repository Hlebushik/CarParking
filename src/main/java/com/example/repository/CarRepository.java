package com.example.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.model.db.Car;

public interface CarRepository extends CrudRepository<Car, String>{
    @Query("SELECT c.parkingSpot FROM Car c")
    Set<Integer> findAllOccupiedSpots();
}
