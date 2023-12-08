package com.rented.demouasfadillah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rented.demouasfadillah.models.Payment;
import com.rented.demouasfadillah.repositories.CustomerRepository;
import com.rented.demouasfadillah.repositories.PMethodeRepository;
import com.rented.demouasfadillah.repositories.PaymentRepository;


@Controller
public class PaymentController {
        @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PMethodeRepository pMethodeRepository;

    @GetMapping("/payment")
    public String showPayment(Model model) {
        model.addAttribute("payment", paymentRepository.findAll());
        return "show-payment";
    }

    @GetMapping("/add-payment")
    public String addPayment(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        model.addAttribute("customer", customerRepository.findAll());
        model.addAttribute("pMethod", pMethodeRepository.findAll());
        return "add-payment";
    }

    @PostMapping("/save-pay")
    public String savePayment(@ModelAttribute("payment") Payment payment ) {
        paymentRepository.save(payment);
        return "redirect:/payment";
    }

    @GetMapping("/up-payment/{idPayment}")
    public String updatePayment(@PathVariable(value = "idPayment") Integer idPayment,Model model){
        Payment payment = paymentRepository.getReferenceById(idPayment);
        // if (payment != null) {
            model.addAttribute("payment", payment);
            model.addAttribute("customer", customerRepository.findAll());
            model.addAttribute("pMethod", pMethodeRepository.findAll());
            return "update-payment";
        // }else{
        //     return "redirect:/payment";
        // }
    }

    @GetMapping("/del-payment/{idPayment}")
    public String deletePayment(@PathVariable(value = "idPayment") Integer idPayment){
        paymentRepository.deleteById(idPayment);
        return "redirect:/payment";
    }  
}

