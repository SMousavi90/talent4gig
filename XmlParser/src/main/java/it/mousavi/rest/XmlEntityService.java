package it.mousavi.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import it.mousavi.exception.InvalidSourceException;
import it.mousavi.exception.InvalidValueException;
import it.mousavi.model.Item;
import it.mousavi.model.Tag;
import it.mousavi.model.XmlEntity;
import it.mousavi.utils.Util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/xmlentity")
@Stateless
public class XmlEntityService {
	@PersistenceContext(unitName = "xmlparser")
	private EntityManager entityManager;


	public Response readSaveRestXml() {
		Item result = new Item();
		try {
			Util util = new Util();
			URL url = new URL("http://localhost:8080/XmlParser/rest/myws/StubXmlApi");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			XmlEntity xmlEntity = new XmlEntity();
			int tagsCount = 0;
			while ((output = br.readLine()) != null) {
				result = util.parseXML(output);
				// Store the data into database
				xmlEntity = new XmlEntity();
				if (result.getSource().equals("APPA") || result.getSource().equals("APPA")
						|| result.getSource().equals("APPA") || result.getSource().equals("APPA"))
					xmlEntity.setSource(result.getSource());
				else
					throw new InvalidSourceException("ERR01 " + "Invalid source");

				if (result.getValue() > 0 && result.getValue() < 1000)
					xmlEntity.setValue(result.getValue());
				else 
					throw new InvalidValueException("ERR02 " + "Invalid value");
				
				entityManager.persist(xmlEntity);
				for (Tag tag : result.getTags()) {
					tagsCount++;
					if (tagsCount > 3)
						throw new InvalidSourceException("ERR03 " + "Invalid number of tags");
					tag.setXmlEntity(xmlEntity);
					entityManager.persist(tag);
				}
			}

			conn.disconnect();
			String xmlString = util.createXMLResponse(xmlEntity.getId().toString(), "OK");

			return Response.status(200).type(MediaType.TEXT_XML).entity(xmlString).build();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public XmlEntity[] getAllXmlEntity() {
//		Query query = entityManager.createQuery("SELECT c FROM XmlEntity c");
//		List<XmlEntity> lstData = (List<XmlEntity>) query.getResultList();
//		return null;
//	}

	public Response getXmlEntityById(int id) {
		Util util = new Util();
		XmlEntity item = entityManager.find(XmlEntity.class, id);
		if (item != null) {
			String itemString = util.createTagXml(item);
			return Response.status(200).type(MediaType.TEXT_XML).entity(itemString).build();
		}
		return null;
	}

	public Response getXmlEntityByTag(String tag) {
		Util util = new Util();
		Query query = entityManager.createQuery("SELECT t FROM Tag t where t.title = :title");
		query.setParameter("title", tag);
		List<Tag> lstTags = (List<Tag>) query.getResultList();
		List<XmlEntity> lstXmlEntities = new ArrayList<XmlEntity>();
		for (Tag t : lstTags) {
			XmlEntity x = new XmlEntity();
			x.setId(t.getXmlEntity().getId());
			x.setSource(t.getXmlEntity().getSource());
			x.setValue(t.getXmlEntity().getValue());
			x.setTag(t.getXmlEntity().getTag());
			lstXmlEntities.add(x);
		}
		if (lstXmlEntities.size() > 0) {
			String itemString = util.createXmlEntitesList(lstXmlEntities);
			return Response.status(200).type(MediaType.TEXT_XML).entity(itemString).build();
		}
		return null;
	}

//	public void saveEntity() {
//		XmlEntity xmlEntity = new XmlEntity();
//		xmlEntity.setSource("test");
//		xmlEntity.setValue(2.3);
//		try {
//			entityManager.persist(xmlEntity);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
}
