package com.rented.demouasfadillah.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rented.demouasfadillah.models.Rented;


public interface RentalRepository extends JpaRepository<Rented,Integer>{
  
}
