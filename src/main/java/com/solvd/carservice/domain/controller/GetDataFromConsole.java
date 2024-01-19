package com.solvd.carservice.domain.controller;

import com.solvd.carservice.domain.exception.AuthorizationException;
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
    private final Validator validator;

    public GetDataFromConsole() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
    }
    public Long getLong(String name) {
        LOGGER.info("Enter the " + name);
        long fromConsole = 0;
        String query = scanner.nextLine();
        try {
            validator.validateIsNumber(name);
            LOGGER.info("You typed - " + query);
            fromConsole = Long.parseLong(query);
        } catch (NumberFormatException | AuthorizationException e) {
            LOGGER.error(e.toString());
            getLong(name);
        }
        return fromConsole;
    }
    public Integer getInteger(String name) {
        LOGGER.info("Enter the " + name);
        int fromConsole = 0;
        String query = scanner.nextLine();
        try {
            validator.validateIsNumber(name);
            LOGGER.info("You typed - " + query);
            fromConsole = Integer.parseInt(query);
        } catch (NumberFormatException | AuthorizationException e) {
            LOGGER.error(e.toString());
            getInteger(name);
        }
        return fromConsole;
    }
    public Double getDouble(String name) {
        LOGGER.info("Enter the " + name);
        double fromConsole = 0;
        String query = scanner.nextLine();
        try {
            validator.validateIsDouble(name);
            LOGGER.info("You typed - " + query);
            fromConsole = Double.parseDouble(query);
        } catch (NumberFormatException | AuthorizationException e) {
            LOGGER.error(e.toString());
            getDouble(name);
        }
        return fromConsole;
    }
    public String getString(String name) {
        LOGGER.info("Enter the " + name);
        String fromConsole = scanner.nextLine();
        try {
            validator.validateIsString(fromConsole);
            LOGGER.info("You typed - " + fromConsole);
        } catch (NumberFormatException | AuthorizationException e) {
            LOGGER.error(e.toString());
            getString(name);
        }
        return fromConsole;
    }
    public Date getDate(String name) {
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
    public Boolean getBoolean(String name) {
        LOGGER.info("Enter the " + name + " 1 - yes, 0 - no");
        boolean fromConsole = false;
        String query = scanner.nextLine();
        try {
            validator.validateIsBoolean(query);
            LOGGER.info("You typed - " + query);
            fromConsole = Boolean.parseBoolean(query);
        } catch (NumberFormatException | AuthorizationException e) {
            LOGGER.error(e.toString());
            getBoolean(name);
        }
        return fromConsole;
    }
}
