package com.solvd.carservice.domain.parse;

import com.solvd.carservice.util.Display;

public abstract class AbstractParser {
    protected Display display;
    protected ParseCompany parseCompany;
    protected ParseDepartment parseDepartment;
    protected ParseEmployee parseEmployee;
    protected ParseService parseService;

    public AbstractParser() {
        this.parseCompany = new ParseCompany();
        this.parseDepartment = new ParseDepartment();
        this.parseEmployee = new ParseEmployee();
        this.parseService = new ParseService();
        this.display = new Display();
    }
    public abstract void addCompany();
    //public abstract void addCar();
    //public abstract void addClient();
    //public abstract void addCost();
    public abstract void addDepartment();
    //public abstract void addDetail();
    public abstract void addEmployee();
    //public abstract void addOrder();
    public abstract void addService();
}
