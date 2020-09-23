
package com.ucsf.painless.model.dashboard;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Data {

    @SerializedName("body_pain_maps_completed")
    private Long mBodyPainMapsCompleted;
    @SerializedName("color_1")
    private String mColor1;
    @SerializedName("color_2")
    private String mColor2;
    @SerializedName("daily_pain_reports_completed")
    private Long mDailyPainReportsCompleted;
    @SerializedName("monthly_pain_reports_completed")
    private Long mMonthlyPainReportsCompleted;
    @SerializedName("pat_id")
    private String mPatId;
    @SerializedName("total_reports_reports_completed")
    private Long mTotalReportsReportsCompleted;
    @SerializedName("weekly_pain_reports_completed")
    private Long mWeeklyPainReportsCompleted;

    public Long getBodyPainMapsCompleted() {
        return mBodyPainMapsCompleted;
    }

    public void setBodyPainMapsCompleted(Long bodyPainMapsCompleted) {
        mBodyPainMapsCompleted = bodyPainMapsCompleted;
    }

    public String getColor1() {
        return mColor1;
    }

    public void setColor1(String color1) {
        mColor1 = color1;
    }

    public String getColor2() {
        return mColor2;
    }

    public void setColor2(String color2) {
        mColor2 = color2;
    }

    public Long getDailyPainReportsCompleted() {
        return mDailyPainReportsCompleted;
    }

    public void setDailyPainReportsCompleted(Long dailyPainReportsCompleted) {
        mDailyPainReportsCompleted = dailyPainReportsCompleted;
    }

    public Long getMonthlyPainReportsCompleted() {
        return mMonthlyPainReportsCompleted;
    }

    public void setMonthlyPainReportsCompleted(Long monthlyPainReportsCompleted) {
        mMonthlyPainReportsCompleted = monthlyPainReportsCompleted;
    }

    public String getPatId() {
        return mPatId;
    }

    public void setPatId(String patId) {
        mPatId = patId;
    }

    public Long getTotalReportsReportsCompleted() {
        return mTotalReportsReportsCompleted;
    }

    public void setTotalReportsReportsCompleted(Long totalReportsReportsCompleted) {
        mTotalReportsReportsCompleted = totalReportsReportsCompleted;
    }

    public Long getWeeklyPainReportsCompleted() {
        return mWeeklyPainReportsCompleted;
    }

    public void setWeeklyPainReportsCompleted(Long weeklyPainReportsCompleted) {
        mWeeklyPainReportsCompleted = weeklyPainReportsCompleted;
    }

}
