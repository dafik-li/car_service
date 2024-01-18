package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.admin.navigate.*;

public class ControllerFactory {
    public void create (String tableName) {
        switch (tableName) {
            case "1": new NavigateCompany().navigate();break;
            case "2": new NavigateDepartment().navigate();break;
            case "3": new NavigateEmployee().navigate();break;
            case "4": new NavigateService().navigate();break;
            case "5": new NavigateCar().navigate();break;
            case "6": new NavigateDetail().navigate();break;
            case "7": new NavigateOrder().navigate();break;
            case "8": new NavigateCost().navigate();break;
            case "9": new NavigateClient().navigate();break;
            case "0": new MainMenuNavigate().authorization();break;
        }
    }
}
