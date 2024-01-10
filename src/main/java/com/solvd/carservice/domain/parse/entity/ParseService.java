package com.solvd.carservice.domain.parse.entity;

import com.solvd.carservice.domain.entity.*;
import com.solvd.carservice.domain.parse.StaxValidator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ParseService {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ParseService.class);
    private final StaxValidator staxValidator;
    private final File xmlFile = new File("src/main/resources/new_xml/new_service.xml");
    private final File xsdFile = new File("src/main/resources/new_xml/new_service.xsd");
    private Service service;
    private final Department department;
    private final Car car;
    private final ParseCompany parseCompany;

    public ParseService() {
        this.staxValidator = new StaxValidator();
        this.service = new Service();
        this.department = new Department();
        this.car = new Car();
        this.parseCompany = new ParseCompany();
    }
    public Service jaxbParse() {
        try {
            JAXBContext context = JAXBContext.newInstance(Service.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            unmarshaller.setSchema(schema);
            service = (Service) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException | SAXException e) {
            LOGGER.error(e.toString());
        }
        return service;
    }
    public Service staxParse() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFile)) {
            staxValidator.validate(xmlFile, xsdFile);
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
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    case "departmentId":
                                        iterator = startElement.getAttributes();
                                        while (iterator.hasNext()) {
                                            Attribute attribute = iterator.next();
                                            QName name = attribute.getName();
                                            if (name.getLocalPart().equals("id")) {
                                                department.setId(Long.valueOf(attribute.getValue()));
                                                while (reader.hasNext()) {
                                                    nextEvent = reader.nextEvent();
                                                    if (nextEvent.isStartElement()) {
                                                        startElement = nextEvent.asStartElement();
                                                        switch (startElement.getName().getLocalPart()) {
                                                            case "name":
                                                                nextEvent = reader.nextEvent();
                                                                department.setName(nextEvent.asCharacters().getData());
                                                                break;
                                                            case "companyId":
                                                                department.setCompanyId(parseCompany.staxParse());
                                                                break;
                                                        }
                                                    }
                                                    if (nextEvent.isEndElement()) {
                                                        EndElement endElement = nextEvent.asEndElement();
                                                        if (endElement.getName().getLocalPart().equals("departmentId")) {
                                                            service.setDepartmentId(department);
                                                            break;
                                                        }
                                                    }
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
