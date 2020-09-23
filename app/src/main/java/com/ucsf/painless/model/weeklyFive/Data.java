package com.ucsf.painless.model.weeklyFive;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("weekly_event_reporting_question_data")
	private List<WeeklyEventReportingQuestionDataItem> weeklyEventReportingQuestionData;

	@SerializedName("pat_id")
	private String patId;

	public List<WeeklyEventReportingQuestionDataItem> getWeeklyEventReportingQuestionData(){
		return weeklyEventReportingQuestionData;
	}

	public String getPatId(){
		return patId;
	}
}