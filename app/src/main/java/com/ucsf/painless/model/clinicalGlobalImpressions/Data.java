package com.ucsf.painless.model.clinicalGlobalImpressions;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("monthly_clinical_global_impression_scales_question_data")
	private List<MonthlyClinicalGlobalImpressionScalesQuestionDataItem> monthlyClinicalGlobalImpressionScalesQuestionData;

	@SerializedName("pat_id")
	private String patId;

	public List<MonthlyClinicalGlobalImpressionScalesQuestionDataItem> getMonthlyClinicalGlobalImpressionScalesQuestionData(){
		return monthlyClinicalGlobalImpressionScalesQuestionData;
	}

	public String getPatId(){
		return patId;
	}
}