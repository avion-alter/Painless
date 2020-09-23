
package com.ucsf.painless.model.clipBoard;

import java.util.List;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Data {

    @SerializedName("pat_id")
    private String mPatId;
    @SerializedName("patient_previous_map_data")
    private List<PatientPreviousMapDatum> mPatientPreviousMapData;

    public String getPatId() {
        return mPatId;
    }

    public void setPatId(String patId) {
        mPatId = patId;
    }

    public List<PatientPreviousMapDatum> getPatientPreviousMapData() {
        return mPatientPreviousMapData;
    }

    public void setPatientPreviousMapData(List<PatientPreviousMapDatum> patientPreviousMapData) {
        mPatientPreviousMapData = patientPreviousMapData;
    }

}
