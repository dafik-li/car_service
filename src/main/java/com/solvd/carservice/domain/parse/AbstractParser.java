package com.solvd.carservice.domain.parse;

import com.solvd.carservice.util.Display;

public abstract class AbstractParser {
    protected Display display;
    protected ParseCompany parseCompany;
    protected ParseDepartment parseDepartment;
    protected ParseEmployee parseEmployee;
    protected ParseService parseService;
    protected ParseCar parseCar;
    protected ParseDetail parseDetail;
    protected ParseClient parseClient;
    protected ParseCost parseCost;
    protected ParseOrder parseOrder;

    public AbstractParser() {
        this.parseCompany = new ParseCompany();
        this.parseDepartment = new ParseDepartment();
        this.parseEmployee = new ParseEmployee();
        this.parseService = new ParseService();
        this.parseCar = new ParseCar();
        this.parseDetail = new ParseDetail();
        this.parseClient = new ParseClient();
        this.display = new Display();
        this.parseCost = new ParseCost();
        this.parseOrder = new ParseOrder();
    }
    public abstract void addCompany();
    public abstract void addCar();
    public abstract void addClient();
    public abstract void addCost();
    public abstract void addDepartment();
    public abstract void addDetail();
    public abstract void addEmployee();
    public abstract void addOrder();
    public abstract void addService();
}
