package edu.columbia.riverLife.dal.implementation.hibernate.bean;

// Generated Oct 16, 2016 7:26:15 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * SalinityMethodHbm generated by hbm2java
 */
public class SalinityMethodHbm implements java.io.Serializable {

	private int salinityMethodId;
	private String method;
	private Set studentSalinityHbms = new HashSet(0);
	private Set standardizedSalinityHbms = new HashSet(0);

	public SalinityMethodHbm() {
	}

	public SalinityMethodHbm(int salinityMethodId, String method) {
		this.salinityMethodId = salinityMethodId;
		this.method = method;
	}

	public SalinityMethodHbm(int salinityMethodId, String method,
			Set studentSalinityHbms, Set standardizedSalinityHbms) {
		this.salinityMethodId = salinityMethodId;
		this.method = method;
		this.studentSalinityHbms = studentSalinityHbms;
		this.standardizedSalinityHbms = standardizedSalinityHbms;
	}

	public int getSalinityMethodId() {
		return this.salinityMethodId;
	}

	public void setSalinityMethodId(int salinityMethodId) {
		this.salinityMethodId = salinityMethodId;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Set getStudentSalinityHbms() {
		return this.studentSalinityHbms;
	}

	public void setStudentSalinityHbms(Set studentSalinityHbms) {
		this.studentSalinityHbms = studentSalinityHbms;
	}

	public Set getStandardizedSalinityHbms() {
		return this.standardizedSalinityHbms;
	}

	public void setStandardizedSalinityHbms(Set standardizedSalinityHbms) {
		this.standardizedSalinityHbms = standardizedSalinityHbms;
	}

}
