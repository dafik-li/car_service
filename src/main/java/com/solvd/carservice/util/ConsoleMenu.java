package com.solvd.carservice.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ConsoleMenu {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ConsoleMenu.class);

    public void moderateStartPageMenu() {
        LOGGER.info("Welcome to a Tony Montana's car service!" + "\n" +
                "If you want to pimp your car - press 1" + "\n" +
                "If you our admin - press 2" + "\n" +
                "If you don't need anything - press 0");
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
    public void chooseActionMenu() {
        LOGGER.info("What operation you want to do?" + "\n" +
                "1 - add" + "\n" +
                "2 - retrieveAll" + "\n" +
                "3 - retrieveById" + "\n" +
                "4 - change" + "\n" +
                "5 - removeById" + "\n" +
                "0 - return");
    }
}
