package edu.columbia.riverLife.domain;

import java.io.Serializable;

public class Macroinvertebrate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 416193935740670079L;
	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Macroinvertebrate [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	

}
