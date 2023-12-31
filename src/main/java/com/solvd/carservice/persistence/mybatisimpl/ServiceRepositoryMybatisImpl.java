package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.MybatisConfig;
import com.solvd.carservice.persistence.ServiceRepository;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
import java.util.Optional;

public class ServiceRepositoryMybatisImpl implements ServiceRepository {

    @Override
    public List<Service> getByName(String name) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = sqlSession.getMapper(ServiceRepository.class);
            return serviceRepository.getByName(name);
        }
    }
    @Override
    public List<Service> getByPrice(Double price) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = sqlSession.getMapper(ServiceRepository.class);
            return serviceRepository.getByPrice(price);
        }
    }
    @Override
    public List<Service> getByHoursToDo(Integer hoursToDo) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = sqlSession.getMapper(ServiceRepository.class);
            return serviceRepository.getByHoursToDo(hoursToDo);
        }
    }
    @Override
    public void create(Service service) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = sqlSession.getMapper(ServiceRepository.class);
            serviceRepository.create(service);
        }
    }
    @Override
    public List<Service> getAll() {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = sqlSession.getMapper(ServiceRepository.class);
            return serviceRepository.getAll();
        }
    }
    @Override
    public Optional<Service> getById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = sqlSession.getMapper(ServiceRepository.class);
            return serviceRepository.getById(id);
        }
    }
    @Override
    public void update(Optional<Service> service, String field) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = sqlSession.getMapper(ServiceRepository.class);
            serviceRepository.update(service, field);
        }
    }
    @Override
    public void deleteById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ServiceRepository serviceRepository = sqlSession.getMapper(ServiceRepository.class);
            serviceRepository.deleteById(id);
        }
    }
}
