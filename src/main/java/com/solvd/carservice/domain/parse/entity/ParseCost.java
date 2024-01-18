package com.solvd.carservice.domain.parse.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.carservice.domain.entity.*;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ParseCost extends AbstractParse<Cost>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ParseCost.class);
    private Cost cost;
    private final Detail detail;
    private final Service service;
    private final ParseCar parseCar;
    private final ParseDepartment parseDepartment;

    public ParseCost() {
        this.cost = new Cost();
        this.detail = new Detail();
        this.service = new Service();
        this.parseCar = new ParseCar();
        this.parseDepartment = new ParseDepartment();
    }
    public Cost jacksonParse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            cost = mapper.readValue(jsonFileCost, Cost.class);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        return cost;
    }
    public Cost jaxbParse() {
        try {
            JAXBContext context = JAXBContext.newInstance(Cost.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFileCost);
            unmarshaller.setSchema(schema);
            cost = (Cost) unmarshaller.unmarshal(xmlFileCost);
        } catch (JAXBException | SAXException e) {
            LOGGER.error(e.toString());
        }
        return cost;
    }
    public Cost staxParse() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFileCost)) {
            xmlStaxValidator.validate(xmlFileCost, xsdFileCost);
            XMLEventReader reader = inputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("cost")) {
                        while (reader.hasNext()) {
                            nextEvent = reader.nextEvent();
                            if (nextEvent.isStartElement()) {
                                startElement = nextEvent.asStartElement();
                                switch (startElement.getName().getLocalPart()) {
                                    case "cost":
                                        nextEvent = reader.nextEvent();
                                        cost.setCost(Double.parseDouble(nextEvent.asCharacters().getData()));
                                        break;
                                    case "serviceId":
                                        Iterator<Attribute> iterator = startElement.getAttributes();
                                        while (iterator.hasNext()) {
                                            Attribute attribute = iterator.next();
                                            QName name = attribute.getName();
                                            if (name.getLocalPart().equals("id")) {
                                                service.setId(Long.valueOf(attribute.getValue()));
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
                                                                service.setCarId(parseCar.staxParse());
                                                                break;
                                                            case "departmentId":
                                                               service.setDepartmentId(parseDepartment.staxParse());
                                                               break;
                                                        }
                                                    }
                                                    if (nextEvent.isEndElement()) {
                                                        EndElement endElement = nextEvent.asEndElement();
                                                        if (endElement.getName().getLocalPart().equals("serviceId")) {
                                                            cost.setServiceId(service);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    case "detailId":
                                        iterator = startElement.getAttributes();
                                        while (iterator.hasNext()) {
                                            Attribute attribute = iterator.next();
                                            QName name = attribute.getName();
                                            if (name.getLocalPart().equals("id")) {
                                                detail.setId(Long.valueOf(attribute.getValue()));
                                                while (reader.hasNext()) {
                                                    nextEvent = reader.nextEvent();
                                                    if (nextEvent.isStartElement()) {
                                                        startElement = nextEvent.asStartElement();
                                                        switch (startElement.getName().getLocalPart()) {
                                                            case "name":
                                                                nextEvent = reader.nextEvent();
                                                                detail.setName(nextEvent.asCharacters().getData());
                                                                break;
                                                            case "price":
                                                                nextEvent = reader.nextEvent();
                                                                detail.setPrice(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                break;
                                                            case "carId":
                                                                detail.setCarId(parseCar.staxParse());
                                                                break;
                                                            case "inStock":
                                                                nextEvent = reader.nextEvent();
                                                                detail.setInStock(Boolean.parseBoolean(nextEvent.asCharacters().getData()));
                                                                break;
                                                            case "deliveryDays":
                                                                nextEvent = reader.nextEvent();
                                                                detail.setDeliveryDays(Integer.parseInt(nextEvent.asCharacters().getData()));
                                                                break;
                                                        }
                                                    }
                                                    if (nextEvent.isEndElement()) {
                                                        EndElement endElement = nextEvent.asEndElement();
                                                        if (endElement.getName().getLocalPart().equals("detailId")) {
                                                            cost.setDetailId(detail);
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
                                if (endElement.getName().getLocalPart().equals("cost")) {
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
        return cost;
    }
}
