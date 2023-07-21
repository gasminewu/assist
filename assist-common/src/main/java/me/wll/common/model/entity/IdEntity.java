package me.wll.common.model.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** ID */
	@Id
	@GenericGenerator(name = "wi-uuid", strategy = "me.wll.common.generator.EUUIDGenerator")
	@GeneratedValue(generator = "wi-uuid")
	@JsonIgnore
	protected String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
