package it.mousavi.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Response;

import it.mousavi.model.Tag;
import it.mousavi.model.XmlEntity;

@Stateless
public class TagService {

	@PersistenceContext
	private EntityManager entityManager;

//	public void saveTag(Tag tag) {
//		if (tag.getId() == null) {
//			entityManager.persist(tag);
//		} else {
//			Tag t = getTagById(tag.getId());
//			t.setTitle(tag.getTitle());
//			t.setXmlEntity(tag.getXmlEntity());
//			entityManager.persist(t);
//		}
//	}
}
