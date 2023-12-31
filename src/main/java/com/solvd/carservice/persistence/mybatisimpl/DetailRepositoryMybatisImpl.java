package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Detail;
import com.solvd.carservice.persistence.DetailRepository;
import com.solvd.carservice.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
import java.util.Optional;

public class DetailRepositoryMybatisImpl implements DetailRepository {

    @Override
    public List<Detail> getByName(String name) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DetailRepository detailRepository = sqlSession.getMapper(DetailRepository.class);
            return detailRepository.getByName(name);
        }
    }
    @Override
    public List<Detail> getByPrice(Integer price) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DetailRepository detailRepository = sqlSession.getMapper(DetailRepository.class);
            return detailRepository.getByPrice(price);
        }
    }
    @Override
    public List<Detail> getByInStock(Boolean inStock) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DetailRepository detailRepository = sqlSession.getMapper(DetailRepository.class);
            return detailRepository.getByInStock(inStock);
        }
    }
    @Override
    public List<Detail> getByDeliveryDays(Integer deliveryDays) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DetailRepository detailRepository = sqlSession.getMapper(DetailRepository.class);
            return detailRepository.getByDeliveryDays(deliveryDays);
        }
    }
    @Override
    public void create(Detail detail) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DetailRepository detailRepository = sqlSession.getMapper(DetailRepository.class);
            detailRepository.create(detail);
        }
    }
    @Override
    public List<Detail> getAll() {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DetailRepository detailRepository = sqlSession.getMapper(DetailRepository.class);
            return detailRepository.getAll();
        }
    }
    @Override
    public Optional<Detail> getById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DetailRepository detailRepository = sqlSession.getMapper(DetailRepository.class);
            return detailRepository.getById(id);
        }
    }
    @Override
    public void update(Optional<Detail> detail, String field) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DetailRepository detailRepository = sqlSession.getMapper(DetailRepository.class);
            detailRepository.update(detail, field);
        }
    }
    @Override
    public void deleteById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            DetailRepository detailRepository = sqlSession.getMapper(DetailRepository.class);
            detailRepository.deleteById(id);
        }
    }
}
