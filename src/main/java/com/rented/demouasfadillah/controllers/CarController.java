package com.rented.demouasfadillah.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rented.demouasfadillah.models.Car;
import com.rented.demouasfadillah.repositories.CarRepository;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/search-car")
    public String searchByName(@RequestParam(name = "search") String brand, Model model) {
        List<Car> cars = carRepository.findByBrandContainingIgnoreCase(brand);
        model.addAttribute("cars", cars);
        return "show-car";
    }

    @GetMapping("/sort-by-brand-asc")
    public String sortingAsc(Model model) {
        model.addAttribute("cars", carRepository.findAllByOrderByBrandAsc());
        return "show-car";
    }

    @GetMapping("/sort-by-brand-desc")
    public String sortingDesc(Model model) {
        model.addAttribute("cars", carRepository.findAllByOrderByBrandDesc());
        return "show-car";
    }

     // ============================================= //
    @GetMapping("/list-car")
    public String listCar(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "show-car";
    }

    @GetMapping("/add-car")
    public String addCar(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "add-car";
    }

    @PostMapping("/add-save-car")
    public String saveCar(
            @RequestParam("brand") String brand,
            @RequestParam("typeCar") String typeCar,
            @RequestParam("productionYear") Integer productionYear,
            @RequestParam("price") Long price,
            @RequestParam("seats") Integer seats,
            @RequestParam("carTrunk") Integer carTrunk,
            @RequestParam("stock") Integer stock,
            @RequestParam("driver") String driver,
            @RequestParam("image") MultipartFile multipartFile,
            Model model) {

        try {
            Car car = new Car();
            Path targetPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static",
                    multipartFile.getOriginalFilename());
            multipartFile.transferTo(targetPath.toFile());
            String url = "http://localhost:8080/" + multipartFile.getOriginalFilename();

            car.setBrand(brand);
            car.setTypeCar(typeCar);
            car.setProductionYear(productionYear);
            car.setPrice(price);
            car.setSeats(seats);
            car.setCarTrunk(carTrunk);
            car.setStock(stock);
            car.setDriver(driver);
            car.setImage(url);

            carRepository.save(car);
        } catch (IOException e) {
            e.printStackTrace();
            // return "redirect:/error";
        }
        return "redirect:/list-car";
    }

    @GetMapping("/edit-car/{idCar}")
    public String editCar(@PathVariable(value = "idCar") Integer idCar, Model model) {
        Car car = carRepository.getReferenceById(idCar);
        model.addAttribute("car", car);
        return "update-car";
    }
    @GetMapping("/delete-car/{idCar}")
    public String deleteCar(@PathVariable(value = "idCar") Integer idCar) {
        carRepository.deleteById(idCar);
        return "redirect:/list-car";
    }

    //booking
    @GetMapping("/detail-booking/{idCar}")
        public String detailBook(@PathVariable Integer idCar, Model model) {
        Car car = carRepository.findById(idCar).orElseThrow();
        model.addAttribute("car", car);
        return "detail-booking";
}

//DAFTAR MOBIL
    @GetMapping("/daftar-mobil")
    public String dataCar(Model model){
        model.addAttribute("car", carRepository.findAll());
        return "daftar-mobil";
    }
}
