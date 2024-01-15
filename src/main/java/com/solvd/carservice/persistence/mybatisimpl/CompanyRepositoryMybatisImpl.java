package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.persistence.CompanyRepository;
import com.solvd.carservice.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
import java.util.Optional;

public class CompanyRepositoryMybatisImpl implements CompanyRepository {

    @Override
    public List<Company> getByName(String name) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            return companyRepository.getByName(name);
        }
    }
    @Override
    public List<Company> getByAddress(String address) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            return companyRepository.getByAddress(address);
        }
    }
    public List<Department> getDepartmentsByCompanyId(Company company) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            return companyRepository.getDepartmentsByCompanyId(company);
        }
    }
    @Override
    public void create(Company company) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            companyRepository.create(company);
        }
    }
    @Override
    public List<Company> getAll() {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            return companyRepository.getAll();
        }
    }
    @Override
    public Optional<Company> getById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            return companyRepository.getById(id);
        }
    }
    @Override
    public void update(Optional<Company> company, String field) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            companyRepository.update(company, field);
        }
    }
    @Override
    public void deleteById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CompanyRepository companyRepository = sqlSession.getMapper(CompanyRepository.class);
            companyRepository.deleteById(id);
        }
    }
}
