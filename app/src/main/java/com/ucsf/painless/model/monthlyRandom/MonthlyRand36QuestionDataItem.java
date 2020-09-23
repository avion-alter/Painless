package com.ucsf.painless.model.monthlyRandom;

import com.google.gson.annotations.SerializedName;

public class MonthlyRand36QuestionDataItem{

	@SerializedName("mrque_question_description")
	private String mrqueQuestionDescription;

	@SerializedName("mrque_number_answer")
	private String mrqueNumberAnswer;

	@SerializedName("mrque_question")
	private String mrqueQuestion;

	@SerializedName("mrque_answer_7")
	private String mrqueAnswer7;

	@SerializedName("mrque_answer_6")
	private String mrqueAnswer6;

	@SerializedName("mrque_answer_5")
	private String mrqueAnswer5;

	@SerializedName("mrque_answer_4")
	private String mrqueAnswer4;

	@SerializedName("mrque_question_number")
	private String mrqueQuestionNumber;

	@SerializedName("mrque_answer_3")
	private String mrqueAnswer3;

	@SerializedName("mrque_answer_2")
	private String mrqueAnswer2;

	@SerializedName("mrque_answer_1")
	private String mrqueAnswer1;


	private boolean radioButtonDone=false;
	private String radioButtonSelected;


	public boolean isRadioButtonDone() {
		return radioButtonDone;
	}

	public void setRadioButtonDone(boolean radioButtonDone) {
		this.radioButtonDone = radioButtonDone;
	}

	public String getRadioButtonSelected() {
		return radioButtonSelected;
	}

	public void setRadioButtonSelected(String radioButtonSelected) {
		this.radioButtonSelected = radioButtonSelected;
	}

	public String getMrqueQuestionDescription(){
		return mrqueQuestionDescription;
	}

	public String getMrqueNumberAnswer(){
		return mrqueNumberAnswer;
	}

	public String getMrqueQuestion(){
		return mrqueQuestion;
	}

	public String getMrqueAnswer7(){
		return mrqueAnswer7;
	}

	public String getMrqueAnswer6(){
		return mrqueAnswer6;
	}

	public String getMrqueAnswer5(){
		return mrqueAnswer5;
	}

	public String getMrqueAnswer4(){
		return mrqueAnswer4;
	}

	public String getMrqueQuestionNumber(){
		return mrqueQuestionNumber;
	}

	public String getMrqueAnswer3(){
		return mrqueAnswer3;
	}

	public String getMrqueAnswer2(){
		return mrqueAnswer2;
	}

	public String getMrqueAnswer1(){
		return mrqueAnswer1;
	}
}