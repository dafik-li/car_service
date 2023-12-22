package com.solvd.carservice.domain;

import java.util.Objects;

public class Detail {
    private Long id;
    private String name;
    private Car carId;
    private Boolean inStock;
    private Integer deliveryDays;

    public Detail() { }
    public Detail(Long id, String name, Car carId, Boolean inStock, Integer deliveryDays) {
        this.id = id;
        this.name = name;
        this.carId = carId;
        this.inStock = inStock;
        this.deliveryDays = deliveryDays;
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
    public Car getCarId() {
        return carId;
    }
    public void setCarId(Car carId) {
        this.carId = carId;
    }
    public Boolean getInStock() {
        return inStock;
    }
    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }
    public Integer getDeliveryDays() {
        return deliveryDays;
    }
    public void setDeliveryDays(Integer deliveryDays) {
        this.deliveryDays = deliveryDays;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Detail)) return false;
        Detail detail = (Detail) o;
        return getId().equals(detail.getId()) &&
                getName().equals(detail.getName()) &&
                getCarId().equals(detail.getCarId()) &&
                getInStock().equals(detail.getInStock()) &&
                getDeliveryDays().equals(detail.getDeliveryDays());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCarId(), getInStock(), getDeliveryDays());
    }
    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", car_id=" + carId +
                ", in_stock=" + inStock +
                ", delivery_days=" + deliveryDays +
                '}';
    }
}
