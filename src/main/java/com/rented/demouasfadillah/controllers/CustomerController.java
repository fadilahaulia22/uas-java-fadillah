package com.rented.demouasfadillah.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rented.demouasfadillah.models.Customer;
import com.rented.demouasfadillah.repositories.CustomerRepository;


@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/search-customer")
    public String searchByName(@RequestParam(name = "search") String name, Model model) {
        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(name);
        model.addAttribute("customer", customers);
        return "show-customer";
    }

    @GetMapping("/search-email")
    public String searchByEmail(@RequestParam(name = "searching") String email, Model model) {
        List<Customer> customers = customerRepository.findByEmailContainingIgnoreCase(email);
        model.addAttribute("customer", customers);
        return "show-customer";
    }

    @GetMapping("/sort-by-name-asc")
    public String sortingAsc(Model model) {
        model.addAttribute("customer", customerRepository.findAllByOrderByNameAsc());
        return "show-customer";
    }

    @GetMapping("/sort-by-name-desc")
    public String sortingDesc(Model model) {
        model.addAttribute("customer", customerRepository.findAllByOrderByNameDesc());
        return "show-customer";
    }

// ================================
    @GetMapping("/list-customer")
    public String listCustomer(Model model) {
        model.addAttribute("customer", customerRepository.findAll());
        return "show-customer";
    }

    @GetMapping("/add-customer")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add-customer";
    }

    @PostMapping("/add-save-customer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerRepository.save(customer);
        return "redirect:/list-customer";
    }

    @GetMapping("/edit-customer/{idCustomer}")
    public String editCustomer(@PathVariable(value = "idCustomer") Integer idCustomer, Model model) {
        Customer customer = customerRepository.getReferenceById(idCustomer);
        model.addAttribute("customer", customer);
        return "update-customer";
    }

    @GetMapping("/delete-customer/{idCustomer}")
    public String deleteCustomer(@PathVariable(value = "idCustomer") Integer idCustomer) {
        customerRepository.deleteById(idCustomer);
        return "redirect:/list-customer";
    }


// data customer user
    @PostMapping("/add-save-data-customer")
    public String saveDataCustomer(@ModelAttribute("customer") Customer customer) {
        customer.setIdCustomer(null);
        customerRepository.save(customer);
        return "redirect:/list-data-customer";
    }

// 
@GetMapping("/data-customer")
public String addData(Model model) {
    Customer customer = new Customer();
    model.addAttribute("customer", customer);
    return "data-customer"; // Update the template name if necessary
}
@PostMapping("/save-data-customer")
public String addSaveDataCustomer(@ModelAttribute("customer") Customer customer,Model model) {
    customerRepository.save(customer);
    model.addAttribute("message", "Data saved successfully!");
    return "redirect:/data-customer";
}


// pergi ke rented
@PostMapping("/save-data-customer-user")
public String SaveDataCustomer(@ModelAttribute("customer") Customer customer,Model model) {
    customerRepository.save(customer);
    model.addAttribute("message", "Data saved successfully!");
    return "redirect:/detail-rented";
}
}
