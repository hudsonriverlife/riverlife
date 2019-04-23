package edu.columbia.riverLife.dal.implementation.hibernate.bean;

// Generated Oct 16, 2016 7:26:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * PartnerHbm generated by hbm2java
 */
public class PartnerHbm implements java.io.Serializable {

	private int partnerId;
	private String partner;
	private Set siteSamplingHbms = new HashSet(0);

	public PartnerHbm() {
	}

	public PartnerHbm(int partnerId, String partner) {
		this.partnerId = partnerId;
		this.partner = partner;
	}

	public PartnerHbm(int partnerId, String partner, Set siteSamplingHbms) {
		this.partnerId = partnerId;
		this.partner = partner;
		this.siteSamplingHbms = siteSamplingHbms;
	}

	public int getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartner() {
		return this.partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public Set getSiteSamplingHbms() {
		return this.siteSamplingHbms;
	}

	public void setSiteSamplingHbms(Set siteSamplingHbms) {
		this.siteSamplingHbms = siteSamplingHbms;
	}

}
