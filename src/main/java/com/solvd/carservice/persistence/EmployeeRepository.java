package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.Employee;
import java.util.List;

public interface EmployeeRepository extends InterfaceRepository<Employee>{
    List<Employee> findByName(String name);
    List<Employee> findBySurname(String surname);
    List<Employee> findByAge(Integer age);
    List<Employee> findByPosition(String position);
    List<Employee> findByLevel(Integer level);
    List<Employee> findBySalary(Integer salary);
    List<Employee> findByPhoneNumber(String phoneNumber);
}
