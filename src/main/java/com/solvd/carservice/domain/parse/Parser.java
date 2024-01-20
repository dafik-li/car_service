package com.solvd.carservice.domain.parse;

import com.solvd.carservice.domain.parse.entity.InterfaceParse;
import com.solvd.carservice.domain.view.admin.InterfaceView;
import com.solvd.carservice.service.InterfaceService;

public class Parser<E> {
    private final InterfaceView<E> view;
    private final InterfaceParse<E> parse;
    private final InterfaceService<E> service;

    public Parser(InterfaceView<E> view, InterfaceParse<E> parse, InterfaceService<E> service) {
        this.view = view;
        this.parse = parse;
        this.service = service;
    }
    public void add(String menu) {
        if (Integer.parseInt(menu) == EnumParser.STAX.getParseType()) {
            E entity = parse.staxParse();
            service.add(entity);
            view.added(entity);
        } else if (Integer.parseInt(menu) == EnumParser.JAXB.getParseType()) {
            E entity = parse.staxParse();
            service.add(entity);
            view.added(entity);
        } else if (Integer.parseInt(menu) == EnumParser.JACKSON.getParseType()) {
            E entity = parse.staxParse();
            service.add(entity);
            view.added(entity);
        }
    }
}