package edu.columbia.riverLife.domain;

import java.io.Serializable;

public class SamplingData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3552828927161793165L;
	private Integer year;
	private Double riverMile;
	private Double data;
	
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getRiverMile() {
		return riverMile;
	}
	public void setRiverMile(Double riverMile) {
		this.riverMile = riverMile;
	}
	public Double getData() {
		return data;
	}
	public void setData(Double data) {
		this.data = data;
	}
	
	
}
