package com.ucsf.painless.model.login;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("patient_data")
	private List<PatientDataItem> patientData;

	public List<PatientDataItem> getPatientData(){
		return patientData;
	}
}