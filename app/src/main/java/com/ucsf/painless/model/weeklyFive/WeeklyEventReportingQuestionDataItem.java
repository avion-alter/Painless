package com.ucsf.painless.model.weeklyFive;

import com.google.gson.annotations.SerializedName;

public class WeeklyEventReportingQuestionDataItem{

	@SerializedName("wer_name")
	private String werName;

	@SerializedName("wer_id")
	private String werId;

	public String getWerName(){
		return werName;
	}

	public String getWerId(){
		return werId;
	}
}