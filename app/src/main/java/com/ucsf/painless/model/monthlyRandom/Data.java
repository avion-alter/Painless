package com.ucsf.painless.model.monthlyRandom;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("monthly_rand_36_question_data")
	private List<MonthlyRand36QuestionDataItem> monthlyRand36QuestionData;

	@SerializedName("pat_id")
	private String patId;

	public List<MonthlyRand36QuestionDataItem> getMonthlyRand36QuestionData(){
		return monthlyRand36QuestionData;
	}

	public String getPatId(){
		return patId;
	}
}