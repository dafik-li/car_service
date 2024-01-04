package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.persistence.MybatisConfig;
import com.solvd.carservice.persistence.OrderRepository;
import org.apache.ibatis.session.SqlSession;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryMybatisImpl implements OrderRepository {

    @Override
    public List<Order> getByDate(Date date) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = sqlSession.getMapper(OrderRepository.class);
            return orderRepository.getByDate(date);
        }
    }
    @Override
    public void create(Order order) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = sqlSession.getMapper(OrderRepository.class);
            orderRepository.create(order);
        }
    }
    @Override
    public List<Order> getAll() {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = sqlSession.getMapper(OrderRepository.class);
            return orderRepository.getAll();
        }
    }
    @Override
    public Optional<Order> getById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = sqlSession.getMapper(OrderRepository.class);
            return orderRepository.getById(id);
        }
    }
    @Override
    public void update(Optional<Order> order, String field) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = sqlSession.getMapper(OrderRepository.class);
            orderRepository.update(order, field);
        }
    }
    @Override
    public void deleteById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            OrderRepository orderRepository = sqlSession.getMapper(OrderRepository.class);
            orderRepository.deleteById(id);
        }
    }
}
