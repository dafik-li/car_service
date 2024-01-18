package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.jdbcimpl.*;
import java.util.List;

public abstract class DepartmentRepository implements InterfaceRepository<Department> {
    protected MapperDepartment mapperDepartment;
    protected MapperEmployee mapperEmployee;
    protected MapperService mapperService;

    public DepartmentRepository() {
        this.mapperDepartment = new MapperDepartment();
        this.mapperEmployee = new MapperEmployee();
        this.mapperService = new MapperService();
    }
    public abstract List<Department> getByName(String name);
    public abstract List<Service> getServicesByDepartmentId(Department department);
    public abstract List<Employee> getEmployeesByDepartmentId(Department department);

}
