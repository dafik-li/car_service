package com.solvd.carservice.domain.parse;

import com.solvd.carservice.domain.entity.Company;
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

public class ParseCompany {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ParseCompany.class);
    private final StaxValidator staxValidator;
    private final File xmlFile = new File("src/main/resources/new_xml/new_company.xml");
    private final File xsdFile = new File("src/main/resources/new_xml/new_company.xsd");
    private Company company;

    public ParseCompany() {
        this.staxValidator = new StaxValidator();
        this.company = new Company();
    }
    public Company jaxbParse() {
        try {
            JAXBContext context = JAXBContext.newInstance(Company.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            unmarshaller.setSchema(schema);
            company = (Company) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException | SAXException e) {
            LOGGER.error(e.toString());
        }
        return company;
    }
    public Company staxParse() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFile)) {
            staxValidator.validate(xmlFile, xsdFile);
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
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return company;
    }
}
