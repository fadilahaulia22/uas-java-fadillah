package com.rented.demouasfadillah.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rented.demouasfadillah.models.Admin;
import com.rented.demouasfadillah.repositories.AdminRepository;
import com.rented.demouasfadillah.repositories.CarRepository;
import com.rented.demouasfadillah.repositories.CustomerRepository;
import com.rented.demouasfadillah.repositories.PMethodeRepository;
import com.rented.demouasfadillah.repositories.PaymentRepository;
import com.rented.demouasfadillah.repositories.RentalRepository;
import com.rented.demouasfadillah.repositories.ReviewRepository;
import com.rented.demouasfadillah.repositories.UserRepository;

@Controller
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PMethodeRepository pMethodeRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    // DAFTAR ADMIN
    @GetMapping("/list-admin")
    public String listAdmin(Model model) {
        model.addAttribute("admin", adminRepository.findAll());
        return "show-admin";
    }

    // REGISTER
    @GetMapping("/register")
    public String regisAdmin(Model model) {
        Admin admin = new Admin();
        model.addAttribute("login", admin);
        return "register-admin";
    }

    // SAVE ADMIN
    @PostMapping("/reg-admin")
    public String regAdmin(@ModelAttribute("login") Admin admin) {
        adminRepository.save(admin);
        return "redirect:/login-admin";
    }

    // EDIT ADMIN
    @GetMapping("/edit-admin/{id}")
    public String editAdmin(@PathVariable(value = "id") Integer id, Model model) {
        Admin admin = adminRepository.getReferenceById(id);
        model.addAttribute("admin", admin);
        return "update-admin";
    }

    // DELETE ADMIN
    @GetMapping("/delete-admin/{id}")
    public String deleteAdmin(@PathVariable(value = "id") Integer id) {
        adminRepository.deleteById(id);
        return "redirect:/list-admin";
    }

    // LOGIN
    @GetMapping("/login-admin")
    public String loginAdmin(Model model) {
        Admin admin = new Admin();
        model.addAttribute("login", admin);
        return "login-admin";
    }

    // PROSES PENCARIAN USERNAME & PASSWORD YANG SESUAI DI DATABASE
    @PostMapping("/post-login")
    public String loginAdmin(@ModelAttribute("login") Admin admin, RedirectAttributes attributes) {
        List<Admin> admins = adminRepository.findAll();
        boolean found = false;

        for (Admin a : admins) {
            if (admin.getUsername().equals(a.getUsername()) && admin.getPassword().equals(a.getPassword())) {
                found = true;
                return "redirect:/beranda-admin";
            }
        }

        if (!found) {
            attributes.addFlashAttribute("error", "Username atau password salah.");
        }
        // adminRepository.save(admin);
        return "redirect:/login-admin";
    }

    
    // BERANDA
    @GetMapping("/beranda-admin")
    public String home(Model model) {
        model.addAttribute("link1", "/list-car");
        model.addAttribute("link2", "/list-customer");
        model.addAttribute("link3", "/payment");
        model.addAttribute("link4", "/list-pMethode");
        model.addAttribute("link5", "/rented");
        model.addAttribute("link6", "/review");
        model.addAttribute("link7", "/list-admin");
        model.addAttribute("link8", "/users");

        long totalCars = carRepository.count();
        model.addAttribute("totalCars", totalCars);

        long totalCustomers = customerRepository.count();
        model.addAttribute("totalCustomers", totalCustomers);

        long totalPayments = paymentRepository.count();
        model.addAttribute("totalPayments", totalPayments);

        long totalPaymentMethodes = pMethodeRepository.count();
        model.addAttribute("totalPaymentMethodes", totalPaymentMethodes);

        long totalRenteds = rentalRepository.count();
        model.addAttribute("totalRenteds", totalRenteds);

        long totalReviews = reviewRepository.count();
        model.addAttribute("totalReviews", totalReviews);

        model.addAttribute("admin", adminRepository.getReferenceById(1));

        long totalUsers = userRepository.count();
        model.addAttribute("totalUsers", totalUsers);

        long totalAdmins = adminRepository.count();
        model.addAttribute("totalAdmins", totalAdmins);
        return "beranda-admin";
    }
}
