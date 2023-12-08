package com.rented.demouasfadillah.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPayment;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "idCustomer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "method_id", referencedColumnName = "idMethod")
    private PaymentMethod paymentMethod;

}
