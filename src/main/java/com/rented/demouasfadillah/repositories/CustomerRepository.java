package com.rented.demouasfadillah.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rented.demouasfadillah.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByNameContainingIgnoreCase(String name);

    List<Customer> findByEmailContainingIgnoreCase(String email);

    List<Customer> findAllByOrderByNameAsc();

    List<Customer> findAllByOrderByNameDesc();
    
}
