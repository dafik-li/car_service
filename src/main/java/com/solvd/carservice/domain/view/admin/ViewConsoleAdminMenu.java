package com.solvd.carservice.domain.view.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ViewConsoleAdminMenu {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ViewConsoleAdminMenu.class);

    public void chooseRepository() {
        LOGGER.info("Please, choose a desire repository" + "\n" +
                "jdbc - press 1" + "\n" +
                "mybatis - press 2" + "\n" +
                "exit - press 0");
    }
    public void chooseAuthorization() {
        LOGGER.info("Welcome to a Tony Montana's car service!" + "\n" +
                "If you want to pimp your car - press 1" + "\n" +
                "If you our admin - press 2" + "\n" +
                "If you want to change the repository - press 3" + "\n" +
                "If you don't need anything - press 0");
    }
    public void chooseInsertMethod() {
        LOGGER.info("Please, choose a desire method to insert in db" + "\n" +
                "xml - press 1" + "\n" +
                "console - press 2" + "\n" +
                "json - press 3" + "\n" +
                "return - press 0");
    }
    public void chooseXmlParser() {
        LOGGER.info("Please, choose a desire XML parser" + "\n" +
                "stax - press 1" + "\n" +
                "jaxb - press 2" + "\n" +
                "return - press 0");
    }
    public void chooseControllerMenu() {
        LOGGER.info("Hallo our admin! Which table do you want to work with?" + "\n" +
                "1 - companies" + "\n" +
                "2 - departments" + "\n" +
                "3 - employees" + "\n" +
                "4 - services" + "\n" +
                "5 - cars" + "\n" +
                "6 - details" + "\n" +
                "7 - orders" + "\n" +
                "8 - costs" + "\n" +
                "9 - clients" + "\n" +
                "0 - return");
    }
    public void chooseModerateMenu() {
        LOGGER.info("What operation do you want to do?" + "\n" +
                "1 - add" + "\n" +
                "2 - retrieveAll" + "\n" +
                "3 - retrieveById" + "\n" +
                "4 - change" + "\n" +
                "5 - removeById" + "\n" +
                "0 - return");
    }
    public void chooseModerateEmployee() {
        LOGGER.info("What operation you want to do?" + "\n" +
                "1 - add" + "\n" +
                "2 - retrieveAll" + "\n" +
                "3 - retrieveById" + "\n" +
                "4 - change" + "\n" +
                "5 - removeById" + "\n" +
                "6 - addService" + "\n" +
                "0 - return");
    }
    public void chooseModerateService() {
        LOGGER.info("What operation you want to do?" + "\n" +
                "1 - add" + "\n" +
                "2 - retrieveAll" + "\n" +
                "3 - retrieveById" + "\n" +
                "4 - change" + "\n" +
                "5 - removeById" + "\n" +
                "6 - addEmployee" + "\n" +
                "0 - return");
    }
}