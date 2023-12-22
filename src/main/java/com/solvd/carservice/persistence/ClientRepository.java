package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.Client;
import java.sql.Date;
import java.util.List;

public interface ClientRepository extends InterfaceRepository<Client>{
    List<Client> findByName(String name);
    List<Client> findBySurname(String surname);
    List<Client> findByPhoneNumber(String phoneNumber);
    List<Client> findByBirthday(Date birthday);
}
