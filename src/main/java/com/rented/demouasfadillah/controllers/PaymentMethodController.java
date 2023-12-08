package com.rented.demouasfadillah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rented.demouasfadillah.models.PaymentMethod;
import com.rented.demouasfadillah.repositories.PMethodeRepository;


@Controller
public class PaymentMethodController {
    @Autowired 
    private PMethodeRepository pMethodeRepository;

@GetMapping("/list-pMethode")
    public String listPMethode(Model model) {
        model.addAttribute("pMethode", pMethodeRepository.findAll());
        return "show-pMethode";
    }

    @GetMapping("/add-pMethode")
    public String addpMethode(Model model) {
        PaymentMethod pMethode = new PaymentMethod();
        model.addAttribute("pMethode", pMethode);
        return "add-pMethode";
    }

    @PostMapping("/add-save-pMethode")
    public String savepMethode(@ModelAttribute("pMethode") PaymentMethod pMethode) {
       pMethodeRepository.save(pMethode);
        return "redirect:/list-pMethode";
    }
    @GetMapping("/edit-pMethode/{idMethod}")
    public String editPMethod(@PathVariable(value = "idMethod") Integer idMethod, Model model) {
        PaymentMethod paymentMethod = pMethodeRepository.getReferenceById(idMethod);
        model.addAttribute("pMethode", paymentMethod);
        return "update-pMethod";
    }

    @GetMapping("/delete-pMethode/{idMethod}")
    public String deletepMethod(@PathVariable(value = "idMethod") Integer idMethod) {
        pMethodeRepository.deleteById(idMethod);
        return "redirect:/list-pMethode";
    }


}
