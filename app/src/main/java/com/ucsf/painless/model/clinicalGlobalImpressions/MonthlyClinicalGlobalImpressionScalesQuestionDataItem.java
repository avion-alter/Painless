package com.ucsf.painless.model.clinicalGlobalImpressions;

import com.google.gson.annotations.SerializedName;

public class MonthlyClinicalGlobalImpressionScalesQuestionDataItem{

	@SerializedName("cgis_question")
	private String cgisQuestion;

	@SerializedName("cgis_number_answer")
	private String cgisNumberAnswer;

	@SerializedName("cgis_question_number")
	private String cgisQuestionNumber;

	@SerializedName("cgis_answer_6")
	private String cgisAnswer6;

	@SerializedName("cgis_answer_7")
	private String cgisAnswer7;

	@SerializedName("cgis_answer_4")
	private String cgisAnswer4;

	@SerializedName("cgis_answer_5")
	private String cgisAnswer5;

	@SerializedName("cgis_answer_2")
	private String cgisAnswer2;

	@SerializedName("cgis_answer_3")
	private String cgisAnswer3;

	@SerializedName("cgis_answer_1")
	private String cgisAnswer1;

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

	public String getCgisQuestion(){
		return cgisQuestion;
	}

	public String getCgisNumberAnswer(){
		return cgisNumberAnswer;
	}

	public String getCgisQuestionNumber(){
		return cgisQuestionNumber;
	}

	public String getCgisAnswer6(){
		return cgisAnswer6;
	}

	public String getCgisAnswer7(){
		return cgisAnswer7;
	}

	public String getCgisAnswer4(){
		return cgisAnswer4;
	}

	public String getCgisAnswer5(){
		return cgisAnswer5;
	}

	public String getCgisAnswer2(){
		return cgisAnswer2;
	}

	public String getCgisAnswer3(){
		return cgisAnswer3;
	}

	public String getCgisAnswer1(){
		return cgisAnswer1;
	}
}