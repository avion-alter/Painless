package com.ucsf.painless.view;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ucsf.painless.R;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class McGillInfoActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.img_close)
    ImageView imgClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcgill_info);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());


    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        finish();
    }
}
