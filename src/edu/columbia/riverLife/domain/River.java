package edu.columbia.riverLife.domain;

import java.io.Serializable;

public class River implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7710415089567463574L;
	private Integer riverId;
	private String name;
	
	
	public Integer getRiverId() {
		return riverId;
	}
	public void setRiverId(Integer riverId) {
		this.riverId = riverId;
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
		builder.append("River [riverId=");
		builder.append(riverId);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
