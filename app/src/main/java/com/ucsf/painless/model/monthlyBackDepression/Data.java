package com.ucsf.painless.model.monthlyBackDepression;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("monthly_beck_depression_inventory_question_data")
	private List<MonthlyBeckDepressionInventoryQuestionDataItem> monthlyBeckDepressionInventoryQuestionData;

	@SerializedName("pat_id")
	private String patId;

	public List<MonthlyBeckDepressionInventoryQuestionDataItem> getMonthlyBeckDepressionInventoryQuestionData(){
		return monthlyBeckDepressionInventoryQuestionData;
	}

	public String getPatId(){
		return patId;
	}
}