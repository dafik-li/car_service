package com.solvd.carservice.service;

import java.util.List;
import java.util.Optional;

public interface InterfaceService<E> {
    E add(E entity);
    List<E> retrieveAll();
    Optional<E> retrieveById(Long id);
    void change(Optional<E> entity, String K);
    void removeById(Long id);
}
