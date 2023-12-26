package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.Generator;

public class ControllerFactory {
    public void create (String tableName) {
        switch (tableName) {
            case "1": new CompanyController().moderate();break;
            case "2": new DepartmentController().moderate();break;
            case "3": new EmployeeController().moderate();break;
            case "4": new ServiceController().moderate();break;
            case "5": new CarController().moderate();break;
            case "6": new DetailController().moderate();break;
            case "7": new OrderController().moderate();break;
            case "8": new CostController().moderate();break;
            case "9": new ClientController().moderate();break;
            case "0": new Generator().authorization();break;
        }
    }
}
