package com.solvd.carservice.domain.entity;

import jakarta.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute(name = "id")
    private Long id;
    private String brand;
    private String model;
    private Integer year;

    @XmlElementWrapper(name = "services")
    @XmlElement(name = "service")
    private List<Service> services;

    @XmlElementWrapper(name = "details")
    @XmlElement(name = "detail")
    private List<Detail> details;


    public Car() { }
    public Car(Long id) {
        this.id = id;
    }
    public Car(String brand, String model, Integer year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    public Car(Long id, String brand, String model, Integer year) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public List<Service> getServices() {
        return services;
    }
    public void setServices(List<Service> services) {
        this.services = services;
    }
    public List<Detail> getDetails() {
        return details;
    }
    public void setDetails(List<Detail> details) {
        this.details = details;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return getId().equals(car.getId()) &&
                getBrand().equals(car.getBrand()) &&
                getModel().equals(car.getModel()) &&
                getYear().equals(car.getYear()) &&
                getServices().equals(car.getServices()) &&
                getDetails().equals(car.getDetails());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getModel(), getYear(), getServices(), getDetails());
    }
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
