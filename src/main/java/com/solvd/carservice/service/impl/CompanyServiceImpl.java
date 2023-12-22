package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.Company;
import com.solvd.carservice.persistence.CompanyRepository;
import com.solvd.carservice.persistence.impl.CompanyRepositoryImpl;
import com.solvd.carservice.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl() {
        this.companyRepository = new CompanyRepositoryImpl();
    }
    @Override
    public Company create(Company company) {
        company.setId(0);
        companyRepository.create(company);
        return company;
    }
}
