package com.solvd.carservice.persistence;

import java.util.List;
import java.util.Optional;

public interface InterfaceRepository<E> {
    void create(E entity);
    List<E> getAll();
    Optional<E> getById(Long id);
    void update(E entity, String K);
    void deleteById(Long id);
}
