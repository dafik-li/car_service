package com.solvd.carservice.domain.parse.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.carservice.domain.entity.Car;
import com.solvd.carservice.domain.entity.Detail;
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

public class ParseDetail extends AbstractParse<Detail>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ParseDetail.class);
    private final static File xmlFileDetail = new File("src/main/resources/new_xml/new_detail.xml");
    private final static File xsdFileDetail = new File("src/main/resources/new_xml/new_detail.xsd");
    private final static File jsonFileDetail = new File("src/main/resources/json/detail.json");
    private Detail detail;
    private final Car car;

    public ParseDetail() {
        this.detail = new Detail();
        this.car = new Car();
    }
    public Detail jacksonParse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            detail = mapper.readValue(jsonFileDetail, Detail.class);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        return detail;
    }
    public Detail jaxbParse() {
        try {
            JAXBContext context = JAXBContext.newInstance(Detail.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFileDetail);
            unmarshaller.setSchema(schema);
            detail = (Detail) unmarshaller.unmarshal(xmlFileDetail);
        } catch (JAXBException | SAXException e) {
            LOGGER.error(e.toString());
        }
        return detail;
    }
    public Detail staxParse() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFileDetail)) {
            xmlStaxValidator.validate(xmlFileDetail, xsdFileDetail);
            XMLEventReader reader = inputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("detail")) {
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
                                                            detail.setCarId(car);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
                                if (endElement.getName().getLocalPart().equals("detail")) {
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
        return detail;
    }
}
