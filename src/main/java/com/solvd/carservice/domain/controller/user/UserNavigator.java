package com.solvd.carservice.domain.controller.user;

import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.controller.admin.ClientController;
import com.solvd.carservice.domain.controller.admin.navigate.MainMenuNavigate;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.view.user.ViewConsoleUserMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Scanner;

public class UserNavigator {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(UserController.class);
    private final Scanner scanner;
    private final Validator validator;
    private final ViewConsoleUserMenu viewConsoleUserMenu;
    private final UserController userController;
    private final UserCalculator userCalculator;

    public UserNavigator() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.viewConsoleUserMenu = new ViewConsoleUserMenu();
        this.userController = new UserController();
        this.userCalculator = new UserCalculator();
    }
    public void userMenu() {
        viewConsoleUserMenu.userMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1":
                userController.retrieveClientById();
                userCalculator.calculateOrder(); break;
            case "2":
                new ClientController().add();
                userMenu(); break;
            case "3": new MainMenuNavigate().authorization(); break;
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
