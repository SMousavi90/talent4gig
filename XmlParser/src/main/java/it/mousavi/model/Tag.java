package it.mousavi.model;

import java.io.Serializable;

import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	private String title;

    @ManyToOne
    @JoinColumn(name = "XMLENTITY_ID")
    private XmlEntity xmlEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public XmlEntity getXmlEntity() {
		return xmlEntity;
	}

	public void setXmlEntity(XmlEntity xmlEntity) {
		this.xmlEntity = xmlEntity;
	}
}
