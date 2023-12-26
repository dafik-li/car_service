package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.Company;
import com.solvd.carservice.persistence.CompanyRepository;
import com.solvd.carservice.persistence.impl.CompanyRepositoryImpl;
import com.solvd.carservice.service.CompanyService;
import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl() {
        this.companyRepository = new CompanyRepositoryImpl();
    }
    @Override
    public Company add(Company company) {
        company.setId(null);
        companyRepository.create(company);
        return company;
    }
    @Override
    public List<Company> retrieveAll() {
        return companyRepository.getAll();
    }
    @Override
    public Optional<Company> retrieveById(Long id) {
        return companyRepository.getById(id);
    }
    @Override
    public void change(Company company, String field) {
        companyRepository.update(company, field);
    }
    @Override
    public void removeById(Long id) {
        companyRepository.deleteById(id);
    }
}
