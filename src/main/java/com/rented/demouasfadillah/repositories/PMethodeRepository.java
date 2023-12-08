package com.rented.demouasfadillah.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rented.demouasfadillah.models.PaymentMethod;


public interface PMethodeRepository extends JpaRepository<PaymentMethod,Integer>{
    
}
