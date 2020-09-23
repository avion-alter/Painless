package com.ucsf.painless.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class MonthlyRemainderActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.timePicker1)
    TimePicker timePicker1;
    @BindView(R.id.btn_next)
    Button btnNext;

    SaveReminderViewModel saveDailyReminderViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_monthly);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        saveDailyReminderViewModel = ViewModelProviders.of(this).get(SaveReminderViewModel.class);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());

        ReminderResponce reminderResponce=(ReminderResponce) getIntent().getSerializableExtra("Reminder");
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());

        try {
            for (int i = 0; i < reminderResponce.getData().getPatientReminderData().size(); i++) {
                if (reminderResponce.getData().getPatientReminderData().get(i).getReminderType().equals("Monthly")){
                    String savedTime=reminderResponce.getData().getPatientReminderData().get(i).getReminderTime();
                    String[] words=savedTime.split(":");
                    timePicker1.setHour(Integer.parseInt(words[0]));
                    timePicker1.setMinute(Integer.parseInt(words[1]));
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days_of_month, android.R.layout.simple_spinner_dropdown_item);
                    if (reminderResponce.getData().getPatientReminderData().get(i).getReminderMonthDay() != null) {
                        int spinnerPosition = adapter.getPosition(reminderResponce.getData().getPatientReminderData().get(i).getReminderMonthDay());
                        spinner.setSelection(spinnerPosition);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClick({R.id.card_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_back:
                finish();
                break;
            case R.id.btn_next:

                JsonArray jsonArray = new JsonArray();
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("reminder_type", "Monthly");
                jsonObject.addProperty("reminder_time", timePicker1.getHour() + ":" + timePicker1.getMinute() + ":00");
                jsonObject.addProperty("reminder_month_day", spinner.getSelectedItem().toString());
                jsonArray.add(jsonObject);



                saveDailyReminderViewModel.saveReminder(jsonArray.toString(), MonthlyRemainderActivity.this).observe(this, new Observer<SaveReminderResponse>() {
                    @Override
                    public void onChanged(@Nullable SaveReminderResponse reminderResponse) {
                        try {
                            if (reminderResponse.isStatus()) {
                                Toast.makeText(MonthlyRemainderActivity.this, "" + reminderResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                Intent n = new Intent(MonthlyRemainderActivity.this, DashboardActivity.class);
                                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(n);
                                finish();
                            } else {
                                Toast.makeText(MonthlyRemainderActivity.this, "" + reminderResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
