package edu.columbia.riverLife.domain;

import java.io.Serializable;
import java.util.Date;

public class MacroinvertebrateCount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 509252474611618251L;
	private Integer macroinvertebrateId;
	private Integer count;
	private Integer location;
	private Date date;
	
	
	public Integer getMacroinvertebrateId() {
		return macroinvertebrateId;
	}
	public void setMacroinvertebrateId(Integer macroinvertebrateId) {
		this.macroinvertebrateId = macroinvertebrateId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MacroinvertebrateCount [macroinvertebrateId=");
		builder.append(macroinvertebrateId);
		builder.append(", count=");
		builder.append(count);
		builder.append(", location=");
		builder.append(location);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
	
	

}
