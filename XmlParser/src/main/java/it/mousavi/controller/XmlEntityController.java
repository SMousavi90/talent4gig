package it.mousavi.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
//import javax.inject.Named;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;



import it.mousavi.model.XmlEntity;
//import it.mousavi.service.XmlEntityService;
import it.mousavi.rest.XmlEntityService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/entitycontroller")
public class XmlEntityController implements Serializable {

	private static final long serialVersionUID = 3918476573304148895L;
	
	@EJB
	XmlEntityService xmlEntityService;
	
//	@Path("/saveEntity")
//	@GET
//	public void saveEntity() {
//		xmlEntityService.saveEntity();
//	}
	
	@Path("/readSaveXmlEntity")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response readSaveXmlEntity() {
		return xmlEntityService.readSaveRestXml();
	}
	
//	@Path("/getAllEntities")
//	@GET
//	public XmlEntity[] getAllEntities() {
//		return xmlEntityService.getAllXmlEntity();
//	}
	
	@Path("/{id}")
	@GET
	public Response getXmlEntityById(@PathParam("id") Integer id) {
		return xmlEntityService.getXmlEntityById(id);
	}
	
	@Path("/tag/{tag}")
	@GET
	public Response getXmlEntityByTag(@PathParam("tag") String tag) {
		return xmlEntityService.getXmlEntityByTag(tag);
	}
	
}
