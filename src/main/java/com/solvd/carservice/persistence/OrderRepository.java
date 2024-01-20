package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.persistence.jdbcimpl.*;
import java.sql.Date;
import java.util.List;

public abstract class OrderRepository implements InterfaceRepository<Order> {
    protected MapperOrder mapperOrder;

    public OrderRepository() {
        this.mapperOrder = new MapperOrder();
    }
    public abstract List<Order> getByDate(Date date);
}
