package com.ucsf.painless.model.monthlyBeckAnxiety;

import com.google.gson.annotations.SerializedName;

public class MonthlyBeckAnxietyInventoryQuestionDataItem{

	@SerializedName("bai_answer_2")
	private String baiAnswer2;

	@SerializedName("bai_answer_3")
	private String baiAnswer3;

	@SerializedName("bai_answer_1")
	private String baiAnswer1;

	@SerializedName("bai_question")
	private String baiQuestion;

	@SerializedName("bai_question_number")
	private String baiQuestionNumber;

	@SerializedName("bai_number_answer")
	private String baiNumberAnswer;

	@SerializedName("bai_answer_4")
	private String baiAnswer4;

	@SerializedName("bai_question_description")
	private String baiQuestionDescription;

	private boolean radioButtonDone=false;
	private String radioButtonSelected;

	public String getBaiQuestionDescription() {
		return baiQuestionDescription;
	}

	public void setBaiQuestionDescription(String baiQuestionDescription) {
		this.baiQuestionDescription = baiQuestionDescription;
	}

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

	public String getBaiAnswer2(){
		return baiAnswer2;
	}

	public String getBaiAnswer3(){
		return baiAnswer3;
	}

	public String getBaiAnswer1(){
		return baiAnswer1;
	}

	public String getBaiQuestion(){
		return baiQuestion;
	}

	public String getBaiQuestionNumber(){
		return baiQuestionNumber;
	}

	public String getBaiNumberAnswer(){
		return baiNumberAnswer;
	}

	public String getBaiAnswer4(){
		return baiAnswer4;
	}
}