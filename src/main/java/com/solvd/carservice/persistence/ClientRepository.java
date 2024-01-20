package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Order;
import com.solvd.carservice.persistence.jdbcimpl.*;
import java.sql.Date;
import java.util.List;

public abstract class ClientRepository implements InterfaceRepository<Client>{
    protected MapperOrder mapperOrder;

    public ClientRepository() {
        this.mapperOrder = new MapperOrder();
    }
    public abstract List<Client> getByName(String name);
    public abstract List<Client> getBySurname(String surname);
    public abstract List<Client> getByPhoneNumber(String phoneNumber);
    public abstract List<Client> getByBirthday(Date birthday);
    public abstract List<Order> getOrdersByClientId(Client client);
}
