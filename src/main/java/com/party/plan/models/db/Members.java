package com.party.plan.models.db;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
public class Members implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private String emailId;
	private String name;
	private Date dob;
	private String vorous;
	private boolean isAdmin;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getVorous() {
		return vorous;
	}

	public void setVorous(String vorous) {
		this.vorous = vorous;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
