package com.solvd.carservice.domain.entity;

import com.solvd.carservice.domain.parse.DateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.util.Objects;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {

    @XmlAttribute(name = "id")
    private Long id;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private java.util.Date date;
    private Client clientId;
    private Cost costId;


    public Order() { }
    public Order(Date date, Client clientId, Cost costId) {
        this.date = date;
        this.clientId = clientId;
        this.costId = costId;
    }
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
    public java.util.Date getDate() {
        return date;
    }
    public void setDate(java.util.Date date) {
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
