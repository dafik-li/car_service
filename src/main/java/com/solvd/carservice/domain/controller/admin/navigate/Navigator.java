package com.solvd.carservice.domain.controller.admin.navigate;

import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.controller.admin.AbstractController;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.TableException;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.view.admin.ViewConsoleAdminMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Scanner;

public class Navigator<E> implements InterfaceNavigator {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(Navigator.class);
    protected Scanner scanner;
    protected Validator validator;
    protected ViewConsoleAdminMenu viewConsoleAdminMenu;
    protected MainMenuNavigator mainMenuNavigator;
    protected Parser<E> parser;
    protected AbstractController<E> controller;

    public Navigator(AbstractController<E> controller, Parser<E> parser) {
        this.controller = controller;
        this.parser = parser;
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.viewConsoleAdminMenu = new ViewConsoleAdminMenu();
        this.mainMenuNavigator = new MainMenuNavigator();
    }
    public void navigate() {
        viewConsoleAdminMenu.chooseModerateMenu();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectInsertMethod(); break;
            case "2": controller.retrieveAll(); break;
            case "3": controller.retrieveById(); break;
            case "4": controller.change(); break;
            case "5": controller.removeById(); break;
            case "0": mainMenuNavigator.moderateController(); break;
        }
        try {
            validator.validateActionMenu(menu);
        } catch (TableException e) {
            LOGGER.error(e.toString());
            navigate();
        }
    }
    public void selectInsertMethod() {
        viewConsoleAdminMenu.chooseInsertMethod();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": selectXmlParser(); break;
            case "2": controller.add(); break;
            case "3": parser.add(menu);
            case "0": navigate(); break;
        }
        try {
            validator.validateAuthorization(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            selectInsertMethod();
        }
    }
    public void selectXmlParser() {
        viewConsoleAdminMenu.chooseXmlParser();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1":
            case "2":
                parser.add(menu);
            case "0": selectInsertMethod(); break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            selectXmlParser();
        }
    }
}
