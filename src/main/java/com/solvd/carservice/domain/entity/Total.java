package com.solvd.carservice.domain.entity;

import java.util.Objects;

public class Total {
    private Long id;
    private Integer sumSalary;
    private Integer countOrders;
    private Double profit;
    private Department departmentId;

    public Total(Long id, Integer sumSalary, Integer countOrders, Double profit, Department departmentId) {
        this.id = id;
        this.sumSalary = sumSalary;
        this.countOrders = countOrders;
        this.profit = profit;
        this.departmentId = departmentId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getSumSalary() {
        return sumSalary;
    }
    public void setSumSalary(Integer sumSalary) {
        this.sumSalary = sumSalary;
    }
    public Integer getCountOrders() {
        return countOrders;
    }
    public void setCountOrders(Integer countOrders) {
        this.countOrders = countOrders;
    }
    public Double getProfit() {
        return profit;
    }
    public void setProfit(Double profit) {
        this.profit = profit;
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
        if (!(o instanceof Total)) return false;
        Total total = (Total) o;
        return getId().equals(total.getId()) &&
                getSumSalary().equals(total.getSumSalary()) &&
                getCountOrders().equals(total.getCountOrders()) &&
                getProfit().equals(total.getProfit()) &&
                getDepartmentId().equals(total.getDepartmentId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSumSalary(), getCountOrders(), getProfit(), getDepartmentId());
    }
    @Override
    public String toString() {
        return "Total{" +
                "id=" + id +
                ", sum_salary=" + sumSalary +
                ", count_orders=" + countOrders +
                ", profit=" + profit +
                ", department_id=" + departmentId +
                '}';
    }
}