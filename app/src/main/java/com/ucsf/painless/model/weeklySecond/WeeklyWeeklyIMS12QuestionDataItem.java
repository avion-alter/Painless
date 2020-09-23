package com.ucsf.painless.model.weeklySecond;

import com.google.gson.annotations.SerializedName;

public class WeeklyWeeklyIMS12QuestionDataItem{

	@SerializedName("wims_start_text")
	private String wimsStartText;

	@SerializedName("wims_question")
	private String wimsQuestion;

	@SerializedName("wims_answer_type")
	private String wimsAnswerType;

	@SerializedName("wims_end_text")
	private String wimsEndText;

	@SerializedName("wims_id")
	private String wimsId;

	@SerializedName("wims_question_number")
	private String wimsQuestionNumber;

	@SerializedName("wims_number_answer")
	private String wimsNumberAnswer;

	public String getWimsStartText(){
		return wimsStartText;
	}

	public String getWimsQuestion(){
		return wimsQuestion;
	}

	public String getWimsAnswerType(){
		return wimsAnswerType;
	}

	public String getWimsEndText(){
		return wimsEndText;
	}

	public String getWimsId(){
		return wimsId;
	}

	public String getWimsQuestionNumber(){
		return wimsQuestionNumber;
	}

	public String getWimsNumberAnswer(){
		return wimsNumberAnswer;
	}
}