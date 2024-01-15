package com.solvd.carservice.domain.view;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ViewConsoleUserMenu {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ViewConsoleUserMenu.class);

    public void userMenu() {
        LOGGER.info("Hallo in Tony Montana Car Service!" + "\n" +
                "1 - log in" + "\n" +
                "2 - register" + "\n" +
                "0 - exit");
    }
    public void returnClient(Client client) {
        LOGGER.info("Hallo " + client.getId());
    }
    public void chooseCar() {
        LOGGER.info("What car do want to pimp?");
    }
    public void returnCar(Car car) {
        LOGGER.info("Car " + car.getId());
    }
    public void chooseService() {
        LOGGER.info("What service do you need?");
    }
    public void returnService(Service service) {
        LOGGER.info("Service " + service.getId());
    }
}
