package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.service.DepartmentService;
import com.solvd.carservice.service.impl.DepartmentServiceImpl;
import java.util.Optional;

public class DepartmentController extends AbstractController {

    public void add() {
        Department department =
                new Department(
                        getDataFromConsole.getStringFromConsole("name"),
                new Company(
                        getDataFromConsole.getLongFromConsole("company")));
        DepartmentService departmentService = new DepartmentServiceImpl();
        departmentService.add(department);
        viewDepartment.added(department);
    }
    public void retrieveAll() {
        viewDepartment.showAll();
        for (Department department : new DepartmentServiceImpl().retrieveAll()) {
            viewDepartment.show(department);
            for (Service service : department.getServices()) {
                viewService.show(service);
            }
            for (Employee employee : department.getEmployees()) {
                viewEmployee.show(employee);
            }
        }
    }
    public void change() {
        viewDepartment.update();
        Optional<Department> department = retrieveById();
        DepartmentService departmentService = new DepartmentServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                department.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
        }
        departmentService.change(department, field);
        viewDepartment.updated(field);
    }
    public Optional<Department> retrieveById() {
        Optional<Department> departmentOptional =
                new DepartmentServiceImpl().retrieveById(
                        (getDataFromConsole.getLongFromConsole("id")));
        viewDepartment.showById(departmentOptional);
        for (Service service : departmentOptional.get().getServices()) {
            viewService.showById(Optional.ofNullable(service));
        }
        for (Employee employee : departmentOptional.get().getEmployees()) {
            viewEmployee.showById(Optional.ofNullable(employee));
        }
        return departmentOptional;
    }
    public void removeById() {
        viewDepartment.delete();
        DepartmentService departmentService = new DepartmentServiceImpl();
        departmentService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewDepartment.successfulDeleted();
    }
}
