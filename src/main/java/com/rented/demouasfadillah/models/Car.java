package com.rented.demouasfadillah.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Car {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCar;

    @Column(length = 64)
    private String image;
    private String brand;
    private String typeCar;
    private Integer productionYear;
    private Long price;
    private Integer seats;
    private Integer carTrunk;
    private Integer stock;
    private String driver;

    @OneToMany(mappedBy = "car")
    private List<Rented> renteds;

    @OneToMany(mappedBy = "car")
    private List<Review> reviews;
   
}