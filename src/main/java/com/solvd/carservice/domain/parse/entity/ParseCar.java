package com.solvd.carservice.domain.parse.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.carservice.domain.entity.Car;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.FileInputStream;
import java.io.IOException;

public class ParseCar extends AbstractParse<Car>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ParseCar.class);
    private Car car;

    public ParseCar() {
        this.car = new Car();
    }
    public Car jacksonParse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            car = mapper.readValue(jsonFileCar, Car.class);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        return car;
    }
    public Car jaxbParse() {
        try {
            JAXBContext context = JAXBContext.newInstance(Car.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFileCar);
            unmarshaller.setSchema(schema);
            car = (Car) unmarshaller.unmarshal(xmlFileCar);
        } catch (JAXBException | SAXException e) {
            LOGGER.error(e.toString());
        }
        return car;
    }
    public Car staxParse() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFileCar)) {
            xmlStaxValidator.validate(xmlFileCar, xsdFileCar);
            XMLEventReader reader = inputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("car")) {
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
                                if (endElement.getName().getLocalPart().equals("car")) {
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
        return car;
    }
}
