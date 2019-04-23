package edu.columbia.riverLife.dal.implementation.hibernate.bean;

// Generated Oct 16, 2016 7:26:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * CurrentTypeHbm generated by hbm2java
 */
public class CurrentTypeHbm implements java.io.Serializable {

	private int currentTypeId;
	private String currentType;
	private Set currentObservationHbms = new HashSet(0);

	public CurrentTypeHbm() {
	}

	public CurrentTypeHbm(int currentTypeId, String currentType) {
		this.currentTypeId = currentTypeId;
		this.currentType = currentType;
	}

	public CurrentTypeHbm(int currentTypeId, String currentType,
			Set currentObservationHbms) {
		this.currentTypeId = currentTypeId;
		this.currentType = currentType;
		this.currentObservationHbms = currentObservationHbms;
	}

	public int getCurrentTypeId() {
		return this.currentTypeId;
	}

	public void setCurrentTypeId(int currentTypeId) {
		this.currentTypeId = currentTypeId;
	}

	public String getCurrentType() {
		return this.currentType;
	}

	public void setCurrentType(String currentType) {
		this.currentType = currentType;
	}

	public Set getCurrentObservationHbms() {
		return this.currentObservationHbms;
	}

	public void setCurrentObservationHbms(Set currentObservationHbms) {
		this.currentObservationHbms = currentObservationHbms;
	}

}
