package com.ucsf.painless.model.weeklyFour;

import com.google.gson.annotations.SerializedName;

public class WeeklyColumbiaSuicideSeverityRatingScaleQuestionDataItem{

	@SerializedName("cssrs_question_number")
	private String cssrsQuestionNumber;

	@SerializedName("cssrs_number_answer")
	private String cssrsNumberAnswer;

	@SerializedName("cssrs_answer_type")
	private String cssrsAnswerType;

	@SerializedName("cssrs_answer_1")
	private String cssrsAnswer1;

	@SerializedName("cssrs_question")
	private String cssrsQuestion;

	@SerializedName("cssrs_id")
	private String cssrsId;

	@SerializedName("cssrs_answer_2")
	private String cssrsAnswer2;

	public String getCssrsQuestionNumber(){
		return cssrsQuestionNumber;
	}

	public String getCssrsNumberAnswer(){
		return cssrsNumberAnswer;
	}

	public String getCssrsAnswerType(){
		return cssrsAnswerType;
	}

	public String getCssrsAnswer1(){
		return cssrsAnswer1;
	}

	public String getCssrsQuestion(){
		return cssrsQuestion;
	}

	public String getCssrsId(){
		return cssrsId;
	}

	public String getCssrsAnswer2(){
		return cssrsAnswer2;
	}
}