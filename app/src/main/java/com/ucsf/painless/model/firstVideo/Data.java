package com.ucsf.painless.model.firstVideo;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("pvdo_id")
	private int pvdoId;

	@SerializedName("pat_id")
	private String patId;

	public int getPvdoId(){
		return pvdoId;
	}

	public String getPatId(){
		return patId;
	}
}