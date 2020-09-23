
package com.ucsf.painless.model.remainders;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Data implements Serializable {

    @SerializedName("pat_id")
    private String mPatId;
    @SerializedName("patient_reminder_data")
    private List<PatientReminderDatum> mPatientReminderData;

    public String getPatId() {
        return mPatId;
    }

    public void setPatId(String patId) {
        mPatId = patId;
    }

    public List<PatientReminderDatum> getPatientReminderData() {
        return mPatientReminderData;
    }

    public void setPatientReminderData(List<PatientReminderDatum> patientReminderData) {
        mPatientReminderData = patientReminderData;
    }

}
