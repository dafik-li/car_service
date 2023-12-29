package com.solvd.carservice.domain;

import java.util.Objects;

public class Service {
    private Long id;
    private String name;
    private Double price;
    private Integer hoursToDo;
    private Car carId;
    private Department departmentId;

    public Service() { }
    public Service(Long id) {
        this.id = id;
    }
    public Service(String name, Double price, Integer hoursToDo, Car carId, Department departmentId) {
        this.name = name;
        this.price = price;
        this.hoursToDo = hoursToDo;
        this.carId = carId;
        this.departmentId = departmentId;
    }
    public Service(Long id, String name, Double price, Integer hoursToDo, Car carId, Department departmentId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.hoursToDo = hoursToDo;
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
    public Integer getHoursToDo() {
        return hoursToDo;
    }
    public void setHoursToDo(Integer hoursToDo) {
        this.hoursToDo = hoursToDo;
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
                getHoursToDo().equals(service.getHoursToDo()) &&
                getCarId().equals(service.getCarId()) &&
                getDepartmentId().equals(service.getDepartmentId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getHoursToDo(), getCarId(), getDepartmentId());
    }
    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", hoursToDo=" + hoursToDo +
                ", carId=" + carId +
                ", departmentId=" + departmentId +
                '}';
    }
}
