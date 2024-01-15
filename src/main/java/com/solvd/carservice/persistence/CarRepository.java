package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;

import java.util.List;

public interface CarRepository extends InterfaceRepository<Car>{
    List<Car> getByBrand(String brand);
    List<Car> getByModel(String model);
    List<Car> getByYear(Integer year);
    List<Service> getServicesByCarId(Car car);
    List<Detail> getDetailByCarId(Car car);
}
