package com.ucsf.painless.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ucsf.painless.R;
import com.ucsf.painless.model.remainders.ReminderResponce;
import com.ucsf.painless.model.saveRemainder.SaveReminderResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.viewModel.SaveReminderViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DailyRemainderActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.timePicker1)
    TimePicker timePicker1;
    @BindView(R.id.card_back)
    CardView card_back;
    @BindView(R.id.timePicker2)
    TimePicker timePicker2;
    @BindView(R.id.timePicker3)
    TimePicker timePicker3;
    @BindView(R.id.btn_next)
    Button btnNext;

    SaveReminderViewModel saveDailyReminderViewModel;
    @BindView(R.id.timePicker4)
    TimePicker timePicker4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_two);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        ReminderResponce reminderResponce = (ReminderResponce) getIntent().getSerializableExtra("Reminder");
        saveDailyReminderViewModel = ViewModelProviders.of(this).get(SaveReminderViewModel.class);
        ;
        timePicker1.setIs24HourView(true);
        timePicker2.setIs24HourView(true);
        timePicker3.setIs24HourView(true);
        timePicker4.setIs24HourView(true);

        try {
            for (int i = 0; i < reminderResponce.getData().getPatientReminderData().size(); i++) {
                if (reminderResponce.getData().getPatientReminderData().get(i).getReminderType().equals("Daily 1")) {
                    String savedTime = reminderResponce.getData().getPatientReminderData().get(i).getReminderTime();
                    String[] words = savedTime.split(":");
                    timePicker1.setHour(Integer.parseInt(words[0]));
                    timePicker1.setMinute(Integer.parseInt(words[1]));
                } else if (reminderResponce.getData().getPatientReminderData().get(i).getReminderType().equals("Daily 2")) {
                    String savedTime = reminderResponce.getData().getPatientReminderData().get(i).getReminderTime();
                    String[] words = savedTime.split(":");
                    timePicker2.setHour(Integer.parseInt(words[0]));
                    timePicker2.setMinute(Integer.parseInt(words[1]));
                } else if (reminderResponce.getData().getPatientReminderData().get(i).getReminderType().equals("Daily 3")) {
                    String savedTime = reminderResponce.getData().getPatientReminderData().get(i).getReminderTime();
                    String[] words = savedTime.split(":");
                    timePicker3.setHour(Integer.parseInt(words[0]));
                    timePicker3.setMinute(Integer.parseInt(words[1]));
                } else if (reminderResponce.getData().getPatientReminderData().get(i).getReminderType().equals("Daily 4")) {
                    String savedTime = reminderResponce.getData().getPatientReminderData().get(i).getReminderTime();
                    String[] words = savedTime.split(":");
                    timePicker4.setHour(Integer.parseInt(words[0]));
                    timePicker4.setMinute(Integer.parseInt(words[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @OnClick({R.id.card_back, R.id.timePicker1, R.id.timePicker2, R.id.timePicker3, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_back:
                finish();
                break;
            case R.id.timePicker1:
                break;
            case R.id.timePicker2:
                break;
            case R.id.timePicker3:
                break;
            case R.id.btn_next:

                JsonArray jsonArray = new JsonArray();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("reminder_type", "Daily 1");
                jsonObject.addProperty("reminder_time", timePicker1.getHour() + ":" + timePicker1.getMinute() + ":00");
                jsonArray.add(jsonObject);

                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("reminder_type", "Daily 2");
                jsonObject2.addProperty("reminder_time", timePicker2.getHour() + ":" + timePicker2.getMinute() + ":00");
                jsonArray.add(jsonObject2);

                JsonObject jsonObject3 = new JsonObject();
                jsonObject3.addProperty("reminder_type", "Daily 3");
                jsonObject3.addProperty("reminder_time", timePicker3.getHour() + ":" + timePicker3.getMinute() + ":00");
                jsonArray.add(jsonObject3);

                JsonObject jsonObject4 = new JsonObject();
                jsonObject4.addProperty("reminder_type", "Daily 4");
                jsonObject4.addProperty("reminder_time", timePicker4.getHour() + ":" + timePicker4.getMinute() + ":00");
                jsonArray.add(jsonObject4);


                saveDailyReminderViewModel.saveReminder(jsonArray.toString(), DailyRemainderActivity.this).observe(this, new Observer<SaveReminderResponse>() {
                    @Override
                    public void onChanged(@Nullable SaveReminderResponse reminderResponse) {
                        try {
                            if (reminderResponse.isStatus()) {
                                Toast.makeText(DailyRemainderActivity.this, "" + reminderResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent n = new Intent(DailyRemainderActivity.this, DashboardActivity.class);
                                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(n);
                                finish();
                            } else {
                                Toast.makeText(DailyRemainderActivity.this, "" + reminderResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                });

                break;
        }
    }
}
