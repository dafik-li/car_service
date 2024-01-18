package com.solvd.carservice.domain.parse.entity;

import com.solvd.carservice.domain.parse.XmlStaxValidator;
import java.io.File;

public abstract class AbstractParse<E> implements InterfaceParse<E>{
    protected XmlStaxValidator xmlStaxValidator;
    protected File xmlFileCar = new File("src/main/resources/new_xml/new_car.xml");
    protected File xsdFileCar = new File("src/main/resources/new_xml/new_car.xsd");
    protected File jsonFileCar = new File("src/main/resources/json/car.json");
    protected File xmlFileClient = new File("src/main/resources/new_xml/new_client.xml");
    protected File xsdFileClient = new File("src/main/resources/new_xml/new_client.xsd");
    protected File jsonFileClient = new File("src/main/resources/json/client.json");
    protected File xmlFileCompany = new File("src/main/resources/new_xml/new_company.xml");
    protected File xsdFileCompany = new File("src/main/resources/new_xml/new_company.xsd");
    protected File jsonFileCompany = new File("src/main/resources/json/company.json");
    protected File xmlFileCost = new File("src/main/resources/new_xml/new_cost.xml");
    protected File xsdFileCost = new File("src/main/resources/new_xml/new_cost.xsd");
    protected File jsonFileCost = new File("src/main/resources/json/cost.json");
    protected File xmlFileDepartment = new File("src/main/resources/new_xml/new_department.xml");
    protected File xsdFileDepartment = new File("src/main/resources/new_xml/new_department.xsd");
    protected File jsonFileDepartment = new File("src/main/resources/json/department.json");
    protected File xmlFileDetail = new File("src/main/resources/new_xml/new_detail.xml");
    protected File xsdFileDetail = new File("src/main/resources/new_xml/new_detail.xsd");
    protected File jsonFileDetail = new File("src/main/resources/json/detail.json");
    protected File xmlFileEmployee = new File("src/main/resources/new_xml/new_employee.xml");
    protected File xsdFileEmployee = new File("src/main/resources/new_xml/new_employee.xsd");
    protected File jsonFileEmployee = new File("src/main/resources/json/employee.json");
    protected File xmlFileOrder = new File("src/main/resources/new_xml/new_order.xml");
    protected File xsdFileOrder = new File("src/main/resources/new_xml/new_order.xsd");
    protected File jsonFileOrder = new File("src/main/resources/json/order.json");
    protected File xmlFileService = new File("src/main/resources/new_xml/new_service.xml");
    protected File xsdFileService = new File("src/main/resources/new_xml/new_service.xsd");
    protected File jsonFileService = new File("src/main/resources/json/service.json");

    @Override
    public abstract E jacksonParse();
    @Override
    public abstract E jaxbParse();
    @Override
    public abstract E staxParse();
}
