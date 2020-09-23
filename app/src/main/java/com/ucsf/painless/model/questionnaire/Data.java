package com.ucsf.painless.model.questionnaire;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("types_pain_data")
	private List<TypesPainDataItem> typesPainData;

	public List<TypesPainDataItem> getTypesPainData(){
		return typesPainData;
	}
}