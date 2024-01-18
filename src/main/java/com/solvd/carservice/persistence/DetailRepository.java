package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.persistence.jdbcimpl.MapperCost;
import com.solvd.carservice.persistence.jdbcimpl.MapperDetail;
import java.util.List;

public abstract class DetailRepository implements InterfaceRepository<Detail> {
    protected MapperCost mapperCost;
    protected MapperDetail mapperDetail;

    public DetailRepository() {
        this.mapperCost = new MapperCost();
        this.mapperDetail = new MapperDetail();
    }
    public abstract List<Detail> getByCar(Long carId);
    public abstract List<Detail> getByName(String name);
    public abstract List<Detail> getByPrice(Integer price);
    public abstract List<Detail> getByInStock(Boolean inStock);
    public abstract List<Detail> getByDeliveryDays(Integer deliveryDays);
    public abstract List<Cost> getCostsByDetailId(Detail detail);
}
