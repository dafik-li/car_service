package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.view.admin.ViewConsoleAdminMenu;
import com.solvd.carservice.util.GetDataFromConsole;
import com.solvd.carservice.domain.controller.Validator;
import java.util.Optional;
import java.util.Scanner;

abstract public class AbstractController {
    protected Scanner scanner;
    protected Validator validator;
    protected GetDataFromConsole getDataFromConsole;
    protected ViewConsoleAdminMenu viewConsoleAdminMenu;
    protected Parser parser;

    public AbstractController() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.getDataFromConsole = new GetDataFromConsole();
        this.viewConsoleAdminMenu = new ViewConsoleAdminMenu();
        this.parser = new Parser();
    }
    public abstract void moderate();
    public abstract void selectInsertMethod();
    public abstract void selectXmlParser();
    public abstract void add();
    public abstract void retrieveAll();
    public abstract void change();
    public abstract Optional<?> retrieveById();
    public abstract void removeById();
}
