package com.ucsf.painless.model.weeklyOne;

import com.google.gson.annotations.SerializedName;

public class WeeklyBriefPainInventoryShortFormQuestionItem{

	@SerializedName("wbpisf_question")
	private String wbpisfQuestion;

	@SerializedName("wbpisf_number_answer")
	private String wbpisfNumberAnswer;

	@SerializedName("wbpisf_question_number")
	private String wbpisfQuestionNumber;

	@SerializedName("wbpisf_answer_type")
	private String wbpisfAnswerType;

	@SerializedName("wbpisf_id")
	private String wbpisfId;

	public String getWbpisfQuestion(){
		return wbpisfQuestion;
	}

	public String getWbpisfNumberAnswer(){
		return wbpisfNumberAnswer;
	}

	public String getWbpisfQuestionNumber(){
		return wbpisfQuestionNumber;
	}

	public String getWbpisfAnswerType(){
		return wbpisfAnswerType;
	}

	public String getWbpisfId(){
		return wbpisfId;
	}
}