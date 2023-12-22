package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.Department;
import java.util.List;

public interface DepartmentRepository extends InterfaceRepository<Department> {
    List<Department> getByName(String name);
}
