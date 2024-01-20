package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.domain.view.admin.ViewDepartment;
import com.solvd.carservice.service.InterfaceService;
import java.util.Optional;

public class CompanyController extends AbstractController<Company> {
    private final ViewDepartment viewDepartment;

    public CompanyController(InterfaceView<Company> view, InterfaceService<Company> service) {
        super(view, service);
        this.viewDepartment = new ViewDepartment();
    }
    public void add() {
        Company company = new Company(
                getDataFromConsole.getString("name"),
                getDataFromConsole.getString("address"));
        service.add(company);
        view.added(company);
    }
    public void retrieveAll() {
        view.showAll();
        for (Company company : service.retrieveAll()) {
            view.show(company);
            for (Department department : company.getDepartments()) {
                viewDepartment.show(department);
            }
        }
    }
    @Override
    public void change() {
        view.update();
        Optional<Company> company = retrieveById();
        String field = getDataFromConsole.getString("select field");
        switch (field) {
            case "name":
                company.get().setName(getDataFromConsole.getString("name"));
                break;
            case "address":
                company.get().setAddress(getDataFromConsole.getString("address"));
                break;
        }
        service.change(company, field);
        view.updated(field);
    }
    public Optional<Company> retrieveById() {
        Optional<Company> companyOptional = service.retrieveById(
                (getDataFromConsole.getLong("id")));
        view.showById(companyOptional);
        for (Department department : companyOptional.get().getDepartments()) {
            viewDepartment.showById(Optional.ofNullable(department));
        }
        return companyOptional;
    }
}
