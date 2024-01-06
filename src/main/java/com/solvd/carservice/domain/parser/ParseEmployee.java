package com.solvd.carservice.domain.parser;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ParseEmployee {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ParseEmployee.class);
    public static Employee staxParseEmployee() {
        Employee employee = new Employee();
        Department department = new Department();
        Company company = new Company();
        File xmlFile = new File("src/main/resources/new_xml/new_employee.xml");
        File xsdFile = new File("src/main/resources/new_xml/new_employee.xsd");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFile)) {
            StaxValidator.validate(xmlFile, xsdFile);
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
                                                if (startElement.getName().getLocalPart().equals("name")) {
                                                    nextEvent = reader.nextEvent();
                                                    department.setName(nextEvent.asCharacters().getData());
                                                } else if (startElement.getName().getLocalPart().equals("companyId")) {
                                                    while (reader.hasNext()) {
                                                        nextEvent = reader.nextEvent();
                                                        if (nextEvent.isStartElement()) {
                                                            startElement = nextEvent.asStartElement();
                                                            if (startElement.getName().getLocalPart().equals("name")) {
                                                                nextEvent = reader.nextEvent();
                                                                company.setName(nextEvent.asCharacters().getData());
                                                            } else if (startElement.getName().getLocalPart().equals("address")) {
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
}
