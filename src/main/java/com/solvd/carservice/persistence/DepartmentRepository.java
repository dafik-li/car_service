package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Department;
import java.util.List;

public interface DepartmentRepository extends InterfaceRepository<Department> {
    List<Department> getByName(String name);
}
