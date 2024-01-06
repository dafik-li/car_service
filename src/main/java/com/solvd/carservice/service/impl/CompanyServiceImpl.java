package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.persistence.CompanyRepository;
import com.solvd.carservice.service.CompanyService;
import com.solvd.carservice.domain.controller.SwitcherRepository;
import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private static final SwitcherRepository switcherRepository = SwitcherRepository.getInstance();

    public CompanyServiceImpl() {
        this.companyRepository = switcherRepository.switchRepository(CompanyRepository.class);
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
    public void change(Optional<Company> company, String field) {
        companyRepository.update(company, field);
    }
    @Override
    public void removeById(Long id) {
        companyRepository.deleteById(id);
    }
}
