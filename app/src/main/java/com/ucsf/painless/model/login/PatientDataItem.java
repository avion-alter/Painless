package com.ucsf.painless.model.login;

import com.google.gson.annotations.SerializedName;

public class PatientDataItem{

	@SerializedName("pat_authorizecode")
	private String patAuthorizecode;

	@SerializedName("pat_email")
	private String patEmail;

	@SerializedName("pat_fname")
	private String patFname;

	@SerializedName("pat_id")
	private String patId;

	@SerializedName("pat_lname")
	private String patLname;

	@SerializedName("pat_gender")
	private String patGender;

	public String getPatAuthorizecode(){
		return patAuthorizecode;
	}

	public String getPatEmail(){
		return patEmail;
	}

	public String getPatFname(){
		return patFname;
	}

	public String getPatId(){
		return patId;
	}

	public String getPatLname(){
		return patLname;
	}

	public String getPatGender() {
		return patGender;
	}

	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
}