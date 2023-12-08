package com.rented.demouasfadillah.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rented.demouasfadillah.models.User;
import com.rented.demouasfadillah.repositories.UserRepository;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

// REGISTER USER
    @GetMapping("/register-user")
    public String getRegPage(Model model) {
        User user = new User();
        model.addAttribute("login", user);
        return "register-user";
    }

    @PostMapping("/reg-user")
    public String saveUser(@ModelAttribute("login") User user) {
        userRepository.save(user);
        // model.addAttribute("message", "Submitted Successfully");
        return "redirect:/login-user";
    }
// LOGIN USER
    @GetMapping("/login-user")
    public String loginUser(Model model){
        User user=new User();
        model.addAttribute("login", user);
        return "login-user";
    }

    @PostMapping("/post-login-user")
    public String loginUser(@ModelAttribute("login") User user, RedirectAttributes attributes) {
        List<User> users = userRepository.findAll();
        boolean found = false;

        for (User a : users) {
            if (user.getUsername().equals(a.getUsername()) && user.getPassword().equals(a.getPassword())) {
                found = true;
                return "redirect:/daftar-mobil";
            }
        }

        if (!found) {
            attributes.addFlashAttribute("error", "Username atau password salah.");
        }
        // adminRepository.save(admin);
        return "redirect:/login-user";
    }
    
    @GetMapping("/users")
    public String userPage(Model model) {
        List<User> listOfUsers = userRepository.findAll();
        model.addAttribute("users", listOfUsers);
        return "show-user";
    }
}
