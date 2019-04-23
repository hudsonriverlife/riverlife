package edu.columbia.riverLife.dal.implementation.hibernate.bean;

// Generated Oct 16, 2016 7:26:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * FishingMethodHbm generated by hbm2java
 */
public class FishingMethodHbm implements java.io.Serializable {

	private int fishingMethodId;
	private String fishingMethod;
	private Set siteSamplingFishCountHbms = new HashSet(0);

	public FishingMethodHbm() {
	}

	public FishingMethodHbm(int fishingMethodId, String fishingMethod) {
		this.fishingMethodId = fishingMethodId;
		this.fishingMethod = fishingMethod;
	}

	public FishingMethodHbm(int fishingMethodId, String fishingMethod,
			Set siteSamplingFishCountHbms) {
		this.fishingMethodId = fishingMethodId;
		this.fishingMethod = fishingMethod;
		this.siteSamplingFishCountHbms = siteSamplingFishCountHbms;
	}

	public int getFishingMethodId() {
		return this.fishingMethodId;
	}

	public void setFishingMethodId(int fishingMethodId) {
		this.fishingMethodId = fishingMethodId;
	}

	public String getFishingMethod() {
		return this.fishingMethod;
	}

	public void setFishingMethod(String fishingMethod) {
		this.fishingMethod = fishingMethod;
	}

	public Set getSiteSamplingFishCountHbms() {
		return this.siteSamplingFishCountHbms;
	}

	public void setSiteSamplingFishCountHbms(Set siteSamplingFishCountHbms) {
		this.siteSamplingFishCountHbms = siteSamplingFishCountHbms;
	}

}
