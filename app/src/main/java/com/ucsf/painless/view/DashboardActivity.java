package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ucsf.painless.R;
import com.ucsf.painless.model.dashboard.DashBoardResponse;
import com.ucsf.painless.viewModel.DashBoardViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.linear_complete_pain_report)
    LinearLayout linearCompletePainReport;
    @BindView(R.id.linear_complete_weekly_pain_report)
    LinearLayout linearCompleteWeeklyPainReport;
    @BindView(R.id.linear_complete_monthly_pain_report)
    LinearLayout linearCompleteMonthlyPainReport;
    @BindView(R.id.card_notification)
    CardView cardNotification;
    @BindView(R.id.card_settings)
    CardView cardSettings;
    @BindView(R.id.card_video)
    CardView cardVideo;

    DashBoardViewModel dashBoardViewModel;
    String token;
    @BindView(R.id.txt_count)
    TextView txtCount;
    @BindView(R.id.txt_total_complete_report)
    TextView txtTotalCompleteReport;
    @BindView(R.id.txt_total_complete_body_map)
    TextView txtTotalCompleteBodyMap;
    @BindView(R.id.card_info)
    CardView cardInfo;
    @BindView(R.id.btn_start_pain_report)
    Button btnStartPainReport;
    @BindView(R.id.btn_weekly_pain_report)
    Button btnWeeklyPainReport;
    @BindView(R.id.btn_monthly_pain_report)
    Button btnMonthlyPainReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        dashBoardViewModel = ViewModelProviders.of(this).get(DashBoardViewModel.class);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();
                        Log.e("token", token);

                    }
                });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId = getString(R.string.app_name);
            String channelName = getString(R.string.app_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d("TAG", "Key: " + key + " Value: " + value);
            }
        }


        Log.d("TAG", "Subscribing to weather topic");
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.app_name);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.app_name);
                        }
                        Log.d("TAG", msg);
                        //Toast.makeText(DashboardActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        // [END handle_data_extras]


        dashBoardViewModel.getDashboardInfo(token, DashboardActivity.this).observe(this, new Observer<DashBoardResponse>() {
            @Override
            public void onChanged(@Nullable DashBoardResponse dashBoardResponse) {
                try {
                    if (dashBoardResponse.getStatus()) {

                        txtCount.setText(dashBoardResponse.getData().getDailyPainReportsCompleted().toString());
                        txtTotalCompleteBodyMap.setText(dashBoardResponse.getData().getBodyPainMapsCompleted().toString());
                        txtTotalCompleteReport.setText(dashBoardResponse.getData().getTotalReportsReportsCompleted().toString());

                    } else {
                        Toast.makeText(DashboardActivity.this, "" + dashBoardResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });

    }

    @OnClick(R.id.card_video)
    public void onVideoViewClicked() {
        Intent n = new Intent(DashboardActivity.this, VideoIntroActivity.class);
        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(n);
    }

    @OnClick(R.id.linear_complete_pain_report)
    public void onViewClicked() {

    }

    @OnClick({R.id.linear_complete_weekly_pain_report, R.id.linear_complete_monthly_pain_report, R.id.card_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_complete_weekly_pain_report:

                break;
            case R.id.linear_complete_monthly_pain_report:

                break;
            case R.id.card_info:
                Intent intentinfo = new Intent(DashboardActivity.this, TrialInfoActivity.class);
                intentinfo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentinfo, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }
    }


    @OnClick({R.id.btn_start_pain_report, R.id.btn_weekly_pain_report, R.id.btn_monthly_pain_report})
    public void onNewViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start_pain_report:
                Intent n = new Intent(DashboardActivity.this, PainIntensityRatingActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.btn_weekly_pain_report:
                Intent weeklyIntent = new Intent(DashboardActivity.this, WeeklyReportOneActivity.class);
                weeklyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(weeklyIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.btn_monthly_pain_report:
                Intent intent = new Intent(DashboardActivity.this, MonthlyRandomActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }
    }

    @OnClick({R.id.card_notification, R.id.card_settings})
    public void onRemainderViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_settings:
                Intent n = new Intent(DashboardActivity.this, SettingActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.card_notification:
                Intent intent = new Intent(DashboardActivity.this, RemainderActivity.class);
                // Intent n = new Intent(DashboardActivity.this, PainIntensityRatingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }

    }



}

