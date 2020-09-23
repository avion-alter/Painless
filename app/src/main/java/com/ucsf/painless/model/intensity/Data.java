package com.ucsf.painless.model.intensity;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("inrt_id")
	private int inrtId;

	@SerializedName("pat_id")
	private String patId;

	public int getInrtId(){
		return inrtId;
	}

	public String getPatId(){
		return patId;
	}
}