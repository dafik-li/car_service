package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.Car;
import java.util.List;

public interface CarRepository extends InterfaceRepository<Car>{
    List<Car> findByBrand(String brand);
    List<Car> findByModel(String model);
    List<Car> findByYear(Integer year);
}
