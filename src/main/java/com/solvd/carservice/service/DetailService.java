package com.solvd.carservice.service;

import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;

import java.util.List;

public interface DetailService extends InterfaceService<Detail>{
    List<Detail> retrieveByCar(Long carId);
}
