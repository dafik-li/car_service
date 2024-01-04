package com.solvd.carservice.domain.controller.user;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.util.ConsoleMenu;
import com.solvd.carservice.util.GetDataFromConsole;
import com.solvd.carservice.domain.controller.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Scanner;

public class UserController {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(Generator.class);
    private final Scanner scanner;
    private final Validator validator;
    private final GetDataFromConsole getDataFromConsole;
    private final ConsoleMenu consoleMenu;

    public UserController() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.getDataFromConsole = new GetDataFromConsole();
        this.consoleMenu = new ConsoleMenu();
    }


    public void clientFormBuilder() {
        LOGGER.info("Client form builder");
    }
}

