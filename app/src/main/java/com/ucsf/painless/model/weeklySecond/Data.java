package com.ucsf.painless.model.weeklySecond;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("pat_id")
	private String patId;

	@SerializedName("weekly_weekly_IMS_12_question_data")
	private List<WeeklyWeeklyIMS12QuestionDataItem> weeklyWeeklyIMS12QuestionData;

	public String getPatId(){
		return patId;
	}

	public List<WeeklyWeeklyIMS12QuestionDataItem> getWeeklyWeeklyIMS12QuestionData(){
		return weeklyWeeklyIMS12QuestionData;
	}
}