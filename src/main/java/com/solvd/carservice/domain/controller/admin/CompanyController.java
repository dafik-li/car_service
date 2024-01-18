package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.service.CompanyService;
import com.solvd.carservice.service.impl.CompanyServiceImpl;
import java.util.Optional;

public class CompanyController extends AbstractController {

    public void add() {
        Company company = new Company(
                getDataFromConsole.getStringFromConsole("name"),
                getDataFromConsole.getStringFromConsole("address"));
        CompanyService companyService = new CompanyServiceImpl();
        companyService.add(company);
        viewCompany.added(company);
    }
    public void retrieveAll() {
        viewCompany.showAll();
        for (Company company : new CompanyServiceImpl().retrieveAll()) {
            viewCompany.show(company);
            for (Department department : company.getDepartments()) {
                viewDepartment.show(department);
            }
        }
    }
    @Override
    public void change() {
        viewCompany.update();
        Optional<Company> company = retrieveById();
        CompanyService companyService = new CompanyServiceImpl();
        String field = getDataFromConsole.getStringFromConsole("select field");
        switch (field) {
            case "name":
                company.get().setName(getDataFromConsole.getStringFromConsole("name"));
                break;
            case "address":
                company.get().setAddress(getDataFromConsole.getStringFromConsole("address"));
                break;
        }
        companyService.change(company, field);
        viewCompany.updated(field);
    }
    public Optional<Company> retrieveById() {
        Optional<Company> companyOptional = new CompanyServiceImpl().retrieveById(
                (getDataFromConsole.getLongFromConsole("id")));
        viewCompany.showById(companyOptional);
        for (Department department : companyOptional.get().getDepartments()) {
            viewDepartment.showById(Optional.ofNullable(department));
        }
        return companyOptional;
    }
    public void removeById() {
        viewCompany.delete();
        CompanyService companyService = new CompanyServiceImpl();
        companyService.removeById(
                getDataFromConsole.getLongFromConsole("id"));
        viewCompany.successfulDeleted();
    }
}
