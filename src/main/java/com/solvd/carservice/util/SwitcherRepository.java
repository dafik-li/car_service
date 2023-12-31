package com.solvd.carservice.util;

import com.solvd.carservice.persistence.*;
import com.solvd.carservice.persistence.jdbcimpl.*;
import com.solvd.carservice.persistence.mybatisimpl.*;

public class SwitcherRepository {
    private static SwitcherRepository instance;
    private boolean isJDBCRepository;

    public SwitcherRepository() {
    }
    public static SwitcherRepository getInstance() {
        if (instance == null) {
            instance = new SwitcherRepository();
            instance.isJDBCRepository = true;
        }
        return instance;
    }
    public void useJDBCRepository() {
        isJDBCRepository = true;
    }
    public void useMybatisRepository() {
        isJDBCRepository = false;
    }
    public <E> E switchRepository(Class<E> eClass) {
        if (eClass.equals(CarRepository.class)) {
            if (isJDBCRepository) {
                return eClass.cast(new CarRepositoryImpl());
            }
            return eClass.cast(new CarRepositoryMybatisImpl());
        } else if (eClass.equals(ClientRepository.class)) {
            if (isJDBCRepository) {
                return eClass.cast(new ClientRepositoryImpl());
            }
            return eClass.cast(new ClientRepositoryMybatisImpl());
        } else if (eClass.equals(CompanyRepository.class)) {
            if (isJDBCRepository) {
                return eClass.cast(new CompanyRepositoryImpl());
            }
            return eClass.cast(new CompanyRepositoryMybatisImpl());
        } else if (eClass.equals(CostRepository.class)) {
            if (isJDBCRepository) {
                return eClass.cast(new CostRepositoryImpl());
            }
            return eClass.cast(new CostRepositoryMybatisImpl());
        } else if (eClass.equals(DepartmentRepository.class)) {
            if (isJDBCRepository) {
                return eClass.cast(new DepartmentRepositoryImpl());
            }
            return eClass.cast(new DepartmentRepositoryMybatisImpl());
        } else if (eClass.equals(DetailRepository.class)) {
            if (isJDBCRepository) {
                return eClass.cast(new DetailRepositoryImpl());
            }
            return eClass.cast(new DetailRepositoryMybatisImpl());
        } else if (eClass.equals(EmployeeRepository.class)) {
            if (isJDBCRepository) {
                return eClass.cast(new EmployeeRepositoryImpl());
            }
            return eClass.cast(new EmployeeRepositoryMybatisImpl());
        } else if (eClass.equals(OrderRepository.class)) {
            if (isJDBCRepository) {
                return eClass.cast(new OrderRepositoryImpl());
            }
            return eClass.cast(new OrderRepositoryMybatisImpl());
        } else if (eClass.equals(ServiceRepository.class)) {
            if (isJDBCRepository) {
                return eClass.cast(new ServiceRepositoryImpl());
            }
            return eClass.cast(new ServiceRepositoryMybatisImpl());
        }
        return null;
    }
}
