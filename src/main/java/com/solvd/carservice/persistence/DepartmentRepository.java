package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;

import java.util.List;

public interface DepartmentRepository extends InterfaceRepository<Department> {
    List<Department> getByName(String name);
    List<Service> getServicesByDepartmentId(Department department);
    List<Employee> getEmployeesByDepartmentId(Department department);

}
