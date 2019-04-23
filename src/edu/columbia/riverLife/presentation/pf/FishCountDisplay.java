package edu.columbia.riverLife.presentation.pf;

import java.io.Serializable;

public class FishCountDisplay implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1497776913214512991L;
	private Integer year;
	private Double riverMile;
	private Integer count;
	
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getRiverMile() {
		return riverMile;
	}
	public void setRiverMile(Double riverMile) {
		this.riverMile = riverMile;
	}
	
	
	
}
