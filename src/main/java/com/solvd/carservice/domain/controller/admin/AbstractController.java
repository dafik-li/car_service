package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.GetDataFromConsole;
import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.view.admin.*;
import java.util.Optional;

abstract public class AbstractController implements InterfaceController {
    protected GetDataFromConsole getDataFromConsole;
    protected Validator validator;
    protected ViewCar viewCar;
    protected ViewClient viewClient;
    protected ViewCompany viewCompany;
    protected ViewDepartment viewDepartment;
    protected ViewDetail viewDetail;
    protected ViewEmployee viewEmployee;
    protected ViewService viewService;
    protected ViewCost viewCost;
    protected ViewOrder viewOrder;

    public AbstractController() {
        this.getDataFromConsole = new GetDataFromConsole();
        this.validator = new Validator();
        this.viewCar = new ViewCar();
        this.viewClient = new ViewClient();
        this.viewCompany = new ViewCompany();
        this.viewDepartment = new ViewDepartment();
        this.viewDetail = new ViewDetail();
        this.viewEmployee = new ViewEmployee();
        this.viewService = new ViewService();
        this.viewCost = new ViewCost();
        this.viewOrder = new ViewOrder();
    }
    public abstract void add();
    public abstract void retrieveAll();
    public abstract void change();
    public abstract Optional<?> retrieveById();
    public abstract void removeById();
}
