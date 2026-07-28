package com.example.service;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.model.api.CarUpdateDTO;
import com.example.model.db.Car;
import com.example.repository.CarRepository;

import jakarta.transaction.Transactional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final int maxSpaces;

    public CarService(CarRepository carRepository, @Value("${parking.spaces}") int maxSpaces) {
        this.carRepository = carRepository;
        this.maxSpaces = maxSpaces;
    }

    public Collection<Car> getAllCars() {
        return (Collection<Car>) carRepository.findAll();
    }


    public Car getCarByNumber(String carNumber) {
        return carRepository.findById(carNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
    }

    @Transactional
    public Car addCar(Car car) {
        Set<Integer> occupiedSpots = carRepository.findAllOccupiedSpots();

        if (carRepository.count() == maxSpaces) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parking is full");
        }

        if (carRepository.existsById(car.getCarNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already parked");
        }

        int assignedSpot = 1;

        while (occupiedSpots.contains(assignedSpot)) {
            assignedSpot++;
        }

        car.setParkingSpot(assignedSpot);

        return carRepository.save(car);
    }


    @Transactional
    public Car updateCarPaidStatus(String carNumber, CarUpdateDTO dto) {
        Car car = getCarByNumber(carNumber);

        car.setPaid(dto.paid());
        return carRepository.save(car);
    }
    
    @Transactional
    public Car deleteCar(String carNumber) {
        Car car = getCarByNumber(carNumber);

        if (!car.isPaid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unpaid car cannot be removed");
        }

        carRepository.delete(car);
        return car;
    }
}
