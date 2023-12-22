package com.solvd.carservice.domain;

import java.sql.Date;
import java.util.Objects;

public class Order {
    private Long id;
    private Date date;
    private Client clientId;
    private Cost costId;

    public Order(Long id, Date date, Client clientId, Cost costId) {
        this.id = id;
        this.date = date;
        this.clientId = clientId;
        this.costId = costId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Client getClientId() {
        return clientId;
    }
    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }
    public Cost getCostId() {
        return costId;
    }
    public void setCostId(Cost costId) {
        this.costId = costId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId()) &&
                getDate().equals(order.getDate()) &&
                getClientId().equals(order.getClientId()) &&
                getCostId().equals(order.getCostId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getClientId(), getCostId());
    }
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", client_id=" + clientId +
                ", cost_id=" + costId +
                '}';
    }
}
