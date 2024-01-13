package com.solvd.carservice.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solvd.carservice.domain.parse.JsonDateAdapter;
import com.solvd.carservice.domain.parse.XmlDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {

    @XmlAttribute(name = "id")
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;

    @JsonDeserialize(using = JsonDateAdapter.class)
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    private java.util.Date birthday;

    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "order")
    private List<Order> orders;


    public Client() { }
    public Client(Long id) {
        this.id = id;
    }
    public Client(String name, String surname, String phoneNumber, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }
    public Client(Long id, String name, String surname, String phoneNumber, Date birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public java.util.Date getBirthday() {
        return birthday;
    }
    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getId().equals(client.getId()) &&
                getName().equals(client.getName()) &&
                getSurname().equals(client.getSurname()) &&
                getPhoneNumber().equals(client.getPhoneNumber()) &&
                getBirthday().equals(client.getBirthday()) &&
                getOrders().equals(client.getOrders());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getPhoneNumber(), getBirthday(), getOrders());
    }
    @Override
    public String toString() {
        return  "Client id - " + getId() + "|" +
                "name - " + getName() + "|" +
                "surname - " + getSurname() + "|" +
                "phone number - " + getPhoneNumber() + "|" +
                "birthday - " + getBirthday();
    }
}
