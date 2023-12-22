package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.Cost;
import java.util.List;

public interface CostRepository extends InterfaceRepository<Cost> {
    List<Cost> getByCost(Double cost);
}
