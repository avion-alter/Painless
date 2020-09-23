package com.ucsf.painless.model.weeklyThree;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("weekly_weekly_pain_catastrophizing_index_question_data")
	private List<WeeklyWeeklyPainCatastrophizingIndexQuestionDataItem> weeklyWeeklyPainCatastrophizingIndexQuestionData;

	@SerializedName("pat_id")
	private String patId;

	public List<WeeklyWeeklyPainCatastrophizingIndexQuestionDataItem> getWeeklyWeeklyPainCatastrophizingIndexQuestionData(){
		return weeklyWeeklyPainCatastrophizingIndexQuestionData;
	}

	public String getPatId(){
		return patId;
	}
}