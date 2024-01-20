package com.solvd.carservice.domain.controller.admin.navigate;

import com.solvd.carservice.domain.controller.SwitcherRepository;
import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.controller.user.UserNavigator;
import com.solvd.carservice.domain.exception.AuthorizationException;
import com.solvd.carservice.domain.exception.ModerateException;
import com.solvd.carservice.domain.view.admin.ViewConsoleAdminMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Scanner;

public class MainMenuNavigator {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(MainMenuNavigator.class);
    private final Scanner scanner;
    private final Validator validator;
    private final ViewConsoleAdminMenu viewConsoleAdminMenu;
    private final UserNavigator userNavigator;
    private final NavigatorFactory navigatorFactory;
    private final SwitcherRepository switcherRepository;

    public MainMenuNavigator() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.viewConsoleAdminMenu = new ViewConsoleAdminMenu();
        this.userNavigator = new UserNavigator();
        this.navigatorFactory = new NavigatorFactory();
        this.switcherRepository = new SwitcherRepository();
    }
    public void selectDBRepository() {
        viewConsoleAdminMenu.chooseRepository();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": switcherRepository.useJDBCRepository(); authorization(); break;
            case "2": switcherRepository.useMybatisRepository(); authorization(); break;
            case "0": System.exit(0);break;
        }
        try {
            validator.validateStartPageMenu(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            selectDBRepository();
        }
    }
    public void authorization() {
        viewConsoleAdminMenu.chooseAuthorization();
        String menu = scanner.nextLine();
        switch (menu) {
            case "1": userNavigator.userMenu(); break;
            case "2": moderateController(); break;
            case "3": selectDBRepository(); break;
            case "0": System.exit(0); break;
        }
        try {
            validator.validateAuthorization(menu);
        } catch (AuthorizationException e) {
            LOGGER.error(e.toString());
            authorization();
        }
    }
    public void moderateController() {
        viewConsoleAdminMenu.chooseControllerMenu();
        String menu = scanner.nextLine();
        try {
            if (menu.equals("0")) {
                authorization();
            } else {
                navigatorFactory.create(menu).navigate();
            }
            validator.validateControllerMenu(menu);
        } catch (ModerateException e) {
            LOGGER.error(e.toString());
            moderateController();
        }
    }
}