package com.ucsf.painless.model.settings;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("pat_id")
	private String patId;

	public String getPatId(){
		return patId;
	}
}