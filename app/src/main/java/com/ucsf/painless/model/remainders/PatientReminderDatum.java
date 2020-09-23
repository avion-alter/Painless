
package com.ucsf.painless.model.remainders;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@SuppressWarnings("unused")
public class PatientReminderDatum implements Serializable {

    @SerializedName("reminder_active_status")
    private String mReminderActiveStatus;
    @SerializedName("reminder_month_day")
    private String mReminderMonthDay;
    @SerializedName("reminder_time")
    private String mReminderTime;
    @SerializedName("reminder_type")
    private String mReminderType;
    @SerializedName("reminder_week_day")
    private String mReminderWeekDay;

    public String getReminderActiveStatus() {
        return mReminderActiveStatus;
    }

    public void setReminderActiveStatus(String reminderActiveStatus) {
        mReminderActiveStatus = reminderActiveStatus;
    }

    public String getReminderMonthDay() {
        return mReminderMonthDay;
    }

    public void setReminderMonthDay(String reminderMonthDay) {
        mReminderMonthDay = reminderMonthDay;
    }

    public String getReminderTime() {
        return mReminderTime;
    }

    public void setReminderTime(String reminderTime) {
        mReminderTime = reminderTime;
    }

    public String getReminderType() {
        return mReminderType;
    }

    public void setReminderType(String reminderType) {
        mReminderType = reminderType;
    }

    public String getReminderWeekDay() {
        return mReminderWeekDay;
    }

    public void setReminderWeekDay(String reminderWeekDay) {
        mReminderWeekDay = reminderWeekDay;
    }

}
