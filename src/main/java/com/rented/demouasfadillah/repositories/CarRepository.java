package com.rented.demouasfadillah.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rented.demouasfadillah.models.Car;


public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByBrandContainingIgnoreCase(String brand);

    List<Car> findAllByOrderByBrandAsc();

    List<Car> findAllByOrderByBrandDesc();
}
