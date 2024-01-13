package com.solvd.carservice.domain.entity;

import jakarta.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;


@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {

    @XmlAttribute(name = "id")
    private Long id;
    private String name;
    private String address;

    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    private List<Department> departments;


    public Company() { }
    public Company(Long id) {
        this.id = id;
    }
    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public Company(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<Department> getDepartments() {
        return departments;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return getId().equals(company.getId()) &&
                getName().equals(company.getName()) &&
                getAddress().equals(company.getAddress()) &&
                getDepartments().equals(company.getDepartments());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getDepartments());
    }
    @Override
    public String toString() {
        return  "Company id - " + getId() + "|" +
                "name - " + getName() + "|" +
                "address - " + getAddress();
    }
}
