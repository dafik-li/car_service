package com.solvd.carservice.persistence;

import java.util.List;
import java.util.Optional;

public interface InterfaceRepository<E> {
    void create(E Entity);
    List<E> findAll();
    Optional<E> findById(Long id);
    void update(E Entity);
    void deleteById(Long id);
}
