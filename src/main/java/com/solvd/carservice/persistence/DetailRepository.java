package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Detail;
import java.util.List;

public interface DetailRepository extends InterfaceRepository<Detail> {
    List<Detail> getByName(String name);
    List<Detail> getByPrice(Integer price);
    List<Detail> getByInStock(Boolean inStock);
    List<Detail> getByDeliveryDays(Integer deliveryDays);
}
