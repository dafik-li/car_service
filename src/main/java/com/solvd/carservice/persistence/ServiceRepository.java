package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.jdbcimpl.*;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public abstract class ServiceRepository implements InterfaceRepository<Service> {
    protected MapperEmployee mapperEmployee;
    protected MapperService mapperService;

    public ServiceRepository() {
        this.mapperEmployee = new MapperEmployee();
        this.mapperService = new MapperService();
    }
    public abstract List<Service> getByCar(Long carId);
    public abstract List<Service> getByName(String name);
    public abstract List<Service> getByPrice(Double price);
    public abstract List<Service> getByHoursToDo(Integer hoursToDo);
    public abstract List<Employee> getEmployeesByServiceId(Service service);
    public abstract void appendEmployee(@Param("employee") Long employeeId, @Param("service") Long serviceId);
}
