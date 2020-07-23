package it.mousavi.model;
import java.io.Serializable;


import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class XmlEntity implements Serializable {
	private static final long serialVersionUID = 0x62A6DA99AABDA8A9L;

	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	
	
	private String source;
	
	
	private Double value;
	
    @ManyToOne
    private Tag tag;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	
}