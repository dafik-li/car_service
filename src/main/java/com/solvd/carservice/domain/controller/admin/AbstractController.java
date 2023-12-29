package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.util.ConsoleMenu;
import com.solvd.carservice.util.GetDataFromConsole;
import com.solvd.carservice.domain.controller.Validator;
import java.util.Scanner;

abstract public class AbstractController {
    protected Scanner scanner;
    protected Validator validator;;
    protected GetDataFromConsole getDataFromConsole;
    protected ConsoleMenu consoleMenu;

    public AbstractController() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.getDataFromConsole = new GetDataFromConsole();
        this.consoleMenu = new ConsoleMenu();
    }

    public abstract void moderate();
    public abstract void add();
    public abstract void retrieveAll();
    public abstract void change();
    public abstract void retrieveById();
    public abstract void removeById();
}
