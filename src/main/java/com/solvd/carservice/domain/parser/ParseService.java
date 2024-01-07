package com.solvd.carservice.domain.parser;

import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Service;
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

public class ParseService {
    public static Service staxParseService() {
        Service service = new Service();
        Department department = new Department();
        Company company = new Company();
        Car car = new Car();
        File xmlFile = new File("src/main/resources/new_xml/new_service.xml");
        File xsdFile = new File("src/main/resources/new_xml/new_service.xsd");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFile)) {
            StaxValidator.validate(xmlFile, xsdFile);
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
                                        Iterator<Attribute> iterator = startElement.getAttributes();
                                        while (iterator.hasNext()) {
                                            Attribute attribute = iterator.next();
                                            QName name = attribute.getName();
                                            if (name.getLocalPart().equals("id")) {
                                                car.setId(Long.valueOf(attribute.getValue()));
                                                while (reader.hasNext()) {
                                                    nextEvent = reader.nextEvent();
                                                    if (nextEvent.isStartElement()) {
                                                        startElement = nextEvent.asStartElement();
                                                        switch (startElement.getName().getLocalPart()) {
                                                            case "brand":
                                                                nextEvent = reader.nextEvent();
                                                                car.setBrand(nextEvent.asCharacters().getData());
                                                                break;
                                                            case "model":
                                                                nextEvent = reader.nextEvent();
                                                                car.setModel(nextEvent.asCharacters().getData());
                                                                break;
                                                            case "year":
                                                                nextEvent = reader.nextEvent();
                                                                car.setYear(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                break;
                                                        }
                                                    }
                                                    if (nextEvent.isEndElement()) {
                                                        EndElement endElement = nextEvent.asEndElement();
                                                        if (endElement.getName().getLocalPart().equals("carId")) {
                                                            service.setCarId(car);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    service.setDepartmentId(ParseDepartment.staxParseDepartment());
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
