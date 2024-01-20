package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.persistence.jdbcimpl.*;
import java.util.List;

public abstract class CostRepository implements InterfaceRepository<Cost> {
    protected MapperCost mapperCost;
    protected MapperOrder mapperOrder;

    public CostRepository() {
        this.mapperCost = new MapperCost();
        this.mapperOrder = new MapperOrder();
    }
    public abstract List<Cost> getByCost(Double cost);
    public abstract List<Order> getOrdersByCostId(Cost cost);
}
