package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.parse.JaxbParser;
import com.solvd.carservice.domain.parse.StaxParser;
import com.solvd.carservice.util.ConsoleMenu;
import com.solvd.carservice.util.GetDataFromConsole;
import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.util.Display;
import java.util.Optional;
import java.util.Scanner;

abstract public class AbstractController {
    protected Scanner scanner;
    protected Validator validator;
    protected GetDataFromConsole getDataFromConsole;
    protected ConsoleMenu consoleMenu;
    protected Display display;
    protected StaxParser staxParser;
    protected JaxbParser jaxbParser;

    public AbstractController() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.getDataFromConsole = new GetDataFromConsole();
        this.consoleMenu = new ConsoleMenu();
        this.display = new Display();
        this.staxParser = new StaxParser();
        this.jaxbParser = new JaxbParser();
    }
    public abstract void moderate();
    //public abstract void selectInsertMethod();
    //public abstract void selectXmlParser();
    public abstract void add();
    public abstract void retrieveAll();
    public abstract void change();
    public abstract Optional<?> retrieveById();
    public abstract void removeById();
}
