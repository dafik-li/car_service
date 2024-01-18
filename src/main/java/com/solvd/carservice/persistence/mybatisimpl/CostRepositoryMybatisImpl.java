package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Cost;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.persistence.CostRepository;
import com.solvd.carservice.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
import java.util.Optional;

public class CostRepositoryMybatisImpl extends CostRepository {

    @Override
    public List<Cost> getByCost(Double cost) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CostRepository costRepository = sqlSession.getMapper(CostRepository.class);
            return costRepository.getByCost(cost);
        }
    }
    @Override
    public void create(Cost cost) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CostRepository costRepository = sqlSession.getMapper(CostRepository.class);
            costRepository.create(cost);
        }
    }
    @Override
    public List<Order> getOrdersByCostId(Cost cost) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CostRepository costRepository = sqlSession.getMapper(CostRepository.class);
            return costRepository.getOrdersByCostId(cost);
        }
    }
    @Override
    public List<Cost> getAll() {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CostRepository costRepository = sqlSession.getMapper(CostRepository.class);
            return costRepository.getAll();
        }
    }
    @Override
    public Optional<Cost> getById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CostRepository costRepository = sqlSession.getMapper(CostRepository.class);
            return costRepository.getById(id);
        }
    }
    @Override
    public void update(Optional<Cost> cost, String field) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CostRepository costRepository = sqlSession.getMapper(CostRepository.class);
            costRepository.update(cost, field);
        }
    }
    @Override
    public void deleteById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            CostRepository costRepository = sqlSession.getMapper(CostRepository.class);
            costRepository.deleteById(id);
        }
    }
}
