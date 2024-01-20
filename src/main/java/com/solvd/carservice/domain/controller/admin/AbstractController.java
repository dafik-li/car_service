package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.controller.GetDataFromConsole;
import com.solvd.carservice.domain.controller.Validator;
import com.solvd.carservice.domain.exception.NoRequestedIdException;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.service.InterfaceService;
import java.util.List;
import java.util.Optional;

abstract public class AbstractController<E> implements InterfaceController<E> {
    protected GetDataFromConsole getDataFromConsole;
    protected Validator validator;
    protected InterfaceView<E> view;
    protected InterfaceService<E> service;

    public AbstractController(InterfaceView<E> view, InterfaceService<E> service) {
        this.service = service;
        this.view = view;
        this.getDataFromConsole = new GetDataFromConsole();
        this.validator = new Validator();
    }
    public abstract void add();
    public abstract void retrieveAll();
    public abstract void change();
    public abstract Optional<?> retrieveById();
    public void removeById() {
        view.delete();
        List<E> list = service.retrieveAll();
        long id = getDataFromConsole.getLong("id");
        try {
            validator.validateId(list, id);
            service.removeById(id);
            view.successfulDeleted();
        } catch (NoRequestedIdException e) {
            e.printStackTrace();
            removeById();
        }
    }
}
