package com.ucsf.painless.model.monthlyBackDepression;

import com.google.gson.annotations.SerializedName;

public class MonthlyBeckDepressionInventoryQuestionDataItem{

	@SerializedName("bdi_number_answer")
	private String bdiNumberAnswer;

	@SerializedName("bdi_question_number")
	private String bdiQuestionNumber;

	@SerializedName("bdi_answer_6")
	private String bdiAnswer6;

	@SerializedName("bdi_answer_5")
	private String bdiAnswer5;

	@SerializedName("bdi_answer_7")
	private String bdiAnswer7;

	@SerializedName("bdi_answer_2")
	private String bdiAnswer2;

	@SerializedName("bdi_answer_1")
	private String bdiAnswer1;

	@SerializedName("bdi_answer_4")
	private String bdiAnswer4;

	@SerializedName("bdi_answer_3")
	private String bdiAnswer3;

	@SerializedName("bdi_question_description")
	private String bdiQuestionDescription;

	private boolean radioButtonDone=false;
	private String radioButtonSelected;

	public String getBdiQuestionDescription() {
		return bdiQuestionDescription;
	}

	public void setBdiQuestionDescription(String bdiQuestionDescription) {
		this.bdiQuestionDescription = bdiQuestionDescription;
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

	public String getBdiNumberAnswer(){
		return bdiNumberAnswer;
	}

	public String getBdiQuestionNumber(){
		return bdiQuestionNumber;
	}

	public String getBdiAnswer6(){
		return bdiAnswer6;
	}

	public String getBdiAnswer5(){
		return bdiAnswer5;
	}

	public String getBdiAnswer7(){
		return bdiAnswer7;
	}

	public String getBdiAnswer2(){
		return bdiAnswer2;
	}

	public String getBdiAnswer1(){
		return bdiAnswer1;
	}

	public String getBdiAnswer4(){
		return bdiAnswer4;
	}

	public String getBdiAnswer3(){
		return bdiAnswer3;
	}
}