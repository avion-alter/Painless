package com.ucsf.painless.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.model.settings.SaveSettingsResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.viewModel.SaveSettingsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.img_in_app_sound)
    ImageView imgInAppSound;
    @BindView(R.id.img_push_notification)
    ImageView imgPushNotification;
    @BindView(R.id.img_save_video)
    ImageView imgSaveVideo;
    @BindView(R.id.img_touch_id)
    ImageView imgTouchId;

    boolean in_app_sound = false;
    boolean push_notification = false;
    boolean save_video = false;
    boolean touch_id = false;

    SaveSettingsViewModel saveSettingsViewModel;
    @BindView(R.id.card_logout)
    CardView cardLogout;
    @BindView(R.id.txt_name)
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        saveSettingsViewModel = ViewModelProviders.of(this).get(SaveSettingsViewModel.class);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());

        txtName.setText(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_full_name));
    }

    @OnClick({R.id.card_back, R.id.img_in_app_sound, R.id.img_push_notification, R.id.img_save_video, R.id.img_touch_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_back:
                finish();
                break;
            case R.id.img_in_app_sound:
                if (in_app_sound) {
                    in_app_sound = false;
                    changeSetting("Off", "in_app_sound");
                    imgInAppSound.setImageResource(R.mipmap.toggle_off);
                } else {
                    in_app_sound = true;
                    imgInAppSound.setImageResource(R.mipmap.toggle_on);
                    changeSetting("On", "in_app_sound");
                }
                break;
            case R.id.img_push_notification:
                if (push_notification) {
                    push_notification = false;
                    changeSetting("Off", "push_notification");
                    imgPushNotification.setImageResource(R.mipmap.toggle_off);
                } else {
                    push_notification = true;
                    changeSetting("On", "push_notification");
                    imgPushNotification.setImageResource(R.mipmap.toggle_on);
                }
                break;
            case R.id.img_save_video:
                if (save_video) {
                    save_video = false;
                    changeSetting("Off", "save_videos_onto_devices");
                    imgSaveVideo.setImageResource(R.mipmap.toggle_off);
                } else {
                    save_video = true;
                    changeSetting("On", "save_videos_onto_devices");
                    imgSaveVideo.setImageResource(R.mipmap.toggle_on);
                }
                break;
            case R.id.img_touch_id:
                if (touch_id) {
                    touch_id = false;
                    changeSetting("Off", "touch_ID");
                    imgTouchId.setImageResource(R.mipmap.toggle_off);
                } else {
                    touch_id = true;
                    changeSetting("On", "touch_ID");
                    imgTouchId.setImageResource(R.mipmap.toggle_on);
                }
                break;
        }
    }


    void changeSetting(String status, String statusName) {
        saveSettingsViewModel.savePatientStatus(status, statusName, SettingActivity.this).observe(this, new Observer<SaveSettingsResponse>() {
            @Override
            public void onChanged(@Nullable SaveSettingsResponse settingsResponse) {
                try {
                    if (settingsResponse.isStatus()) {
                        Toast.makeText(SettingActivity.this, "" + settingsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SettingActivity.this, "" + settingsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick(R.id.card_logout)
    public void onViewClicked() {
        sessionManager.setBooleanKey(SessionKeyConstant.KEY_is_login, false);
        Intent n = new Intent(SettingActivity.this, LoginActivity.class);
        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(n);
        finish();
    }
}
