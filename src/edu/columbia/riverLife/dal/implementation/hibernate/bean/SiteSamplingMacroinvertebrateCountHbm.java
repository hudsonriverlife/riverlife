package edu.columbia.riverLife.dal.implementation.hibernate.bean;

// Generated Oct 16, 2016 7:26:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * SiteSamplingMacroinvertebrateCountHbm generated by hbm2java
 */
public class SiteSamplingMacroinvertebrateCountHbm implements
		java.io.Serializable {

	private int siteSamplingMacroinvertebrateId;
	private MacroinvertebrateCollectionMethodHbm macroinvertebrateCollectionMethodHbm;
	private MacroinvertebrateHbm macroinvertebrateHbm;
	private SiteSamplingHbm siteSamplingHbm;
	private Date countTime;
	private int amount;

	public SiteSamplingMacroinvertebrateCountHbm() {
	}

	public SiteSamplingMacroinvertebrateCountHbm(
			int siteSamplingMacroinvertebrateId,
			MacroinvertebrateCollectionMethodHbm macroinvertebrateCollectionMethodHbm,
			MacroinvertebrateHbm macroinvertebrateHbm,
			SiteSamplingHbm siteSamplingHbm, int amount) {
		this.siteSamplingMacroinvertebrateId = siteSamplingMacroinvertebrateId;
		this.macroinvertebrateCollectionMethodHbm = macroinvertebrateCollectionMethodHbm;
		this.macroinvertebrateHbm = macroinvertebrateHbm;
		this.siteSamplingHbm = siteSamplingHbm;
		this.amount = amount;
	}

	public SiteSamplingMacroinvertebrateCountHbm(
			int siteSamplingMacroinvertebrateId,
			MacroinvertebrateCollectionMethodHbm macroinvertebrateCollectionMethodHbm,
			MacroinvertebrateHbm macroinvertebrateHbm,
			SiteSamplingHbm siteSamplingHbm, Date countTime, int amount) {
		this.siteSamplingMacroinvertebrateId = siteSamplingMacroinvertebrateId;
		this.macroinvertebrateCollectionMethodHbm = macroinvertebrateCollectionMethodHbm;
		this.macroinvertebrateHbm = macroinvertebrateHbm;
		this.siteSamplingHbm = siteSamplingHbm;
		this.countTime = countTime;
		this.amount = amount;
	}

	public int getSiteSamplingMacroinvertebrateId() {
		return this.siteSamplingMacroinvertebrateId;
	}

	public void setSiteSamplingMacroinvertebrateId(
			int siteSamplingMacroinvertebrateId) {
		this.siteSamplingMacroinvertebrateId = siteSamplingMacroinvertebrateId;
	}

	public MacroinvertebrateCollectionMethodHbm getMacroinvertebrateCollectionMethodHbm() {
		return this.macroinvertebrateCollectionMethodHbm;
	}

	public void setMacroinvertebrateCollectionMethodHbm(
			MacroinvertebrateCollectionMethodHbm macroinvertebrateCollectionMethodHbm) {
		this.macroinvertebrateCollectionMethodHbm = macroinvertebrateCollectionMethodHbm;
	}

	public MacroinvertebrateHbm getMacroinvertebrateHbm() {
		return this.macroinvertebrateHbm;
	}

	public void setMacroinvertebrateHbm(
			MacroinvertebrateHbm macroinvertebrateHbm) {
		this.macroinvertebrateHbm = macroinvertebrateHbm;
	}

	public SiteSamplingHbm getSiteSamplingHbm() {
		return this.siteSamplingHbm;
	}

	public void setSiteSamplingHbm(SiteSamplingHbm siteSamplingHbm) {
		this.siteSamplingHbm = siteSamplingHbm;
	}

	public Date getCountTime() {
		return this.countTime;
	}

	public void setCountTime(Date countTime) {
		this.countTime = countTime;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
