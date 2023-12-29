package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Service;
import java.util.List;

public interface ServiceRepository extends InterfaceRepository<Service> {
    List<Service> getByName(String name);
    List<Service> getByPrice(Double price);
    List<Service> getByHoursToDo(Integer hoursToDo);
}
