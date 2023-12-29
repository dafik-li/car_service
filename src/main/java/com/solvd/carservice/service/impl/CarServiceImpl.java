package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.persistence.CarRepository;
import com.solvd.carservice.persistence.DAOimpl.CarRepositoryImpl;
import com.solvd.carservice.service.CarService;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl() {
        this.carRepository = new CarRepositoryImpl();
    }
    @Override
    public Car add(Car car) {
        car.setId(null);
        carRepository.create(car);
        return car;
    }
    @Override
    public List<Car> retrieveAll() {
        return carRepository.getAll();
    }
    @Override
    public Optional<Car> retrieveById(Long id) {
        return carRepository.getById(id);
    }
    @Override
    public void change(Car car, String field) {
        carRepository.update(car, field);
    }
    @Override
    public void removeById(Long id) {
        carRepository.deleteById(id);
    }
}
