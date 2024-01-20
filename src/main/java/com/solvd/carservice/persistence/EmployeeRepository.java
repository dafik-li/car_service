package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.jdbcimpl.*;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public abstract class EmployeeRepository implements InterfaceRepository<Employee> {
    protected MapperEmployee mapperEmployee;
    protected MapperService mapperService;

    public EmployeeRepository() {
        this.mapperEmployee = new MapperEmployee();
        this.mapperService = new MapperService();
    }
    public abstract List<Employee> getByName(String name);
    public abstract List<Employee> getBySurname(String surname);
    public abstract List<Employee> getByAge(Integer age);
    public abstract List<Employee> getByPosition(String position);
    public abstract List<Employee> getByLevel(Integer level);
    public abstract List<Employee> getBySalary(Integer salary);
    public abstract List<Employee> getByPhoneNumber(String phoneNumber);
    public abstract List<Service> getServicesByEmployeeId(Employee employee);
    public abstract void appendService(@Param("employee") Long employeeId, @Param("service") Long serviceId);
}
