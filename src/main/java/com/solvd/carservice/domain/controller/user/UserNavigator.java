package com.solvd.carservice.domain.controller.user;

import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.controller.admin.ClientController;
import com.solvd.carservice.domain.controller.admin.navigate.MainMenuNavigator;
import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.view.admin.ViewClient;
import com.solvd.carservice.domain.view.user.ViewConsoleUserMenu;
import com.solvd.carservice.service.impl.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Scanner;

public class UserNavigator {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(UserNavigator.class);
    private final Scanner scanner;
    private final Validator validator;
    private final ViewConsoleUserMenu viewConsoleUserMenu;
    private final UserController userController;
    private final UserCalculator userCalculator;
    private final ClientController clientController;

    public UserNavigator() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.viewConsoleUserMenu = new ViewConsoleUserMenu();
        this.userController = new UserController();
        this.userCalculator = new UserCalculator();
        this.clientController = new ClientController(new ViewClient(), new ClientServiceImpl());
    }
    public void userMenu() {
        viewConsoleUserMenu.userMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": userController.retrieveClientById(); userCalculator.calculateOrder(); break;
            case "2": clientController.add(); userMenu(); break;
            case "3": new MainMenuNavigator().authorization(); break;
            case "0": System.exit(0); break;
        }
        try {
            validator.validateAuthorization(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            userMenu();
        }
    }
}
