package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.persistence.CarRepository;
import com.solvd.carservice.service.CarService;
import com.solvd.carservice.domain.controller.SwitcherRepository;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private static final SwitcherRepository switcherRepository = SwitcherRepository.getInstance();

    public CarServiceImpl() {
        this.carRepository = switcherRepository.switchRepository(CarRepository.class);
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
    public void change(Optional<Car> car, String field) {
        carRepository.update(car, field);
    }
    @Override
    public void removeById(Long id) {
        carRepository.deleteById(id);
    }
}
