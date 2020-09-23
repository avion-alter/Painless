package com.ucsf.painless.model.weeklyOne;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("pat_id")
	private String patId;

	@SerializedName("weekly_brief_pain_inventory_short_form_question")
	private List<WeeklyBriefPainInventoryShortFormQuestionItem> weeklyBriefPainInventoryShortFormQuestion;

	public String getPatId(){
		return patId;
	}

	public List<WeeklyBriefPainInventoryShortFormQuestionItem> getWeeklyBriefPainInventoryShortFormQuestion(){
		return weeklyBriefPainInventoryShortFormQuestion;
	}
}