package com.solvd.carservice.domain.parse;

import com.solvd.carservice.domain.entity.Company;
import com.solvd.carservice.domain.entity.Department;
import com.solvd.carservice.domain.entity.Employee;
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

public class ParseEmployee {
    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }
    private final static Logger LOGGER = (Logger) LogManager.getLogger(ParseEmployee.class);
    private final StaxValidator staxValidator;
    private final File xmlFile = new File("src/main/resources/new_xml/new_employee.xml");
    private final File xsdFile = new File("src/main/resources/new_xml/new_employee.xsd");
    private Employee employee;
    private final Department department;
    private final ParseCompany parseCompany;

    public ParseEmployee() {
        this.staxValidator = new StaxValidator();
        this.employee = new Employee();
        this.department = new Department();
        this.parseCompany = new ParseCompany();
    }
    public Employee jaxbParse() {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            unmarshaller.setSchema(schema);
            employee = (Employee) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException | SAXException e) {
            LOGGER.error(e.toString());
        }
        return employee;
    }
    public Employee staxParse() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(xmlFile)) {
            staxValidator.validate(xmlFile, xsdFile);
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
                                                            employee.setDepartmentId(department);
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
                                if (endElement.getName().getLocalPart().equals("employee")) {
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
        return employee;
    }
}
