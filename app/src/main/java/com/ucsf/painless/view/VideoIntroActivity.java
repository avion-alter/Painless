package com.ucsf.painless.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ucsf.painless.R;
import com.ucsf.painless.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class VideoIntroActivity extends AppCompatActivity {


    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.linear_complete_pain_report)
    LinearLayout linearCompletePainReport;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record_intro);
        ButterKnife.bind(this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());

    }


    @OnClick({R.id.card_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_back:
                Intent n = new Intent(VideoIntroActivity.this, DashboardActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(n);
                break;
            case R.id.btn_next:
                Intent intent = new Intent(VideoIntroActivity.this, FirstVideoRecordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
