package com.solvd.carservice.domain.parse.entity;

public interface InterfaceParse<E> {
    E jacksonParse();
    E jaxbParse();
    E staxParse();
}
