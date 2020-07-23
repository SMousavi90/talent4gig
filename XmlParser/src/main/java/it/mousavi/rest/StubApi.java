package it.mousavi.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Path("/myws")
public class StubApi {
	@GET
	@Path("/StubXmlApi")
	public String StubXmlApi() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("item");
			doc.appendChild(rootElement);

			Element source = doc.createElement("source");
			source.appendChild(doc.createTextNode("APPA"));
			rootElement.appendChild(source);
			
			Element value = doc.createElement("value");
			value.appendChild(doc.createTextNode("123.43"));
			rootElement.appendChild(value);

			Element tags = doc.createElement("tags");
			
			Element tag1 = doc.createElement("tag");
			tag1.appendChild(doc.createTextNode("APPA"));
			tags.appendChild(tag1);

			Element tag2 = doc.createElement("tag");
			tag2.appendChild(doc.createTextNode("FRONTEND"));
			tags.appendChild(tag2);
			
			rootElement.appendChild(tags);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource dmSource = new DOMSource(doc);
			StringWriter sw = new StringWriter();
			transformer.transform(dmSource, new StreamResult(sw));

			return sw.toString();

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		return null;
	}
}