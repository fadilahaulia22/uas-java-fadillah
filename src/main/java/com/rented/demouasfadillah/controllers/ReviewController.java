package com.rented.demouasfadillah.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rented.demouasfadillah.models.Review;
import com.rented.demouasfadillah.repositories.CarRepository;
import com.rented.demouasfadillah.repositories.CustomerRepository;
import com.rented.demouasfadillah.repositories.ReviewRepository;


@Controller
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository2;

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/review")
    public String showReview(Model model) {
        model.addAttribute("review", reviewRepository.findAll());
        return "show-review";
    }

    @GetMapping("/add-review")
    public String addReview(Model model) {
        Review review = new Review();
        model.addAttribute("review", review);
        model.addAttribute("customer", customerRepository2.findAll());
        model.addAttribute("car", carRepository.findAll());
        return "add-review";
    }

    @PostMapping("/save-review")
    public String saveReview(@ModelAttribute("review") Review review ) {
        reviewRepository.save(review);
        return "redirect:/review";
    }

    @GetMapping("/up-review/{idReview}")
    public String updateReview(@PathVariable(value = "idReview") Integer idReview,Model model){
        Review review = reviewRepository.getReferenceById(idReview);
        // if (review != null) {
            model.addAttribute("review", review);
            model.addAttribute("customer", customerRepository2.findAll());
            model.addAttribute("car", carRepository.findAll());
            return "update-review";
        // }else{
        //     return "redirect:/review";
        // }
    }

    @GetMapping("/del-review/{idReview}")
    public String deleteReview(@PathVariable(value = "idReview") Integer idReview){
        reviewRepository.deleteById(idReview);
        return "redirect:/review";
    }  
}

