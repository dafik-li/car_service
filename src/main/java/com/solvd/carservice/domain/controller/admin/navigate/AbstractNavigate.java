package com.solvd.carservice.domain.controller.admin.navigate;

import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.parse.Parser;
import com.solvd.carservice.domain.view.admin.ViewConsoleAdminMenu;
import java.util.Scanner;

public abstract class AbstractNavigate implements InterfaceNavigate {
    protected Scanner scanner;
    protected Validator validator;
    protected ViewConsoleAdminMenu viewConsoleAdminMenu;
    protected Parser parser;

    public AbstractNavigate() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.viewConsoleAdminMenu = new ViewConsoleAdminMenu();
        this.parser = new Parser();
    }
    public abstract void navigate();
    public abstract void selectInsertMethod();
    public abstract void selectXmlParser();
}
