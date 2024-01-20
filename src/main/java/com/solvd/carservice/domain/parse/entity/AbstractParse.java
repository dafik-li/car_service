package com.solvd.carservice.domain.parse.entity;

import com.solvd.carservice.domain.parse.XmlStaxValidator;

public abstract class AbstractParse<E> implements InterfaceParse<E>{
    protected XmlStaxValidator xmlStaxValidator;

    public AbstractParse() {
        this.xmlStaxValidator = new XmlStaxValidator();
    }
    @Override
    public abstract E jacksonParse();
    @Override
    public abstract E jaxbParse();
    @Override
    public abstract E staxParse();
}
