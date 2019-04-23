package edu.columbia.riverLife.domain;

import java.io.Serializable;
import java.util.Date;

public class FishCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4038977817165482481L;
	
	private Integer fishId;
	private Integer count;
	private Integer riverSiteId;
	private Double riverMile;
	private Date date;
	
	public Integer getFishId() {
		return fishId;
	}
	public void setFishId(Integer fishId) {
		this.fishId = fishId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getRiverSiteId() {
		return riverSiteId;
	}
	public void setRiverSiteId(Integer riverSiteId) {
		this.riverSiteId = riverSiteId;
	}
	public Double getRiverMile() {
		return riverMile;
	}
	public void setRiverMile(Double riverMile) {
		this.riverMile = riverMile;
	}
	
	
	
}
