package com.solvd.carservice.domain;

import java.util.Objects;

public class Service {
    private Long id;
    private String name;
    private Double price;
    private Car carId;
    private Department departmentId;

    public Service(Long id, String name, Double price, Car carId, Department departmentId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.carId = carId;
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Car getCarId() {
        return carId;
    }
    public void setCarId(Car carId) {
        this.carId = carId;
    }
    public Department getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return getId().equals(service.getId()) &&
                getName().equals(service.getName()) &&
                getPrice().equals(service.getPrice()) &&
                getCarId().equals(service.getCarId()) &&
                getDepartmentId().equals(service.getDepartmentId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getCarId(), getDepartmentId());
    }
    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", car_id=" + carId +
                ", department_id=" + departmentId +
                '}';
    }
}
