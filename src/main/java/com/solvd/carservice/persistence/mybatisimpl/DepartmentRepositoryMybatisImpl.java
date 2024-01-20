package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.DepartmentRepository;
import com.solvd.carservice.persistence.EmployeeRepository;
import com.solvd.carservice.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryMybatisImpl extends DepartmentRepository {

    @Override
    public List<Department> getByName(String name) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            return departmentRepository.getByName(name);
        }
    }
    public List<Service> getServicesByDepartmentId(Department department) {
        try (SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            return departmentRepository.getServicesByDepartmentId(department);
        }
    }
    public List<Employee> getEmployeesByDepartmentId(Department department) {
        try (SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            return departmentRepository.getEmployeesByDepartmentId(department);
        }
    }
    @Override
    public void create(Department department) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            departmentRepository.create(department);
        }
    }
    @Override
    public List<Department> getAll() {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            return departmentRepository.getAll();
        }
    }
    @Override
    public Optional<Department> getById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            return departmentRepository.getById(id);
        }
    }
    @Override
    public void update(Optional<Department> department, String field) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            departmentRepository.update(department, field);
        }
    }
    @Override
    public void deleteById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            departmentRepository.deleteById(id);
        }
    }
}
