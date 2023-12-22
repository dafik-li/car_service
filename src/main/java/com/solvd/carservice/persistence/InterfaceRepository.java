package com.solvd.carservice.persistence;

import java.util.List;
import java.util.Optional;

public interface InterfaceRepository<E> {
    void create(E Entity);
    List<E> getAll();
    Optional<E> getById(Long id);
    void update(E Entity, String K);
    void deleteById(Long id);
}
