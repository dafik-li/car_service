package com.solvd.carservice.domain.controller;

import com.solvd.carservice.domain.controller.admin.ControllerFactory;
import com.solvd.carservice.domain.controller.user.UserController;
import com.solvd.carservice.exception.AuthorizationException;
import com.solvd.carservice.exception.ModerateException;
import com.solvd.carservice.util.ConsoleMenu;
import com.solvd.carservice.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Scanner;

public class Generator {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(Generator.class);
    private final Scanner scanner;
    private final Validator validator;
    private final ConsoleMenu consoleMenu;
    private final UserController userController;
    private final ControllerFactory controllerFactory;

    public Generator() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.consoleMenu = new ConsoleMenu();
        this.userController = new UserController();
        this.controllerFactory = new ControllerFactory();
    }
    public void authorization() {
        consoleMenu.moderateStartPageMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": userController.clientFormBuilder(); break;
            case "2": moderateController(); break;
            case "0": System.exit(0); break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            authorization();
        }
    }
    public void moderateController() {
        consoleMenu.chooseControllerMenu();
        String menu = scanner.nextLine();
        controllerFactory.create(menu);
        try {
            validator.validateControllerMenu(menu);
        } catch (ModerateException e) {
            LOGGER.error(e.toString());
            moderateController();
        }
    }
}