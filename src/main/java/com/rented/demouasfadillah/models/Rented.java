package com.rented.demouasfadillah.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Rented {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRented;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "idCustomer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "idCar")
    private Car car;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rentalDate", nullable = false)
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date rentalDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "returnDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date returnDate;

    private String paymentStatus;
    private long tCost;
}
