package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.Order;
import java.sql.Date;
import java.util.List;

public interface OrderRepository extends InterfaceRepository<Order> {
    List<Order> getByDate(Date date);
}
