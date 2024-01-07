package com.solvd.carservice.domain.parser;

import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ParseEmployee {
    public static Employee staxParseEmployee() {
        Employee employee = new Employee();
        Department department = new Department();
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
                                        Iterator<Attribute> iterator = startElement.getAttributes();
                                        while (iterator.hasNext()) {
                                            Attribute attribute = iterator.next();
                                            QName name = attribute.getName();
                                            if (name.getLocalPart().equals("id")) {
                                                department.setId(Long.valueOf(attribute.getValue()));
                                                while (reader.hasNext()) {
                                                    nextEvent = reader.nextEvent();
                                                    if (nextEvent.isEndElement()) {
                                                        EndElement endElement = nextEvent.asEndElement();
                                                        if (endElement.getName().getLocalPart().equals("departmentId")) {
                                                            employee.setDepartmentId(department);
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
