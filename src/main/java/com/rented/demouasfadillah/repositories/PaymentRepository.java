package com.rented.demouasfadillah.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rented.demouasfadillah.models.Payment;


public interface PaymentRepository extends JpaRepository<Payment,Integer>{
    
}
