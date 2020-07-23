package it.mousavi.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import it.mousavi.model.Tag;
import it.mousavi.model.XmlEntity;
import it.mousavi.model.Item;

public class Util {
	public Item parseXML(String fileName) throws UnsupportedEncodingException {
		Item emp = null;
		List<Tag> lstTags = new ArrayList<Tag>();
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		try {
			byte[] byteArray = fileName.getBytes("UTF-8"); // for files
			ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray); // for files
			XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream); // for files
//            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();
				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();
					if (startElement.getName().getLocalPart().equals("item")) {
						emp = new Item();
					}
					// set the other varibles from xml elements
					else if (startElement.getName().getLocalPart().equals("source")) {
						xmlEvent = xmlEventReader.nextEvent();
						emp.setSource(xmlEvent.asCharacters().getData());
					} else if (startElement.getName().getLocalPart().equals("value")) {
						xmlEvent = xmlEventReader.nextEvent();
						emp.setValue(Double.parseDouble(xmlEvent.asCharacters().getData()));
					} else if (startElement.getName().getLocalPart().equals("tag")) {
						xmlEvent = xmlEventReader.nextEvent();
						Tag tag = new Tag();
						tag.setTitle(xmlEvent.asCharacters().getData());
						lstTags.add(tag);
					}
				}
			}

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		emp.setTags(lstTags);
		return emp;
	}

	public String createXMLResponse(String id, String status) {
		try {
			StringWriter stringWriter = new StringWriter();

			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);

			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("item");

			xMLStreamWriter.writeStartElement("id");
			xMLStreamWriter.writeCharacters(id);
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("status");
			xMLStreamWriter.writeCharacters(status);
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndDocument();

			xMLStreamWriter.flush();
			xMLStreamWriter.close();

			String xmlString = stringWriter.getBuffer().toString();

			stringWriter.close();

			return xmlString;

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String createTagXml(XmlEntity item) {
		try {
			StringWriter stringWriter = new StringWriter();

			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);

			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("item");

			xMLStreamWriter.writeStartElement("id");
			xMLStreamWriter.writeCharacters(item.getId().toString());
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("source");
			xMLStreamWriter.writeCharacters(item.getSource());
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("value");
			xMLStreamWriter.writeCharacters(item.getValue().toString());
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndDocument();

			xMLStreamWriter.flush();
			xMLStreamWriter.close();

			String xmlString = stringWriter.getBuffer().toString();

			stringWriter.close();

			return xmlString;

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String createXmlEntitesList(List<XmlEntity> lstXmlEntities) {
		try {
			StringWriter stringWriter = new StringWriter();
			String xmlResult = "";
			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
			String xmlString = "";
			
			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("items");
			for (XmlEntity item: lstXmlEntities) {
				
				xMLStreamWriter.writeStartElement("item");

				xMLStreamWriter.writeStartElement("id");
				xMLStreamWriter.writeCharacters(item.getId().toString());
				xMLStreamWriter.writeEndElement();

				xMLStreamWriter.writeStartElement("source");
				xMLStreamWriter.writeCharacters(item.getSource());
				xMLStreamWriter.writeEndElement();

				xMLStreamWriter.writeStartElement("value");
				xMLStreamWriter.writeCharacters(item.getValue().toString());
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeEndElement();
			}
			
			
			xMLStreamWriter.writeEndDocument();

			xmlResult = stringWriter.getBuffer().toString();
			xMLStreamWriter.flush();
			xMLStreamWriter.close();
			stringWriter.close();

			return xmlResult;

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
