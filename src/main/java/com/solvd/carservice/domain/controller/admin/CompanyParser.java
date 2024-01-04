package com.solvd.carservice.domain.controller.admin;

import com.solvd.carservice.domain.entity.Company;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CompanyParser {
    public Company staxParseCompany() {
        File file = new File("src/main/resources/add_company.xml");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fileInputStream);
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("desired")) {
                        //...
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

}
