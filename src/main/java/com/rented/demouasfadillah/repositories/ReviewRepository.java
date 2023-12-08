package com.rented.demouasfadillah.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rented.demouasfadillah.models.Review;


public interface ReviewRepository extends JpaRepository<Review,Integer> {
    
}
