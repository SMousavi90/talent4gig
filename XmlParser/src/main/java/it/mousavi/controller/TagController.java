package it.mousavi.controller;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import it.mousavi.model.Tag;
import it.mousavi.model.XmlEntity;
import it.mousavi.rest.TagService;
import it.mousavi.rest.XmlEntityService;

@Path("/tagcontroller")
public class TagController {
	private static final long serialVersionUID = 3918476573302148895L;

	@EJB
	TagService tagService;
	
}
