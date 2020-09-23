package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ucsf.painless.R;
import com.ucsf.painless.model.settings.SaveSettingsResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.viewModel.SaveSettingsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RemainderActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.linear_set_remainder)
    LinearLayout linearSetRemainder;
    @BindView(R.id.img_sound)
    ImageView imgSound;
    @BindView(R.id.img_push_remainder)
    ImageView imgPushRemainder;
    @BindView(R.id.card_back)
    CardView cardBack;
    SaveSettingsViewModel saveSettingsViewModel;

    boolean in_app_sound=false;
    boolean push_notification=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        saveSettingsViewModel = ViewModelProviders.of(this).get(SaveSettingsViewModel.class);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());


    }

    @OnClick({R.id.linear_set_remainder, R.id.img_sound, R.id.img_push_remainder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_set_remainder:
                Intent n = new Intent(RemainderActivity.this, RemainderSelectionActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.img_sound:
                if (in_app_sound){
                    in_app_sound=false;
                    changeSetting("Off","remider_sound_alarm");
                    imgSound.setImageResource(R.mipmap.toggle_off);
                }else {
                    in_app_sound=true;
                    imgSound.setImageResource(R.mipmap.toggle_on);
                    changeSetting("On","remider_sound_alarm");
                }
                break;
            case R.id.img_push_remainder:
                if (push_notification){
                    push_notification=false;
                    changeSetting("Off","remider_push_notification");
                    imgPushRemainder.setImageResource(R.mipmap.toggle_off);
                }else {
                    push_notification=true;
                    changeSetting("On","remider_push_notification");
                    imgPushRemainder.setImageResource(R.mipmap.toggle_on);
                }
                break;
        }
    }

    @OnClick(R.id.card_back)
    public void onViewClicked() {
        finish();
    }

    void changeSetting(String status, String statusName){
        saveSettingsViewModel.savePatientStatus(status, statusName,RemainderActivity.this).observe(this, new Observer<SaveSettingsResponse>() {
            @Override
            public void onChanged(@Nullable SaveSettingsResponse settingsResponse) {
                try {
                    if (settingsResponse.isStatus()){
                        Toast.makeText(RemainderActivity.this, ""+settingsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RemainderActivity.this, ""+settingsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        });
    }
}
