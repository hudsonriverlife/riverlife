package edu.columbia.riverLife.dal.implementation.hibernate.bean;

// Generated Oct 16, 2016 7:26:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * ChemistryHbm generated by hbm2java
 */
public class ChemistryHbm implements java.io.Serializable {

	private int chemistryId;
	private SiteSamplingHbm siteSamplingHbm;
	private String name;
	private Date sampleTime;
	private Double doPpm;
	private Double waterTemperature;
	private Integer saturationPercentage;
	private Double ph;
	private Double nitratesPpm;
	private Double phosphatesPpm;
	private Double alkalinityPpm;
	private String notes;

	public ChemistryHbm() {
	}

	public ChemistryHbm(int chemistryId, SiteSamplingHbm siteSamplingHbm,
			String name) {
		this.chemistryId = chemistryId;
		this.siteSamplingHbm = siteSamplingHbm;
		this.name = name;
	}

	public ChemistryHbm(int chemistryId, SiteSamplingHbm siteSamplingHbm,
			String name, Date sampleTime, Double doPpm,
			Double waterTemperature, Integer saturationPercentage, Double ph,
			Double nitratesPpm, Double phosphatesPpm, Double alkalinityPpm,
			String notes) {
		this.chemistryId = chemistryId;
		this.siteSamplingHbm = siteSamplingHbm;
		this.name = name;
		this.sampleTime = sampleTime;
		this.doPpm = doPpm;
		this.waterTemperature = waterTemperature;
		this.saturationPercentage = saturationPercentage;
		this.ph = ph;
		this.nitratesPpm = nitratesPpm;
		this.phosphatesPpm = phosphatesPpm;
		this.alkalinityPpm = alkalinityPpm;
		this.notes = notes;
	}

	public int getChemistryId() {
		return this.chemistryId;
	}

	public void setChemistryId(int chemistryId) {
		this.chemistryId = chemistryId;
	}

	public SiteSamplingHbm getSiteSamplingHbm() {
		return this.siteSamplingHbm;
	}

	public void setSiteSamplingHbm(SiteSamplingHbm siteSamplingHbm) {
		this.siteSamplingHbm = siteSamplingHbm;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSampleTime() {
		return this.sampleTime;
	}

	public void setSampleTime(Date sampleTime) {
		this.sampleTime = sampleTime;
	}

	public Double getDoPpm() {
		return this.doPpm;
	}

	public void setDoPpm(Double doPpm) {
		this.doPpm = doPpm;
	}

	public Double getWaterTemperature() {
		return this.waterTemperature;
	}

	public void setWaterTemperature(Double waterTemperature) {
		this.waterTemperature = waterTemperature;
	}

	public Integer getSaturationPercentage() {
		return this.saturationPercentage;
	}

	public void setSaturationPercentage(Integer saturationPercentage) {
		this.saturationPercentage = saturationPercentage;
	}

	public Double getPh() {
		return this.ph;
	}

	public void setPh(Double ph) {
		this.ph = ph;
	}

	public Double getNitratesPpm() {
		return this.nitratesPpm;
	}

	public void setNitratesPpm(Double nitratesPpm) {
		this.nitratesPpm = nitratesPpm;
	}

	public Double getPhosphatesPpm() {
		return this.phosphatesPpm;
	}

	public void setPhosphatesPpm(Double phosphatesPpm) {
		this.phosphatesPpm = phosphatesPpm;
	}

	public Double getAlkalinityPpm() {
		return this.alkalinityPpm;
	}

	public void setAlkalinityPpm(Double alkalinityPpm) {
		this.alkalinityPpm = alkalinityPpm;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
