package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.domain.view.admin.ViewEmployee;
import com.solvd.carservice.domain.view.admin.ViewService;
import com.solvd.carservice.service.DepartmentService;
import com.solvd.carservice.service.InterfaceService;
import com.solvd.carservice.service.impl.DepartmentServiceImpl;
import java.util.Optional;

public class DepartmentController extends AbstractController<Department> {
    private final ViewService viewService;
    private final ViewEmployee viewEmployee;

    public DepartmentController(InterfaceView<Department> view, InterfaceService<Department> service) {
        super(view, service);
        this.viewService = new ViewService();
        this.viewEmployee = new ViewEmployee();
    }
    public void add() {
        Department department =
                new Department(
                        getDataFromConsole.getString("name"),
                new Company(
                        getDataFromConsole.getLong("company")));
        service.add(department);
        view.added(department);
    }
    public void retrieveAll() {
        view.showAll();
        for (Department department : service.retrieveAll()) {
            view.show(department);
            for (Service service : department.getServices()) {
                viewService.show(service);
            }
            for (Employee employee : department.getEmployees()) {
                viewEmployee.show(employee);
            }
        }
    }
    public void change() {
        view.update();
        Optional<Department> department = retrieveById();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "name":
                department.get().setName(getDataFromConsole.getString("name"));
                break;
        }
        service.change(department, field);
        view.updated(field);
    }
    public Optional<Department> retrieveById() {
        Optional<Department> departmentOptional = service.retrieveById(
                        (getDataFromConsole.getLong("id")));
        view.showById(departmentOptional);
        for (Service service : departmentOptional.get().getServices()) {
            viewService.showById(Optional.ofNullable(service));
        }
        for (Employee employee : departmentOptional.get().getEmployees()) {
            viewEmployee.showById(Optional.ofNullable(employee));
        }
        return departmentOptional;
    }
}
