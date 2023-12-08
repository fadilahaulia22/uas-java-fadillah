package com.rented.demouasfadillah.models;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReview;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "idCustomer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "idCar")
    private Car car;

    private int rating;
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reviewDate", nullable = false)
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date reviewDate;

}
