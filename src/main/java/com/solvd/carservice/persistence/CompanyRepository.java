package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;

import java.util.List;

public interface CompanyRepository extends InterfaceRepository<Company>{
    List<Company> getByName(String name);
    List<Company> getByAddress(String address);
    List<Department>getDepartmentsByCompanyId(Company company);
}
