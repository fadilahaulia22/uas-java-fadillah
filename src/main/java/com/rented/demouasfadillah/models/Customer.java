package com.rented.demouasfadillah.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idCustomer;
    private String name;
    private String address;
    private String phone;
    private String email;
    
    @OneToMany(mappedBy = "customer")
    private List<Rented> renteds;

    @OneToMany(mappedBy = "customer")
    private List<Payment> payments;

     @OneToMany(mappedBy = "customer")
    private List<Review> reviews;
}
