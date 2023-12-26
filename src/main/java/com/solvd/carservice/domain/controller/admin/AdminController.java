/*package com.solvd.carservice.domain.controller;

import com.solvd.carservice.util.ConsoleMenu;
import java.util.Scanner;

public class AdminController {
    private final Scanner scanner;
    private final ConsoleMenu consoleMenu;
    private final ControllerFactory controllerFactory;

    public AdminController() {
        this.scanner = new Scanner(System.in);
        this.consoleMenu = new ConsoleMenu();
        this.controllerFactory = new ControllerFactory();
    }
    public void moderateTables() {
        consoleMenu.moderateTablesMenu();
        String menu = scanner.nextLine();
        controllerFactory.create(menu);
        try {
            validator.validateModeration(menu);
        } catch (ModerateException e) {
            LOGGER.error(e.toString());
            moderateTables();
        }
    }
}

 */