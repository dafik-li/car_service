package com.solvd.carservice.domain.controller.admin.navigate;

import com.solvd.carservice.domain.controller.admin.navigate.objectManager.*;

public class NavigatorFactory {

    public Navigator<?> create (String tableName) {
        switch (tableName) {
            case "1": return new CompanyFactory().create();
            case "2": return new DepartmentFactory().create();
            case "3": new EmployeeNavigator(new EmployeeFactory().createController(), new EmployeeFactory().createParser()).navigate();
            case "4": new ServiceNavigator(new ServiceFactory().createController(), new ServiceFactory().createParser()).navigate();
            case "5": return new CarFactory().create();
            case "6": return new DetailFactory().create();
            case "7": return new OrderFactory().create();
            case "8": return new CostFactory().create();
            case "9": return new ClientFactory().create();
        }
        throw new IllegalStateException("Unexpected value: " + tableName);
    }
}