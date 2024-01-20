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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;

public class ParseOrder extends AbstractParse<Order>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ParseOrder.class);
    private final static File xmlFileOrder = new File("src/main/resources/new_xml/new_order.xml");
    private final static File xsdFileOrder = new File("src/main/resources/new_xml/new_order.xsd");
    private final static File jsonFileOrder = new File("src/main/resources/json/order.json");
    private Order order;
    private final Client client;
    private final Cost cost;
    private final ParseService parseService;
    private final ParseDetail parseDetail;

    public ParseOrder() {
        this.order = new Order();
        this.client = new Client();
        this.cost = new Cost();
        this.parseService = new ParseService();
        this.parseDetail = new ParseDetail();
    }
    public Order jacksonParse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            order = mapper.readValue(jsonFileOrder, Order.class);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        return order;
    }
    public Order jaxbParse() {
        try {
            JAXBContext context = JAXBContext.newInstance(Order.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFileOrder);
            unmarshaller.setSchema(schema);
            order = (Order) unmarshaller.unmarshal(xmlFileOrder);
        } catch (JAXBException | SAXException e) {
            LOGGER.error(e.toString());
        }
        return order;
    }
    public Order staxParse() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFileOrder)) {
            xmlStaxValidator.validate(xmlFileOrder, xsdFileOrder);
            XMLEventReader reader = inputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("order")) {
                        while (reader.hasNext()) {
                            nextEvent = reader.nextEvent();
                            if (nextEvent.isStartElement()) {
                                startElement = nextEvent.asStartElement();
                                switch (startElement.getName().getLocalPart()) {
                                    case "date":
                                        nextEvent = reader.nextEvent();
                                        order.setDate(Date.valueOf(nextEvent.asCharacters().getData()));
                                        break;
                                    case "clientId":
                                        Iterator<Attribute> iterator = startElement.getAttributes();
                                        while (iterator.hasNext()) {
                                            Attribute attribute = iterator.next();
                                            QName name = attribute.getName();
                                            if (name.getLocalPart().equals("id")) {
                                                client.setId(Long.valueOf(attribute.getValue()));
                                                while (reader.hasNext()) {
                                                    nextEvent = reader.nextEvent();
                                                    if (nextEvent.isStartElement()) {
                                                        startElement = nextEvent.asStartElement();
                                                        switch (startElement.getName().getLocalPart()) {
                                                            case "name":
                                                                nextEvent = reader.nextEvent();
                                                                client.setName(nextEvent.asCharacters().getData());
                                                                break;
                                                            case "surname":
                                                                nextEvent = reader.nextEvent();
                                                                client.setSurname(nextEvent.asCharacters().getData());
                                                                break;
                                                            case "phoneNumber":
                                                                nextEvent = reader.nextEvent();
                                                                client.setPhoneNumber(nextEvent.asCharacters().getData());
                                                                break;
                                                            case "birthday":
                                                                nextEvent = reader.nextEvent();
                                                                client.setBirthday(Date.valueOf(nextEvent.asCharacters().getData()));
                                                                break;
                                                        }
                                                    }
                                                    if (nextEvent.isEndElement()) {
                                                        EndElement endElement = nextEvent.asEndElement();
                                                        if (endElement.getName().getLocalPart().equals("clientId")) {
                                                            order.setClientId(client);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    case "costId":
                                        iterator = startElement.getAttributes();
                                        while (iterator.hasNext()) {
                                            Attribute attribute = iterator.next();
                                            QName name = attribute.getName();
                                            if (name.getLocalPart().equals("id")) {
                                                cost.setId(Long.valueOf(attribute.getValue()));
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
                                                                cost.setServiceId(parseService.staxParse());
                                                                break;
                                                            case "detailId":
                                                                cost.setDetailId(parseDetail.staxParse());
                                                        }
                                                    }
                                                    if (nextEvent.isEndElement()) {
                                                        EndElement endElement = nextEvent.asEndElement();
                                                        if (endElement.getName().getLocalPart().equals("costId")) {
                                                            order.setCostId(cost);
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
                                if (endElement.getName().getLocalPart().equals("order")) {
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
        return order;
    }
}
