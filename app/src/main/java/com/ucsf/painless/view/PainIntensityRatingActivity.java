package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ucsf.painless.R;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PainIntensityRatingActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.seekBar_luminosite)
    SeekBar seekBarLuminosite;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.seekbar_show)
    SeekBar seekbarShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_intensity_rating);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        seekbarShow.setEnabled(false);
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        Intent n = new Intent(PainIntensityRatingActivity.this, PainReliefRatingActivity.class);
        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        n.putExtra("intensity", String.valueOf(seekBarLuminosite.getProgress()));
        startActivity(n, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @OnClick(R.id.card_back)
    public void onBackViewClicked() {
        finish();
    }
}
