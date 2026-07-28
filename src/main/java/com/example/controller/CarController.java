package com.example.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import com.example.model.api.CarUpdateDTO;
import com.example.model.db.Car;
import com.example.service.CarService;

@RequestMapping("/cars")
@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService)  {
        this.carService = carService;
    }

    @GetMapping
    public Collection<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{carNumber}")
    public Car getCar(@PathVariable("carNumber") String carNumber) {
        Car car = carService.getCarByNumber(carNumber);
        return car;

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Car createCar(@RequestBody Car car) {
        Car createdCar = carService.addCar(car);
        return createdCar;
    }

    @PutMapping("/{carNumber}")
    public Car updateCarPaidStatus(@PathVariable("carNumber") String carNumber, @RequestBody CarUpdateDTO dto) {
        Car updatedCar = carService.updateCarPaidStatus(carNumber, dto);
        return updatedCar;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{carNumber}")
    public void deleteCar(@PathVariable("carNumber") String carNumber) {
            carService.deleteCar(carNumber);
    }

}
