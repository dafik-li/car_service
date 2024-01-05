package com.solvd.carservice.domain.controller;

import com.solvd.carservice.domain.entity.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Parser {

    public Company staxParseCompany() {
        Company company = new Company();
        Department department = new Department();
        Employee employee = new Employee();
        Service service = new Service();
        Car car = new Car();
        File file = new File("src/main/resources/company.xml");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            XMLEventReader reader = inputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("company")) {
                        while (reader.hasNext()) {
                            nextEvent = reader.nextEvent();
                            if (nextEvent.isStartElement()) {
                                startElement = nextEvent.asStartElement();
                                switch (startElement.getName().getLocalPart()) {
                                    case "name":
                                        nextEvent = reader.nextEvent();
                                        company.setName(nextEvent.asCharacters().getData());
                                        break;
                                    case "address":
                                        nextEvent = reader.nextEvent();
                                        company.setAddress(nextEvent.asCharacters().getData());
                                        break;
                                    case "department":
                                        while (reader.hasNext()) {
                                            nextEvent = reader.nextEvent();
                                            if (nextEvent.isStartElement()) {
                                                startElement = nextEvent.asStartElement();
                                                switch (startElement.getName().getLocalPart()) {
                                                    case "name":
                                                        nextEvent = reader.nextEvent();
                                                        department.setName(nextEvent.asCharacters().getData());
                                                    case "companyId":
                                                        while (reader.hasNext()) {
                                                            nextEvent = reader.nextEvent();
                                                            if (nextEvent.isStartElement()) {
                                                                startElement = nextEvent.asStartElement();
                                                                switch (startElement.getName().getLocalPart()) {
                                                                    case "name":
                                                                        nextEvent = reader.nextEvent();
                                                                        company.setName(nextEvent.asCharacters().getData());
                                                                    case "address":
                                                                        nextEvent = reader.nextEvent();
                                                                        company.setAddress(nextEvent.asCharacters().getData());
                                                                }
                                                            }
                                                            if (nextEvent.isEndElement()) {
                                                                EndElement endElement = nextEvent.asEndElement();
                                                                if (endElement.getName().getLocalPart().equals("companyId")) {
                                                                    department.setCompanyId(company);
                                                                }
                                                            }
                                                        }
                                                    case "employee":
                                                        while (reader.hasNext()) {
                                                            nextEvent = reader.nextEvent();
                                                            if (nextEvent.isStartElement()) {
                                                                startElement = nextEvent.asStartElement();
                                                                switch (startElement.getName().getLocalPart()) {
                                                                    case "name":
                                                                        nextEvent = reader.nextEvent();
                                                                        employee.setName(nextEvent.asCharacters().getData());
                                                                        break;
                                                                    case "surname":
                                                                        nextEvent = reader.nextEvent();
                                                                        employee.setSurname(nextEvent.asCharacters().getData());
                                                                        break;
                                                                    case "age":
                                                                        nextEvent = reader.nextEvent();
                                                                        employee.setAge(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                        break;
                                                                    case "position":
                                                                        nextEvent = reader.nextEvent();
                                                                        employee.setPosition(nextEvent.asCharacters().getData());
                                                                        break;
                                                                    case "level":
                                                                        nextEvent = reader.nextEvent();
                                                                        employee.setLevel(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                        break;
                                                                    case "salary":
                                                                        nextEvent = reader.nextEvent();
                                                                        employee.setSalary(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                        break;
                                                                    case "phoneNumber":
                                                                        nextEvent = reader.nextEvent();
                                                                        employee.setPhoneNumber(nextEvent.asCharacters().getData());
                                                                        break;
                                                                    case "departmentId":
                                                                        while (reader.hasNext()) {
                                                                            nextEvent = reader.nextEvent();
                                                                            if (nextEvent.isStartElement()) {
                                                                                startElement = nextEvent.asStartElement();
                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                    case "name":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        department.setName(nextEvent.asCharacters().getData());
                                                                                    case "companyId":
                                                                                        while (reader.hasNext()) {
                                                                                            nextEvent = reader.nextEvent();
                                                                                            if (nextEvent.isStartElement()) {
                                                                                                startElement = nextEvent.asStartElement();
                                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                                    case "name":
                                                                                                        nextEvent = reader.nextEvent();
                                                                                                        company.setName(nextEvent.asCharacters().getData());
                                                                                                    case "address":
                                                                                                        nextEvent = reader.nextEvent();
                                                                                                        company.setAddress(nextEvent.asCharacters().getData());
                                                                                                }
                                                                                            }
                                                                                            if (nextEvent.isEndElement()) {
                                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                                if (endElement.getName().getLocalPart().equals("companyId")) {
                                                                                                    department.setCompanyId(company);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                }
                                                                            }
                                                                            if (nextEvent.isEndElement()) {
                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                if (endElement.getName().getLocalPart().equals("departmentId")) {
                                                                                    employee.setDepartmentId(department);
                                                                                }
                                                                            }
                                                                        }
                                                                    case "service":
                                                                        while (reader.hasNext()) {
                                                                            nextEvent = reader.nextEvent();
                                                                            if (nextEvent.isStartElement()) {
                                                                                startElement = nextEvent.asStartElement();
                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                    case "name":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        service.setName(nextEvent.asCharacters().getData());
                                                                                        break;
                                                                                    case "price":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        service.setPrice(Double.parseDouble(nextEvent.asCharacters().getData()));
                                                                                        break;
                                                                                    case "hoursToDo":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        service.setHoursToDo(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                                        break;
                                                                                    case "carId":
                                                                                        while (reader.hasNext()) {
                                                                                            nextEvent = reader.nextEvent();
                                                                                            if (nextEvent.isStartElement()) {
                                                                                                startElement = nextEvent.asStartElement();
                                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                                    case "brand":
                                                                                                        nextEvent = reader.nextEvent();
                                                                                                        car.setBrand(nextEvent.asCharacters().getData());
                                                                                                    case "model":
                                                                                                        nextEvent = reader.nextEvent();
                                                                                                        car.setModel(nextEvent.asCharacters().getData());
                                                                                                    case "year":
                                                                                                        nextEvent = reader.nextEvent();
                                                                                                        car.setYear(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                                                }
                                                                                            }
                                                                                            if (nextEvent.isEndElement()) {
                                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                                if (endElement.getName().getLocalPart().equals("carId")) {
                                                                                                    service.setCarId(car);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    case "departmentId":
                                                                                        while (reader.hasNext()) {
                                                                                            nextEvent = reader.nextEvent();
                                                                                            if (nextEvent.isStartElement()) {
                                                                                                startElement = nextEvent.asStartElement();
                                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                                    case "name":
                                                                                                        nextEvent = reader.nextEvent();
                                                                                                        department.setName(nextEvent.asCharacters().getData());
                                                                                                    case "companyId":
                                                                                                        while (reader.hasNext()) {
                                                                                                            nextEvent = reader.nextEvent();
                                                                                                            if (nextEvent.isStartElement()) {
                                                                                                                startElement = nextEvent.asStartElement();
                                                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                                                    case "name":
                                                                                                                        nextEvent = reader.nextEvent();
                                                                                                                        company.setName(nextEvent.asCharacters().getData());
                                                                                                                    case "address":
                                                                                                                        nextEvent = reader.nextEvent();
                                                                                                                        company.setAddress(nextEvent.asCharacters().getData());
                                                                                                                }
                                                                                                            }
                                                                                                            if (nextEvent.isEndElement()) {
                                                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                                                if (endElement.getName().getLocalPart().equals("companyId")) {
                                                                                                                    department.setCompanyId(company);
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                }
                                                                                            }
                                                                                            if (nextEvent.isEndElement()) {
                                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                                if (endElement.getName().getLocalPart().equals("departmentId")) {
                                                                                                    service.setDepartmentId(department);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                }
                                                                            }
                                                                            if (nextEvent.isEndElement()) {
                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                if (endElement.getName().getLocalPart().equals("service")) {
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                }
                                                            }
                                                            if (nextEvent.isEndElement()) {
                                                                EndElement endElement = nextEvent.asEndElement();
                                                                if (endElement.getName().getLocalPart().equals("employee")) {
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    case "service":
                                                        while (reader.hasNext()) {
                                                            nextEvent = reader.nextEvent();
                                                            if (nextEvent.isStartElement()) {
                                                                startElement = nextEvent.asStartElement();
                                                                switch (startElement.getName().getLocalPart()) {
                                                                    case "name":
                                                                        nextEvent = reader.nextEvent();
                                                                        service.setName(nextEvent.asCharacters().getData());
                                                                        break;
                                                                    case "price":
                                                                        nextEvent = reader.nextEvent();
                                                                        service.setPrice(Double.parseDouble(nextEvent.asCharacters().getData()));
                                                                        break;
                                                                    case "hoursToDo":
                                                                        nextEvent = reader.nextEvent();
                                                                        service.setHoursToDo(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                        break;
                                                                    case "carId":
                                                                        while (reader.hasNext()) {
                                                                            nextEvent = reader.nextEvent();
                                                                            if (nextEvent.isStartElement()) {
                                                                                startElement = nextEvent.asStartElement();
                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                    case "brand":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        car.setBrand(nextEvent.asCharacters().getData());
                                                                                    case "model":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        car.setModel(nextEvent.asCharacters().getData());
                                                                                    case "year":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        car.setYear(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                                }
                                                                            }
                                                                            if (nextEvent.isEndElement()) {
                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                if (endElement.getName().getLocalPart().equals("carId")) {
                                                                                    service.setCarId(car);
                                                                                }
                                                                            }
                                                                        }
                                                                    case "departmentId":
                                                                        while (reader.hasNext()) {
                                                                            nextEvent = reader.nextEvent();
                                                                            if (nextEvent.isStartElement()) {
                                                                                startElement = nextEvent.asStartElement();
                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                    case "name":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        department.setName(nextEvent.asCharacters().getData());
                                                                                    case "companyId":
                                                                                        while (reader.hasNext()) {
                                                                                            nextEvent = reader.nextEvent();
                                                                                            if (nextEvent.isStartElement()) {
                                                                                                startElement = nextEvent.asStartElement();
                                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                                    case "name":
                                                                                                        nextEvent = reader.nextEvent();
                                                                                                        company.setName(nextEvent.asCharacters().getData());
                                                                                                    case "address":
                                                                                                        nextEvent = reader.nextEvent();
                                                                                                        company.setAddress(nextEvent.asCharacters().getData());
                                                                                                }
                                                                                            }
                                                                                            if (nextEvent.isEndElement()) {
                                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                                if (endElement.getName().getLocalPart().equals("companyId")) {
                                                                                                    department.setCompanyId(company);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                }
                                                                            }
                                                                            if (nextEvent.isEndElement()) {
                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                if (endElement.getName().getLocalPart().equals("departmentId")) {
                                                                                    service.setDepartmentId(department);
                                                                                }
                                                                            }
                                                                        }
                                                                    case "employee":
                                                                        while (reader.hasNext()) {
                                                                            nextEvent = reader.nextEvent();
                                                                            if (nextEvent.isStartElement()) {
                                                                                startElement = nextEvent.asStartElement();
                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                    case "name":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        employee.setName(nextEvent.asCharacters().getData());
                                                                                        break;
                                                                                    case "surname":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        employee.setSurname(nextEvent.asCharacters().getData());
                                                                                        break;
                                                                                    case "age":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        employee.setAge(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                                        break;
                                                                                    case "position":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        employee.setPosition(nextEvent.asCharacters().getData());
                                                                                        break;
                                                                                    case "level":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        employee.setLevel(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                                        break;
                                                                                    case "salary":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        employee.setSalary(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                                        break;
                                                                                    case "phoneNumber":
                                                                                        nextEvent = reader.nextEvent();
                                                                                        employee.setPhoneNumber(nextEvent.asCharacters().getData());
                                                                                        break;
                                                                                    case "departmentId":
                                                                                        while (reader.hasNext()) {
                                                                                            nextEvent = reader.nextEvent();
                                                                                            if (nextEvent.isStartElement()) {
                                                                                                startElement = nextEvent.asStartElement();
                                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                                    case "name":
                                                                                                        nextEvent = reader.nextEvent();
                                                                                                        department.setName(nextEvent.asCharacters().getData());
                                                                                                    case "companyId":
                                                                                                        while (reader.hasNext()) {
                                                                                                            nextEvent = reader.nextEvent();
                                                                                                            if (nextEvent.isStartElement()) {
                                                                                                                startElement = nextEvent.asStartElement();
                                                                                                                switch (startElement.getName().getLocalPart()) {
                                                                                                                    case "name":
                                                                                                                        nextEvent = reader.nextEvent();
                                                                                                                        company.setName(nextEvent.asCharacters().getData());
                                                                                                                    case "address":
                                                                                                                        nextEvent = reader.nextEvent();
                                                                                                                        company.setAddress(nextEvent.asCharacters().getData());
                                                                                                                }
                                                                                                            }
                                                                                                            if (nextEvent.isEndElement()) {
                                                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                                                if (endElement.getName().getLocalPart().equals("companyId")) {
                                                                                                                    department.setCompanyId(company);
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                }
                                                                                            }
                                                                                            if (nextEvent.isEndElement()) {
                                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                                if (endElement.getName().getLocalPart().equals("departmentId")) {
                                                                                                    employee.setDepartmentId(department);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                }
                                                                            }
                                                                            if (nextEvent.isEndElement()) {
                                                                                EndElement endElement = nextEvent.asEndElement();
                                                                                if (endElement.getName().getLocalPart().equals("employee")) {
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                }
                                                            }
                                                            if (nextEvent.isEndElement()) {
                                                                EndElement endElement = nextEvent.asEndElement();
                                                                if (endElement.getName().getLocalPart().equals("service")) {
                                                                    break;
                                                                }
                                                            }
                                                        }

                                                }
                                            }
                                            if (nextEvent.isEndElement()) {
                                                EndElement endElement = nextEvent.asEndElement();
                                                if (endElement.getName().getLocalPart().equals("department")) {
                                                    break;
                                                }
                                            }
                                        }
                                }
                            }
                            if (nextEvent.isEndElement()) {
                                EndElement endElement = nextEvent.asEndElement();
                                if (endElement.getName().getLocalPart().equals("company")) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return company;
    }





    public Employee staxParseEmployee() {
        Employee employee = new Employee();
        Department department = new Department();
        Company company = new Company();
        File file = new File("src/main/resources/employee.xml");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            XMLEventReader reader = inputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("employee")) {
                        while (reader.hasNext()) {
                            nextEvent = reader.nextEvent();
                            if (nextEvent.isStartElement()) {
                                startElement = nextEvent.asStartElement();
                                switch (startElement.getName().getLocalPart()) {
                                    case "name":
                                        nextEvent = reader.nextEvent();
                                        employee.setName(nextEvent.asCharacters().getData());
                                        break;
                                    case "surname":
                                        nextEvent = reader.nextEvent();
                                        employee.setSurname(nextEvent.asCharacters().getData());
                                        break;
                                    case "age":
                                        nextEvent = reader.nextEvent();
                                        employee.setAge(Integer.parseInt(nextEvent.asCharacters().getData()));
                                        break;
                                    case "position":
                                        nextEvent = reader.nextEvent();
                                        employee.setPosition(nextEvent.asCharacters().getData());
                                        break;
                                    case "level":
                                        nextEvent = reader.nextEvent();
                                        employee.setLevel(Integer.parseInt(nextEvent.asCharacters().getData()));
                                        break;
                                    case "salary":
                                        nextEvent = reader.nextEvent();
                                        employee.setSalary(Integer.parseInt(nextEvent.asCharacters().getData()));
                                        break;
                                    case "phoneNumber":
                                        nextEvent = reader.nextEvent();
                                        employee.setPhoneNumber(nextEvent.asCharacters().getData());
                                        break;
                                    case "departmentId":
                                        while (reader.hasNext()) {
                                            nextEvent = reader.nextEvent();
                                            if (nextEvent.isStartElement()) {
                                                startElement = nextEvent.asStartElement();
                                                switch (startElement.getName().getLocalPart()) {
                                                    case "name":
                                                        nextEvent = reader.nextEvent();
                                                        department.setName(nextEvent.asCharacters().getData());
                                                    case "companyId":
                                                        while (reader.hasNext()) {
                                                            nextEvent = reader.nextEvent();
                                                            if (nextEvent.isStartElement()) {
                                                                startElement = nextEvent.asStartElement();
                                                                switch (startElement.getName().getLocalPart()) {
                                                                    case "name":
                                                                        nextEvent = reader.nextEvent();
                                                                        company.setName(nextEvent.asCharacters().getData());
                                                                    case "address":
                                                                        nextEvent = reader.nextEvent();
                                                                        company.setAddress(nextEvent.asCharacters().getData());
                                                                }
                                                            }
                                                            if (nextEvent.isEndElement()) {
                                                                EndElement endElement = nextEvent.asEndElement();
                                                                if (endElement.getName().getLocalPart().equals("companyId")) {
                                                                    department.setCompanyId(company);
                                                                }
                                                            }
                                                        }
                                                }
                                            }
                                            if (nextEvent.isEndElement()) {
                                                EndElement endElement = nextEvent.asEndElement();
                                                if (endElement.getName().getLocalPart().equals("departmentId")) {
                                                    employee.setDepartmentId(department);
                                                }
                                            }
                                        }
                                }
                            }
                            if (nextEvent.isEndElement()) {
                                EndElement endElement = nextEvent.asEndElement();
                                if (endElement.getName().getLocalPart().equals("employee")) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    public Service staxParseService() {
        Service service = new Service();
        Department department = new Department();
        Company company = new Company();
        Car car = new Car();
        File file = new File("src/main/resources/service.xml");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            XMLEventReader reader = inputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("service")) {
                        while (reader.hasNext()) {
                            nextEvent = reader.nextEvent();
                            if (nextEvent.isStartElement()) {
                                startElement = nextEvent.asStartElement();
                                switch (startElement.getName().getLocalPart()) {
                                    case "name":
                                        nextEvent = reader.nextEvent();
                                        service.setName(nextEvent.asCharacters().getData());
                                        break;
                                    case "price":
                                        nextEvent = reader.nextEvent();
                                        service.setPrice(Double.parseDouble(nextEvent.asCharacters().getData()));
                                        break;
                                    case "hoursToDo":
                                        nextEvent = reader.nextEvent();
                                        service.setHoursToDo(Integer.parseInt(nextEvent.asCharacters().getData()));
                                        break;
                                    case "carId":
                                        while (reader.hasNext()) {
                                            nextEvent = reader.nextEvent();
                                            if (nextEvent.isStartElement()) {
                                                startElement = nextEvent.asStartElement();
                                                switch (startElement.getName().getLocalPart()) {
                                                    case "brand":
                                                        nextEvent = reader.nextEvent();
                                                        car.setBrand(nextEvent.asCharacters().getData());
                                                    case "model":
                                                        nextEvent = reader.nextEvent();
                                                        car.setModel(nextEvent.asCharacters().getData());
                                                    case "year":
                                                        nextEvent = reader.nextEvent();
                                                        car.setYear(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                }
                                            }
                                            if (nextEvent.isEndElement()) {
                                                EndElement endElement = nextEvent.asEndElement();
                                                if (endElement.getName().getLocalPart().equals("carId")) {
                                                    service.setCarId(car);
                                                }
                                            }
                                        }
                                    case "departmentId":
                                        while (reader.hasNext()) {
                                            nextEvent = reader.nextEvent();
                                            if (nextEvent.isStartElement()) {
                                                startElement = nextEvent.asStartElement();
                                                switch (startElement.getName().getLocalPart()) {
                                                    case "name":
                                                        nextEvent = reader.nextEvent();
                                                        department.setName(nextEvent.asCharacters().getData());
                                                    case "companyId":
                                                        while (reader.hasNext()) {
                                                            nextEvent = reader.nextEvent();
                                                            if (nextEvent.isStartElement()) {
                                                                startElement = nextEvent.asStartElement();
                                                                switch (startElement.getName().getLocalPart()) {
                                                                    case "name":
                                                                        nextEvent = reader.nextEvent();
                                                                        company.setName(nextEvent.asCharacters().getData());
                                                                    case "address":
                                                                        nextEvent = reader.nextEvent();
                                                                        company.setAddress(nextEvent.asCharacters().getData());
                                                                }
                                                            }
                                                            if (nextEvent.isEndElement()) {
                                                                EndElement endElement = nextEvent.asEndElement();
                                                                if (endElement.getName().getLocalPart().equals("companyId")) {
                                                                    department.setCompanyId(company);
                                                                }
                                                            }
                                                        }
                                                }
                                            }
                                            if (nextEvent.isEndElement()) {
                                                EndElement endElement = nextEvent.asEndElement();
                                                if (endElement.getName().getLocalPart().equals("departmentId")) {
                                                    service.setDepartmentId(department);
                                                }
                                            }
                                        }
                                }
                            }
                            if (nextEvent.isEndElement()) {
                                EndElement endElement = nextEvent.asEndElement();
                                if (endElement.getName().getLocalPart().equals("service")) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return service;
    }
}
