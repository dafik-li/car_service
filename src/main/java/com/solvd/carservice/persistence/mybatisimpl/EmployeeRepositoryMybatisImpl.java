package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.EmployeeRepository;
import com.solvd.carservice.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryMybatisImpl extends EmployeeRepository {

    @Override
    public List<Employee> getByName(String name) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getByName(name);
        }
    }
    @Override
    public List<Employee> getBySurname(String surname) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getBySurname(surname);
        }
    }
    @Override
    public List<Employee> getByAge(Integer age) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getByAge(age);
        }
    }
    @Override
    public List<Employee> getByPosition(String position) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getByPosition(position);
        }
    }
    @Override
    public List<Employee> getByLevel(Integer level) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getByLevel(level);
        }
    }
    @Override
    public List<Employee> getBySalary(Integer salary) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getBySalary(salary);
        }
    }
    @Override
    public List<Employee> getByPhoneNumber(String phoneNumber) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getByPhoneNumber(phoneNumber);
        }
    }
    @Override
    public List<Service> getServicesByEmployeeId(Employee employee) {
        try (SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getServicesByEmployeeId(employee);
        }
    }
    @Override
    public void appendService(Long employeeId, Long serviceId) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.appendService(employeeId, serviceId);
        }
    }
    @Override
    public void create(Employee employee) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.create(employee);
        }
    }
    @Override
    public List<Employee> getAll() {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getAll();
        }
    }
    @Override
    public Optional<Employee> getById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.getById(id);
        }
    }
    @Override
    public void update(Optional<Employee> employee, String field) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.update(employee, field);
        }
    }
    @Override
    public void deleteById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.deleteById(id);
        }
    }
}
