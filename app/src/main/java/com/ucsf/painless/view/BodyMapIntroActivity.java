package com.ucsf.painless.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.ucsf.painless.R;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BodyMapIntroActivity extends AppCompatActivity {

    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.btn_next)
    Button btnNext;

    SessionManager sessionManager;
    String intensity_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_map_intro);
        ButterKnife.bind(this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        sessionManager = new SessionManager(BodyMapIntroActivity.this);
        intensity_id = getIntent().getStringExtra("intensity_id");

    }


    @OnClick({R.id.card_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_back:
                finish();
                break;
            case R.id.btn_next:
                if (sessionManager.getStringKey(SessionKeyConstant.KEY_gender).equals("male")) {
                    Intent n = new Intent(BodyMapIntroActivity.this, BodyMapWebViewActivity.class);
                    n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    n.putExtra("intensity_id", intensity_id);
                    startActivity(n);
                } else {
                    Intent n = new Intent(BodyMapIntroActivity.this, BodyMapWebViewActivity.class);
                    n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    n.putExtra("intensity_id", intensity_id);
                    startActivity(n);
                }
                break;
        }
    }
}
