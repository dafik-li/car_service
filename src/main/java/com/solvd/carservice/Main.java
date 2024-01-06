package com.solvd.carservice;

import com.solvd.carservice.domain.controller.Generator;
import com.solvd.carservice.domain.parser.ParseDepartment;

public class Main {

    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.selectDBRepository();
        //StaxValidator validator = new StaxValidator();
        //validator.validate();
        //ParseEmployee parseEmployee = new ParseEmployee();
        //parseEmployee.staxParseEmployee();
        //ParseService parseService = new ParseService();
        //parseService.staxParseService();
        //ParseDepartment parseDepartment = new ParseDepartment();
        //parseDepartment.staxParseDepartment();
        //ParseCompany parseCompany = new ParseCompany();
        //parseCompany.staxParseCompany();
    }
}
