package com.rented.demouasfadillah.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BerandaController {

    @GetMapping("/beranda-awal")
    public String beranda(Model model){
        return "beranda";
    }


    // CONTACT
    @GetMapping("/contact")
    public String contact(Model model){
        return "contact-beranda";
    }

    @GetMapping("/pembayaran")
    public String pembayaran(){
        return "pembayaran";
    }

    @GetMapping("/end")
    public String end(){
        return "end";
    }
}
