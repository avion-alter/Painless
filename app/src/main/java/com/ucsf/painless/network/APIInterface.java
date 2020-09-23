package com.ucsf.painless.network;


import com.ucsf.painless.model.bodyMap.BodyMapResponse;
import com.ucsf.painless.model.clinicalGlobalImpressions.ClinicalGlobalImpressionsResponse;
import com.ucsf.painless.model.clipBoard.ClipBoardResponse;
import com.ucsf.painless.model.dashboard.DashBoardResponse;
import com.ucsf.painless.model.firstVideo.FirstVideoResponse;
import com.ucsf.painless.model.intensity.IntensityResponse;
import com.ucsf.painless.model.login.LoginResponse;
import com.ucsf.painless.model.monthlyBackDepression.MonthlyBeckDepressionResponse;
import com.ucsf.painless.model.monthlyBeckAnxiety.MonthlyBeckAnxietyInventoryQuestionResponse;
import com.ucsf.painless.model.monthlyRandom.MonthlyRandomResponse;
import com.ucsf.painless.model.questionnaire.McGillQuestionnaireResponse;
import com.ucsf.painless.model.remainders.ReminderResponce;
import com.ucsf.painless.model.saveBodyMapToWeb.SaveWebView;
import com.ucsf.painless.model.saveMonthlyReport.SaveMonthlyResponse;
import com.ucsf.painless.model.saveQuestionnaire.SaveMcGillPainQuesionnaireResponse;
import com.ucsf.painless.model.saveRemainder.SaveReminderResponse;
import com.ucsf.painless.model.saveWeekly.SaveWeeklyResponse;
import com.ucsf.painless.model.settings.SaveSettingsResponse;
import com.ucsf.painless.model.weeklyFive.WeeklyFiveResponse;
import com.ucsf.painless.model.weeklyFour.WeeklyFourResponse;
import com.ucsf.painless.model.weeklyOne.WeeklyOneResponse;
import com.ucsf.painless.model.weeklySecond.WeeklyTwoResponse;
import com.ucsf.painless.model.weeklyThree.WeeklyThreeResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {

    @FormUrlEncoded
    @POST("patient_login?")
    Call<LoginResponse> signIn(@Field("pat_email") String email, @Field("pat_password") String password, @Field("pat_device_type") String device_type, @Field("pat_fcm_toke") String fcm_id, @Field("pat_device_uuid") String pat_device_uuid);

    @FormUrlEncoded
    @POST("patient_dashboard?")
    Call<DashBoardResponse> getDashboardInfo(@Field("pat_id") String email, @Field("pat_device_type") String device_type, @Field("pat_fcm_toke") String fcm_id);

    @FormUrlEncoded
    @POST("save_patient_intensity_rating?")
    Call<IntensityResponse> saveIntensityRating(@Field("pat_id") String pat_id, @Field("inrt_pain_in") String inrt_pain_in, @Field("inrt_painrelief") String inrt_painrelief, @Field("inrt_unpleasantness") String inrt_unpleasantness);

    @FormUrlEncoded
    @POST("save_patient_mood_VAS_rating?")
    Call<IntensityResponse> saveMoodRating(@Field("pat_id") String pat_id, @Field("inrt_id") String inrt_id, @Field("daily_mood_VAS_rating_value_json") String daily_mood_VAS_rating_value_json);

    @FormUrlEncoded
    @POST("types_of_pain?")
    Call<McGillQuestionnaireResponse> getQuestionnaire(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("save_body_pain_map?")
    Call<BodyMapResponse> saveBodyMap(@Field("inrt_id") String inrt_id, @Field("pat_id") String pat_id, @Field("body_pain_map_value_json") String body_pain_map_value_json);

    @FormUrlEncoded
    @POST("save_mcgill_pain_questionnaire?")
    Call<SaveMcGillPainQuesionnaireResponse> saveMcGillPainQuesionnaire(@Field("pat_id") String pat_id,@Field("inrt_id") String inrt_id,@Field("mcgill_pain_questionnaire_value_json") String mcgill_pain_questionnaire_value_json);


    @Multipart
    @POST("save_patient_video_one?")
    Call<FirstVideoResponse> savePatientVideoOne(@Part MultipartBody.Part video_file,
                                                 @Part ("pat_id") Integer pat_id);

    @Multipart
    @POST("save_patient_video_second_thrid?")
    Call<FirstVideoResponse> savePatientVideoTwo(@Part MultipartBody.Part video_file,
                                                 @Part ("pat_id") Integer pat_id,
                                                 @Part ("pvdo_id") Integer pvdo_id,
                                                 @Part ("second_thrid") String second_thrid);

    @FormUrlEncoded
    @POST("monthly_rand_36_question?")
    Call<MonthlyRandomResponse> getMonthlyRandQues(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("monthly_beck_anxiety_inventory_question?")
    Call<MonthlyBeckAnxietyInventoryQuestionResponse> getMonthlyBeckAnxietyInventoryQuestion(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("monthly_beck_depression_inventory_question?")
    Call<MonthlyBeckDepressionResponse> getMonthlyBeckDepression(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("monthly_clinical_global_impression_scales_question?")
    Call<ClinicalGlobalImpressionsResponse> getClinicalGlobalImpressionsQuestion(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("save_patient_monthly_rand_36_answer?")
    Call<SaveMonthlyResponse> saveMonthly(@Field("pat_id") String pat_id,@Field("monthly_rand_36_answer_value_json") String monthly_rand_36_answer_value_json);

    @FormUrlEncoded
    @POST("save_patient_clinical_global_impression_scales_answer?")
    Call<SaveMonthlyResponse> saveClinicalGlobalImpression(@Field("pat_id") String pat_id,@Field("report_id") String report_id,@Field("monthly_clinical_global_impression_scales_answer_value_json") String monthly_clinical_global_impression_scales_answer_value_json);

    @FormUrlEncoded
    @POST("save_patient_beck_anxiety_inventory_answer?")
    Call<SaveMonthlyResponse> saveMonthlyBeckAnxietyInventoryAnswer(@Field("pat_id") String pat_id,@Field("report_id") String report_id,@Field("monthly_beck_anxiety_inventory_answer_value_json") String monthly_clinical_global_impression_scales_answer_value_json);

    @FormUrlEncoded
    @POST("save_patient_beck_depression_inventory_answer?")
    Call<SaveMonthlyResponse> saveMonthlyBeckDepressionAnswer(@Field("pat_id") String pat_id,@Field("report_id") String report_id,@Field("monthly_beck_depression_inventory_answer_value_json") String monthly_clinical_global_impression_scales_answer_value_json);

    @FormUrlEncoded
    @POST("save_patient_reminder?")
    Call<SaveReminderResponse> savePatientReminder(@Field("pat_id") String pat_id, @Field("reminder_value_json") String reminder_value_json);

    @FormUrlEncoded
    @POST("save_patient_status?")
    Call<SaveSettingsResponse> savePatientStatus(@Field("pat_id") String pat_id, @Field("status") String status, @Field("status_name") String status_name);

    @FormUrlEncoded
    @POST("weekly_brief_pain_inventory_short_form_question?")
    Call<WeeklyOneResponse> getWeeklyOne(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("weekly_IMS_12_question?")
    Call<WeeklyTwoResponse> getWeeklyTwo(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("weekly_pain_catastrophizing_index_question?")
    Call<WeeklyThreeResponse> getWeeklyThree(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("weekly_columbia_suicide_severity_rating_scale_question?")
    Call<WeeklyFourResponse> getWeeklyFour(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("weekly_event_reporting_question?")
    Call<WeeklyFiveResponse> getWeeklyFive(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("save_patient_answer_weekly_brief_pain_inventory_short_form?")
    Call<SaveWeeklyResponse> saveWeeklyOne(@Field("pat_id") String pat_id, @Field("weekly_brief_pain_inventory_short_form_answer_value_json") String weekly_brief_pain_inventory_short_form_answer_value_json);

    @FormUrlEncoded
    @POST("save_patient_answer_weekly_IMS_12?")
    Call<SaveWeeklyResponse> saveWeeklyTwo(@Field("pat_id") String pat_id,@Field("report_id") String report_id, @Field("weekly_IMS_12_answer_value_json") String weekly_IMS_12_answer_value_json);

    @FormUrlEncoded
    @POST("save_patient_answer_weekly_pain_catastrophizing_index?")
    Call<SaveWeeklyResponse> saveWeeklyThree(@Field("pat_id") String pat_id,@Field("report_id") String report_id, @Field("weekly_pain_catastrophizing_index_answer_value_json") String weekly_pain_catastrophizing_index_answer_value_json);

    @FormUrlEncoded
    @POST("save_patient_answer_weekly_columbia_suicide_severity_rating_scale_answer?")
    Call<SaveWeeklyResponse> saveWeeklyFour(@Field("pat_id") String pat_id,@Field("report_id") String report_id, @Field("weekly_columbia_suicide_severity_rating_scale_answer_value_json") String weekly_columbia_suicide_severity_rating_scale_answer_value_json);

    @FormUrlEncoded
    @POST("save_patient_answer_weekly_event_reporting?")
    Call<SaveWeeklyResponse> saveWeeklyFive(@Field("pat_id") String pat_id,@Field("report_id") String report_id, @Field("weekly_event_reporting_answer_value_json") String weekly_event_reporting_answer_value_json);

    @FormUrlEncoded
    @POST("patient_reminder?")
    Call<ReminderResponce> getReminder(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("patient_previous_map_data?")
    Call<ClipBoardResponse> getClipBoard(@Field("pat_id") String pat_id);

    @FormUrlEncoded
    @POST("check_patient_body_pain_map?")
    Call<SaveWebView> saveBodyMapWeb(@Field("pat_id") String pat_id, @Field("inrt_id") String inrt_id);

}
