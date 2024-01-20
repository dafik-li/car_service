package com.solvd.carservice.domain.view.admin;

import java.util.Optional;

public interface InterfaceView<E> {
    void showAll();
    void show(E entity);
    void added(E entity);
    void update();
    void updated(String field);
    void showById(Optional<E> entity);
    void delete();
    void successfulDeleted();
}
