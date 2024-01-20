package com.solvd.carservice.domain.parse.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.carservice.domain.entity.Client;
import com.solvd.carservice.domain.entity.Employee;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;

public class ParseClient extends AbstractParse<Client>{
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ParseClient.class);
    private final static File xmlFileClient = new File("src/main/resources/new_xml/new_client.xml");
    private final static File xsdFileClient = new File("src/main/resources/new_xml/new_client.xsd");
    private final static File jsonFileClient = new File("src/main/resources/json/client.json");
    private Client client;

    public ParseClient() {
        this.client = new Client();
    }
    public Client jacksonParse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            client = mapper.readValue(jsonFileClient, Client.class);
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        return client;
    }
    public Client jaxbParse() {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFileClient);
            unmarshaller.setSchema(schema);
            client = (Client) unmarshaller.unmarshal(xmlFileClient);
        } catch (JAXBException | SAXException e) {
            LOGGER.error(e.toString());
        }
        return client;
    }
    public Client staxParse() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFileClient)) {
            xmlStaxValidator.validate(xmlFileClient, xsdFileClient);
            XMLEventReader reader = inputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("client")) {
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
                                if (endElement.getName().getLocalPart().equals("client")) {
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
        return client;
    }
}
