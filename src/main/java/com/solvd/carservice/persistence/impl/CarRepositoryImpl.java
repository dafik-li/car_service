package com.solvd.carservice.persistence.impl;

import com.solvd.carservice.domain.Car;
import com.solvd.carservice.persistence.CarRepository;
import java.util.List;
import java.util.Optional;

public class CarRepositoryImpl implements CarRepository {

    @Override
    public List<Car> findByBrand(String brand) {
        return null;
    }

    @Override
    public List<Car> findByModel(String model) {
        return null;
    }

    @Override
    public List<Car> findByYear(Integer year) {
        return null;
    }

    @Override
    public void create(Car car) {

    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
