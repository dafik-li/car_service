package com.solvd.carservice.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class GetDataFromConsole {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(GetDataFromConsole.class);
    private final Scanner scanner;

    public GetDataFromConsole() {
        this.scanner = new Scanner(System.in);
    }
    public Long getLongFromConsole(String name) {
        LOGGER.info("Enter the " + name);
        long fromConsole = 0;
        String query = scanner.nextLine();
        try {
            LOGGER.info("You typed - " + query);
            fromConsole = Long.parseLong(query);
        } catch (NumberFormatException e) {
            LOGGER.error(e.toString());
        }
        return fromConsole;
    }
    public Integer getIntegerFromConsole(String name) {
        LOGGER.info("Enter the " + name);
        int fromConsole = 0;
        String query = scanner.nextLine();
        try {
            LOGGER.info("You typed - " + query);
            fromConsole = Integer.parseInt(query);
        } catch (NumberFormatException e) {
            LOGGER.error(e.toString());
        }
        return fromConsole;
    }
    public Double getDoubleFromConsole(String name) {
        LOGGER.info("Enter the " + name);
        double fromConsole = 0;
        String query = scanner.nextLine();
        try {
            LOGGER.info("You typed - " + query);
            fromConsole = Double.parseDouble(query);
        } catch (NumberFormatException e) {
            LOGGER.error(e.toString());
        }
        return fromConsole;
    }
    public String getStringFromConsole(String name) {
        LOGGER.info("Enter the " + name);
        String fromConsole = scanner.nextLine();
        try {
            LOGGER.info("You typed - " + fromConsole);
        } catch (NumberFormatException e) {
            LOGGER.error(e.toString());
        }
        return fromConsole;
    }
    public Date getDateFromConsole(String name) {
        LOGGER.info("Enter the " + name + "in format YYYY-MM-DD");
        java.util.Date fromConsole;
        Date fromConsoleSql = null;
        String query = scanner.nextLine();
        try {
            LOGGER.info("You typed - " + query);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fromConsole = simpleDateFormat.parse(query);
            fromConsoleSql = new Date(fromConsole.getTime());
        } catch (NumberFormatException | ParseException e) {
            LOGGER.error(e.toString());
        }
        return fromConsoleSql;
    }
    public Boolean getBooleanFromConsole(String name) {
        LOGGER.info("Enter the " + name);
        boolean fromConsole = false;
        String query = scanner.nextLine();
        try {
            LOGGER.info("You typed - " + query);
            fromConsole = Boolean.parseBoolean(query);
        } catch (NumberFormatException e) {
            LOGGER.error(e.toString());
        }
        return fromConsole;
    }
}
