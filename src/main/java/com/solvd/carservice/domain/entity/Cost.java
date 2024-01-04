package com.solvd.carservice.domain.entity;

import java.util.Objects;

public class Cost {
    private Long id;
    private Double cost;
    private Service serviceId;
    private Detail detailId;

    public Cost() { }
    public Cost(Long id) {
        this.id = id;
    }
    public Cost(Double cost, Service serviceId, Detail detailId) {
        this.cost = cost;
        this.serviceId = serviceId;
        this.detailId = detailId;
    }
    public Cost(Long id, Double cost, Service serviceId, Detail detailId) {
        this.id = id;
        this.cost = cost;
        this.serviceId = serviceId;
        this.detailId = detailId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }
    public Service getServiceId() {
        return serviceId;
    }
    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }
    public Detail getDetailId() {
        return detailId;
    }
    public void setDetailId(Detail detailId) {
        this.detailId = detailId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cost)) return false;
        Cost cost1 = (Cost) o;
        return getId().equals(cost1.getId()) &&
                getCost().equals(cost1.getCost()) &&
                getServiceId().equals(cost1.getServiceId()) &&
                getDetailId().equals(cost1.getDetailId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCost(), getServiceId(), getDetailId());
    }
    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", cost=" + cost +
                ", service_id=" + serviceId +
                ", detail_id=" + detailId +
                '}';
    }
}
