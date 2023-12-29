package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.persistence.DepartmentRepository;
import com.solvd.carservice.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryMybatisImpl implements DepartmentRepository {

    @Override
    public List<Department> getByName(String name) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            return departmentRepository.getByName(name);
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
    public void update(Department department, String field) {
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
