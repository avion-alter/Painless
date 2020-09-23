package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ucsf.painless.R;
import com.ucsf.painless.model.intensity.IntensityResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.viewModel.IntensityRatingViewModel;
import com.ucsf.painless.viewModel.MoodRatingViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoodRatingActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.seekBar_luminosite)
    SeekBar seekBarLuminosite;
    @BindView(R.id.btn_next)
    Button btnNext;

    String intensity = "";
    String relief = "";
    Dialog myDialog;
    MoodRatingViewModel moodRatingViewModel;
    String intensity_id;
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.seekbar_show)
    SeekBar seekbarShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_mood_rating);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        seekbarShow.setEnabled(false);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        moodRatingViewModel = ViewModelProviders.of(this).get(MoodRatingViewModel.class);
        intensity = getIntent().getStringExtra("intensity_id");
        relief = getIntent().getStringExtra("relief");

    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {

        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("dmvr_rating_value", String.valueOf(seekBarLuminosite.getProgress()));
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonArray.put(jsonObject);

        moodRatingViewModel.saveMoodRating(intensity, jsonArray.toString(), MoodRatingActivity.this).observe(this, new Observer<IntensityResponse>() {
            @Override
            public void onChanged(@Nullable IntensityResponse intensityResponse) {
                try {
                    if (intensityResponse.getStatus()) {
                        myDialog = new Dialog(MoodRatingActivity.this);
                        intensity_id = String.valueOf(intensityResponse.getData().getInrtId());
                        ShowPopup();
                    } else {
                        Toast.makeText(MoodRatingActivity.this, "" + intensityResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });

    }


    public void ShowPopup() {

        Button next;
        myDialog.setContentView(R.layout.dialog_intensity_saved);
        next = (Button) myDialog.findViewById(R.id.btn_next);
        TextView txt_heading = (TextView) myDialog.findViewById(R.id.txt_heading);
        TextView txt_date = (TextView) myDialog.findViewById(R.id.txt_date);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        txt_heading.setText("Just one more step to go!");


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy- hh:mm a", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        txt_date.setText(currentDateandTime);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(MoodRatingActivity.this, SaveMcGillQuestionnaireActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                n.putExtra("intensity_id", intensity_id);
                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(MoodRatingActivity.this).toBundle());
                myDialog.dismiss();
            }
        });

        myDialog.show();
    }

    @OnClick(R.id.card_back)
    public void onBackViewClicked() {
        finish();
    }
}