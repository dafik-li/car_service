package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.persistence.CarRepository;
import com.solvd.carservice.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
import java.util.Optional;

public class CarRepositoryMybatisImpl extends CarRepository {

    @Override
    public List<Car> getByBrand(String brand) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            return carRepository.getByBrand(brand);
        }
    }
    @Override
    public List<Car> getByModel(String model) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            return carRepository.getByModel(model);
        }
    }
    @Override
    public List<Car> getByYear(Integer year) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            return carRepository.getByYear(year);
        }
    }
    @Override
    public void create(Car car) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            carRepository.create(car);
        }
    }
    @Override
    public List<Service> getServicesByCarId(Car car) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            return carRepository.getServicesByCarId(car);
        }
    }
    @Override
    public List<Detail> getDetailByCarId(Car car) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            return carRepository.getDetailByCarId(car);
        }
    }
    @Override
    public List<Car> getAll() {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            return carRepository.getAll();
        }
    }
    @Override
    public Optional<Car> getById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            return carRepository.getById(id);
        }
    }
    @Override
    public void update(Optional<Car> car, String field) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            carRepository.update(car, field);
        }
    }
    @Override
    public void deleteById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CarRepository carRepository = sqlSession.getMapper(CarRepository.class);
            carRepository.deleteById(id);
        }
    }
}
