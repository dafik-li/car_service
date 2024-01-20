package com.solvd.carservice.domain.controller.admin;

import java.util.Optional;

public interface InterfaceController<E> {
    void add();
    void retrieveAll();
    void change();
    Optional<?> retrieveById();
    void removeById();
}
