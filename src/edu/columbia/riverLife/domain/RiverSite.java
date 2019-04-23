package edu.columbia.riverLife.domain;

import java.io.Serializable;

public class RiverSite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6761470915217708532L;
	private Integer id;
	private String name;
	private Double lat;
	private Double lon;
	private Double mile;
	private String description;
	
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
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getMile() {
		return mile;
	}
	public void setMile(Double mile) {
		this.mile = mile;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RiverSite [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lon=");
		builder.append(lon);
		builder.append(", mile=");
		builder.append(mile);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
	

}
