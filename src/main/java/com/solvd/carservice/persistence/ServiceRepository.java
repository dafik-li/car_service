package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.Service;
import java.util.List;

public interface ServiceRepository extends InterfaceRepository<Service> {
    List<Service> findByName(String name);
    List<Service> findByPrice(Double price);
}
