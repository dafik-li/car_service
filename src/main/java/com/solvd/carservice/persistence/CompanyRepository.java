package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.Company;
import java.util.List;

public interface CompanyRepository extends InterfaceRepository<Company>{
    List<Company> getByName(String name);
    List<Company> getByAddress(String address);
}
