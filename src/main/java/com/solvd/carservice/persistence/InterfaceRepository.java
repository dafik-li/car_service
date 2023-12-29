package com.solvd.carservice.persistence;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Optional;

public interface InterfaceRepository<E> {
    void create(E entity);
    List<E> getAll();
    Optional<E> getById(Long id);
    void update(@Param("entity") E entity, @Param("string") String K);
    void deleteById(Long id);
}
