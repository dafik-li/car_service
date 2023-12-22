package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.Detail;
import java.util.List;

public interface DetailRepository extends InterfaceRepository<Detail> {
    List<Detail> findByName(String name);
    List<Detail> findByInStock(Boolean inStock);
    List<Detail> findByDeliveryDays(Integer deliveryDays);
}
