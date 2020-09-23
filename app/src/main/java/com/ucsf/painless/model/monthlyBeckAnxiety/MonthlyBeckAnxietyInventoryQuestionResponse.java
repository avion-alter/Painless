package com.ucsf.painless.model.monthlyBeckAnxiety;

import com.google.gson.annotations.SerializedName;

public class MonthlyBeckAnxietyInventoryQuestionResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}