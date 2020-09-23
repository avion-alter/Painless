package com.ucsf.painless.model.monthlyBeckAnxiety;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("monthly_beck_anxiety_inventory_question_data")
	private List<MonthlyBeckAnxietyInventoryQuestionDataItem> monthlyBeckAnxietyInventoryQuestionData;

	@SerializedName("pat_id")
	private String patId;

	public List<MonthlyBeckAnxietyInventoryQuestionDataItem> getMonthlyBeckAnxietyInventoryQuestionData(){
		return monthlyBeckAnxietyInventoryQuestionData;
	}

	public String getPatId(){
		return patId;
	}
}