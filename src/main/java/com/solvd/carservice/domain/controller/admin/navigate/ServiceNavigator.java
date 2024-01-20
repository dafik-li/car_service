package com.solvd.carservice.domain.controller.admin.navigate;

import com.solvd.carservice.domain.controller.admin.AbstractController;
import com.solvd.carservice.domain.controller.admin.ServiceController;
import com.solvd.carservice.domain.entity.Service;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.view.admin.ViewService;
import com.solvd.carservice.service.impl.ServiceServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ServiceNavigator extends Navigator<Service>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(Navigator.class);
    private final ServiceController serviceController;

    public ServiceNavigator(AbstractController<Service> controller, Parser<Service> parser) {
        super(controller, parser);
        this.serviceController = new ServiceController(new ViewService(), new ServiceServiceImpl());
    }
    public void navigate() {
        viewConsoleAdminMenu.chooseModerateService();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectInsertMethod(); break;
            case "2": serviceController.retrieveAll(); break;
            case "3": serviceController.retrieveById(); break;
            case "4": serviceController.change(); break;
            case "5": serviceController.removeById(); break;
            case "6": serviceController.addEmployee(); break;
            case "0": mainMenuNavigator.moderateController(); break;
        }
        try {
            validator.validateActionMenu(menu);
        } catch (TableException e) {
            LOGGER.error(e.toString());
            navigate();
        }
    }
}
