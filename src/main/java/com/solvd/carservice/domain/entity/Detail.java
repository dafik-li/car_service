package com.solvd.carservice.domain.entity;

import java.util.Objects;

public class Detail {
    private Long id;
    private String name;
    private Integer price;
    private Car carId;
    private Boolean inStock;
    private Integer deliveryDays;

    public Detail() { }
    public Detail(Long id) {
        this.id = id;
    }
    public Detail(Long id, String name, Integer price, Boolean inStock, Integer deliveryDays) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.deliveryDays = deliveryDays;
    }
    public Detail(String name, Integer price, Car carId, Boolean inStock, Integer deliveryDays) {
        this.name = name;
        this.price = price;
        this.carId = carId;
        this.inStock = inStock;
        this.deliveryDays = deliveryDays;
    }
    public Detail(Long id, String name, Integer price, Car carId, Boolean inStock, Integer deliveryDays) {
        this.id = id;
        this.name = name;
        this.price = price;
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
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
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
                getPrice().equals(detail.getPrice()) &&
                getCarId().equals(detail.getCarId()) &&
                getInStock().equals(detail.getInStock()) &&
                getDeliveryDays().equals(detail.getDeliveryDays());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getCarId(), getInStock(), getDeliveryDays());
    }
    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", carId=" + carId +
                ", inStock=" + inStock +
                ", deliveryDays=" + deliveryDays +
                '}';
    }
}
