package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Order;

import java.util.List;

public interface CostRepository extends InterfaceRepository<Cost> {
    List<Cost> getByCost(Double cost);
    List<Order> getOrdersByCostId(Cost cost);
}
