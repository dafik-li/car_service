package com.solvd.carservice.domain.entity;

import jakarta.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;


@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

    @XmlAttribute(name = "id")
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String position;
    private Integer level;
    private Integer salary;
    private String phoneNumber;
    private Department departmentId;

    @XmlElementWrapper(name = "services")
    @XmlElement(name = "service")
    private List<Service> services;


    public Employee() { }
    public Employee(String name, String surname, Integer age, String position, Integer level, Integer salary, String phoneNumber, Department departmentId) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.position = position;
        this.level = level;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
    }
    public Employee(Long id, String name, String surname, Integer age, String position, Integer level, Integer salary, String phoneNumber, Department departmentId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.position = position;
        this.level = level;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.departmentId = departmentId;
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
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Department getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }
    public List<Service> getServices() {
        return services;
    }
    public void setServices(List<Service> services) {
        this.services = services;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId().equals(employee.getId()) &&
                getName().equals(employee.getName()) &&
                getSurname().equals(employee.getSurname()) &&
                getAge().equals(employee.getAge()) &&
                getPosition().equals(employee.getPosition()) &&
                getLevel().equals(employee.getLevel()) &&
                getSalary().equals(employee.getSalary()) &&
                getPhoneNumber().equals(employee.getPhoneNumber()) &&
                getDepartmentId().equals(employee.getDepartmentId()) &&
                getServices().equals(employee.getServices());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getAge(), getPosition(), getLevel(), getSalary(), getPhoneNumber(), getDepartmentId(), getServices());
    }
    @Override
    public String toString() {
        return  "Employee id - " + getId() + "|" +
                "name - " + getName() + "|" +
                "surname - " + getSurname() + "|" +
                "age - " + getAge() + "|" +
                "position - " + getPosition() + "|" +
                "level - " + getLevel() + "|" +
                "salary - " + getSalary() + "|" +
                "phone - " + getPhoneNumber() + " - [" +
                getDepartmentId() + "]";
    }
}
