
package com.ucsf.painless.model.clipBoard;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class PatientPreviousMapDatum {

    @SerializedName("body_part_name")
    private String mBodyPartName;
    @SerializedName("bpm_body_name")
    private String mBpmBodyName;
    @SerializedName("bpm_color")
    private String mBpmColor;
    @SerializedName("bpm_create_date")
    private String mBpmCreateDate;
    @SerializedName("bpm_front_back")
    private String mBpmFrontBack;
    @SerializedName("bpm_id")
    private String mBpmId;
    @SerializedName("bpm_inrt_id")
    private String mBpmInrtId;
    @SerializedName("bpm_pat_id")
    private String mBpmPatId;
    @SerializedName("bpm_right_left")
    private String mBpmRightLeft;

    public String getBodyPartName() {
        return mBodyPartName;
    }

    public void setBodyPartName(String bodyPartName) {
        mBodyPartName = bodyPartName;
    }

    public String getBpmBodyName() {
        return mBpmBodyName;
    }

    public void setBpmBodyName(String bpmBodyName) {
        mBpmBodyName = bpmBodyName;
    }

    public String getBpmColor() {
        return mBpmColor;
    }

    public void setBpmColor(String bpmColor) {
        mBpmColor = bpmColor;
    }

    public String getBpmCreateDate() {
        return mBpmCreateDate;
    }

    public void setBpmCreateDate(String bpmCreateDate) {
        mBpmCreateDate = bpmCreateDate;
    }

    public String getBpmFrontBack() {
        return mBpmFrontBack;
    }

    public void setBpmFrontBack(String bpmFrontBack) {
        mBpmFrontBack = bpmFrontBack;
    }

    public String getBpmId() {
        return mBpmId;
    }

    public void setBpmId(String bpmId) {
        mBpmId = bpmId;
    }

    public String getBpmInrtId() {
        return mBpmInrtId;
    }

    public void setBpmInrtId(String bpmInrtId) {
        mBpmInrtId = bpmInrtId;
    }

    public String getBpmPatId() {
        return mBpmPatId;
    }

    public void setBpmPatId(String bpmPatId) {
        mBpmPatId = bpmPatId;
    }

    public String getBpmRightLeft() {
        return mBpmRightLeft;
    }

    public void setBpmRightLeft(String bpmRightLeft) {
        mBpmRightLeft = bpmRightLeft;
    }

}
