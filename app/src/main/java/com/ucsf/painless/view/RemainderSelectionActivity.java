package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ucsf.painless.R;
import com.ucsf.painless.model.login.LoginResponse;
import com.ucsf.painless.model.remainders.ReminderResponce;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.viewModel.LoginViewModel;
import com.ucsf.painless.viewModel.RemainderViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RemainderSelectionActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.linear_daily)
    LinearLayout linearDaily;
    @BindView(R.id.linear_weekly)
    LinearLayout linearWeekly;
    @BindView(R.id.linear_monthly)
    LinearLayout linearMonthly;
    @BindView(R.id.card_back)
    CardView cardBack;
    
    RemainderViewModel remainderViewModel;
    ReminderResponce reminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_one);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());

        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());

        remainderViewModel= ViewModelProviders.of(this).get(RemainderViewModel.class);

        remainderViewModel.getReminder(RemainderSelectionActivity.this).observe(this, new Observer<ReminderResponce>() {
            @Override
            public void onChanged(@Nullable ReminderResponce reminderResponce) {
                try {
                    if (reminderResponce.getStatus()){
                        reminder=reminderResponce;
                    }else {
                        Toast.makeText(RemainderSelectionActivity.this, ""+reminderResponce.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        });

    }

    @OnClick({R.id.linear_daily, R.id.linear_weekly, R.id.linear_monthly})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_daily:
                Intent n = new Intent(RemainderSelectionActivity.this, DailyRemainderActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                n.putExtra("Reminder", reminder);
                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.linear_weekly:
                Intent intent = new Intent(RemainderSelectionActivity.this, WeeklyRemainderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Reminder", reminder);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

                break;
            case R.id.linear_monthly:
                Intent intent1 = new Intent(RemainderSelectionActivity.this, MonthlyRemainderActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.putExtra("Reminder", reminder);
                startActivity(intent1, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }
    }

    @OnClick(R.id.card_back)
    public void onViewClicked() {
        finish();
    }
}
