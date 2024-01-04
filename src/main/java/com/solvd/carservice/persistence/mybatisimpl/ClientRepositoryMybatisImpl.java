package com.solvd.carservice.persistence.mybatisimpl;

import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.persistence.ClientRepository;
import com.solvd.carservice.persistence.MybatisConfig;
import org.apache.ibatis.session.SqlSession;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryMybatisImpl implements ClientRepository {

    @Override
    public List<Client> getByName(String name) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.getByName(name);
        }
    }
    @Override
    public List<Client> getBySurname(String surname) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.getBySurname(surname);
        }
    }
    @Override
    public List<Client> getByPhoneNumber(String phoneNumber) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.getByPhoneNumber(phoneNumber);
        }
    }
    @Override
    public List<Client> getByBirthday(Date birthday) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.getByBirthday(birthday);
        }
    }
    @Override
    public void create(Client client) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
           clientRepository.create(client);
        }
    }
    @Override
    public List<Client> getAll() {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.getAll();
        }
    }
    @Override
    public Optional<Client> getById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.getById(id);
        }
    }
    @Override
    public void update(Optional<Client> client, String field) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.update(client, field);
        }
    }
    @Override
    public void deleteById(Long id) {
        try(SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.deleteById(id);
        }
    }
}
