package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.jdbcimpl.*;
import java.util.List;

public abstract class CarRepository implements InterfaceRepository<Car> {
    protected MapperDetail mapperDetail;
    protected MapperService mapperService;

    public CarRepository() {
        this.mapperDetail = new MapperDetail();
        this.mapperService = new MapperService();
    }
    public abstract List<Car> getByBrand(String brand);
    public abstract List<Car> getByModel(String model);
    public abstract List<Car> getByYear(Integer year);
    public abstract List<Service> getServicesByCarId(Car car);
    public abstract List<Detail> getDetailByCarId(Car car);
}
