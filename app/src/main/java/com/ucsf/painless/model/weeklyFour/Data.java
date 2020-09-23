package com.ucsf.painless.model.weeklyFour;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("pat_id")
	private String patId;

	@SerializedName("weekly_columbia_suicide_severity_rating_scale_question_data")
	private List<WeeklyColumbiaSuicideSeverityRatingScaleQuestionDataItem> weeklyColumbiaSuicideSeverityRatingScaleQuestionData;

	public String getPatId(){
		return patId;
	}

	public List<WeeklyColumbiaSuicideSeverityRatingScaleQuestionDataItem> getWeeklyColumbiaSuicideSeverityRatingScaleQuestionData(){
		return weeklyColumbiaSuicideSeverityRatingScaleQuestionData;
	}
}