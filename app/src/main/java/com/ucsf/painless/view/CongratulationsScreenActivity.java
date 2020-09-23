package com.ucsf.painless.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionKeyConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CongratulationsScreenActivity extends AppCompatActivity {

    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_screen)
    TextView txtScreen;

    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        ButterKnife.bind(this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        txtName.setText(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_full_name));
        msg = getIntent().getStringExtra("msg");
        txtScreen.setText(msg);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.congratulationsalerttone);
        mp.start();

    }


    @OnClick(R.id.card_back)
    public void onViewClicked() {
        Intent n = new Intent(CongratulationsScreenActivity.this, DashboardActivity.class);
        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(n);
    }
}
