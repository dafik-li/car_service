package com.solvd.carservice.domain.entity;

import java.util.List;
import java.util.Objects;

public class Department {
    private Long id;
    private String name;
    private Company companyId;
    private List<Employee> employees;
    private List<Service> services;

    public Department() { }
    public Department(Long id) {
        this.id = id;
    }
    public Department(String name, Company companyId) {
        this.name = name;
        this.companyId = companyId;
    }
    public Department(Long id, String name, Company companyId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
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
    public Company getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName()) &&
                getCompanyId().equals(that.getCompanyId()) &&
                getEmployees().equals(that.getEmployees()) &&
                getServices().equals(that.getServices());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCompanyId(), getEmployees(), getServices());
    }
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company_id=" + companyId +
                '}';
    }
}
