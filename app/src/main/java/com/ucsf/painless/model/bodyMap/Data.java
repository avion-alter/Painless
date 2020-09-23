package com.ucsf.painless.model.bodyMap;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("inrt_id")
	private String inrtId;

	@SerializedName("pat_id")
	private String patId;

	public String getInrtId(){
		return inrtId;
	}

	public String getPatId(){
		return patId;
	}
}