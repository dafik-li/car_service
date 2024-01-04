package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Car;
import java.util.List;

public interface CarRepository extends InterfaceRepository<Car>{
    List<Car> getByBrand(String brand);
    List<Car> getByModel(String model);
    List<Car> getByYear(Integer year);
}
