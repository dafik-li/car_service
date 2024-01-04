package com.solvd.carservice.persistence;

import com.solvd.carservice.domain.entity.Client;
import java.sql.Date;
import java.util.List;

public interface ClientRepository extends InterfaceRepository<Client>{
    List<Client> getByName(String name);
    List<Client> getBySurname(String surname);
    List<Client> getByPhoneNumber(String phoneNumber);
    List<Client> getByBirthday(Date birthday);
}
