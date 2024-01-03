package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Employee;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface EmployeeRepository extends InterfaceRepository<Employee>{
    List<Employee> getByName(String name);
    List<Employee> getBySurname(String surname);
    List<Employee> getByAge(Integer age);
    List<Employee> getByPosition(String position);
    List<Employee> getByLevel(Integer level);
    List<Employee> getBySalary(Integer salary);
    List<Employee> getByPhoneNumber(String phoneNumber);
    void appendService(@Param("employee") Long employeeId, @Param("service") Long serviceId);
}
