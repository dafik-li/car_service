package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.persistence.jdbcimpl.*;
import java.util.List;

public abstract class CompanyRepository implements InterfaceRepository<Company> {
    protected MapperDepartment mapperDepartment;

    public CompanyRepository() {
        this.mapperDepartment = new MapperDepartment();
    }
    public abstract List<Company> getByName(String name);
    public abstract List<Company> getByAddress(String address);
    public abstract List<Department>getDepartmentsByCompanyId(Company company);
}
