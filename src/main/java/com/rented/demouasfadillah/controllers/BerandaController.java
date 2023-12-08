package com.rented.demouasfadillah.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BerandaController {

    //TAPILAN BERANDA AWAL(USER)
    @GetMapping("/beranda")
    public String beranda(Model model){
        return "beranda";
    }

    // ABOUT
    @GetMapping("/about")
    public String about() {
        return "redirect:/beranda#how"; // Redirect ke bagian dengan ID "how"
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
