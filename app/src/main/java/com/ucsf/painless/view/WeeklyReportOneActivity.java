package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ucsf.painless.R;
import com.ucsf.painless.adapter.WeeklySelectionAdapter;
import com.ucsf.painless.model.saveWeekly.SaveWeeklyResponse;
import com.ucsf.painless.model.weeklyOne.WeeklyOneResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.viewModel.WeeklyOneViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeeklyReportOneActivity extends AppCompatActivity {
    List<String> namesList;

    Integer selectedIdOne;
    WeeklySelectionAdapter adapterOne;

    Integer selectedIdTwo;
    WeeklySelectionAdapter adapterTwo;

    Integer selectedIdThree;
    WeeklySelectionAdapter adapterThree;

    WeeklyOneViewModel weeklyOneViewModel;

    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.seekBar_luminosite)
    SeekBar seekBarLuminosite;
    @BindView(R.id.recycler_view_one)
    RecyclerView recyclerViewOne;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.recycler_view_two)
    RecyclerView recyclerViewTwo;
    @BindView(R.id.recycler_view_three)
    RecyclerView recyclerViewThree;
    @BindView(R.id.txt_one)
    TextView txtOne;
    @BindView(R.id.txt_two)
    TextView txtTwo;
    @BindView(R.id.txt_three)
    TextView txtThree;
    @BindView(R.id.txt_four)
    TextView txtFour;
    @BindView(R.id.txt_five)
    TextView txtFive;
    @BindView(R.id.txt_six)
    TextView txtSix;
    @BindView(R.id.txt_seven)
    TextView txtSeven;
    @BindView(R.id.edt_text_one)
    EditText edtTextOne;
    @BindView(R.id.seekBar_two)
    SeekBar seekBarTwo;
    @BindView(R.id.seekBar_three)
    SeekBar seekBarThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report_one);
        ButterKnife.bind(this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());

        weeklyOneViewModel = ViewModelProviders.of(this).get(WeeklyOneViewModel.class);

        namesList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            namesList.add(String.valueOf(i));
        }
        //FirstList
        selectedIdOne = -1;
        adapterOne = new WeeklySelectionAdapter(namesList, selectedIdOne);
        recyclerViewOne.setHasFixedSize(true);
        recyclerViewOne.setLayoutManager(new LinearLayoutManager(WeeklyReportOneActivity.this));
        recyclerViewOne.setAdapter(adapterOne);

        //SecondList
        selectedIdTwo = -1;
        adapterTwo = new WeeklySelectionAdapter(namesList, selectedIdTwo);
        recyclerViewTwo.setHasFixedSize(true);
        recyclerViewTwo.setLayoutManager(new LinearLayoutManager(WeeklyReportOneActivity.this));
        recyclerViewTwo.setAdapter(adapterTwo);

        //ThirdList
        selectedIdThree = -1;
        adapterThree = new WeeklySelectionAdapter(namesList, selectedIdThree);
        recyclerViewThree.setHasFixedSize(true);
        recyclerViewThree.setLayoutManager(new LinearLayoutManager(WeeklyReportOneActivity.this));
        recyclerViewThree.setAdapter(adapterThree);
        getData();
    }

    @OnClick({R.id.card_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_back:
                finish();
                break;
            case R.id.btn_next:

                JsonArray jsonArray = new JsonArray();
                JsonObject jsonObjectOne, jsonObjectSecond, jsonObjectThree, jsonObjectFour, jsonObjectFive, jsonObjectSix, jsonObjectSeven;

                jsonObjectOne = new JsonObject();
                jsonObjectOne.addProperty("pawbpisf_wbpisf_id", "1");
                jsonObjectOne.addProperty("pawbpisf_answer", edtTextOne.getText().toString());
                jsonArray.add(jsonObjectOne);

                jsonObjectSecond = new JsonObject();
                jsonObjectSecond.addProperty("pawbpisf_wbpisf_id", "2");
                jsonObjectSecond.addProperty("pawbpisf_answer", String.valueOf(seekBarLuminosite.getProgress()));
                jsonArray.add(jsonObjectSecond);

                if (!String.valueOf(adapterOne.getSelectedId()).equals("-1")){
                Toast.makeText(this, String.valueOf(adapterOne.getSelectedId()), Toast.LENGTH_SHORT).show();
                jsonObjectThree = new JsonObject();
                jsonObjectThree.addProperty("pawbpisf_wbpisf_id", "3");
                jsonObjectThree.addProperty("pawbpisf_answer", adapterOne.getSelectedId().toString());
                jsonArray.add(jsonObjectThree);
                }else {
                    Toast.makeText(this, "Please rate your usual tingling sensation/pain.", Toast.LENGTH_SHORT).show();
                    return;
                }

                jsonObjectFour = new JsonObject();
                jsonObjectFour.addProperty("pawbpisf_wbpisf_id", "4");
                jsonObjectFour.addProperty("pawbpisf_answer", String.valueOf(seekBarTwo.getProgress()));
                jsonArray.add(jsonObjectFour);

                if (!adapterTwo.getSelectedId().toString().equals("-1")){
                jsonObjectFive = new JsonObject();
                jsonObjectFive.addProperty("pawbpisf_wbpisf_id", "5");
                jsonObjectFive.addProperty("pawbpisf_answer", adapterTwo.getSelectedId().toString());
                jsonArray.add(jsonObjectFive);
                }else {
                    Toast.makeText(this, "Please rate your usual numbness sensation/pain.", Toast.LENGTH_SHORT).show();
                    return;
                }

                jsonObjectSix = new JsonObject();
                jsonObjectSix.addProperty("pawbpisf_wbpisf_id", "6");
                jsonObjectSix.addProperty("pawbpisf_answer", String.valueOf(seekBarThree.getProgress()));
                jsonArray.add(jsonObjectSix);

                if (!adapterThree.getSelectedId().toString().equals("-1")){
                jsonObjectSeven = new JsonObject();
                jsonObjectSeven.addProperty("pawbpisf_wbpisf_id", "7");
                jsonObjectSeven.addProperty("pawbpisf_answer",adapterThree.getSelectedId().toString());
                jsonArray.add(jsonObjectSeven);
                }else {
                    Toast.makeText(this, "Please rate your usual pain due to touch.", Toast.LENGTH_SHORT).show();
                    return;
                }

                weeklyOneViewModel.saveWeeklyOne(WeeklyReportOneActivity.this,jsonArray.toString()).observe(this, new Observer<SaveWeeklyResponse>() {
                    @Override
                    public void onChanged(@Nullable SaveWeeklyResponse saveWeeklyResponse) {
                        try {
                            if (saveWeeklyResponse.isStatus()) {
                                Intent n = new Intent(WeeklyReportOneActivity.this, WeeklyReportTwoActivity.class);
                                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                n.putExtra("report_id",saveWeeklyResponse.getData().getReportId());
                                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(WeeklyReportOneActivity.this).toBundle());
                            } else {
                                Toast.makeText(WeeklyReportOneActivity.this, "" + saveWeeklyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                });


                break;
        }
    }


    private void getData() {
        weeklyOneViewModel.getWeeklyOne(WeeklyReportOneActivity.this).observe(this, new Observer<WeeklyOneResponse>() {
            @Override
            public void onChanged(@Nullable WeeklyOneResponse oneResponse) {
                try {
                    if (oneResponse.isStatus()) {

                        txtOne.setText(oneResponse.getData().getWeeklyBriefPainInventoryShortFormQuestion().get(0).getWbpisfQuestion());
                        txtTwo.setText(oneResponse.getData().getWeeklyBriefPainInventoryShortFormQuestion().get(1).getWbpisfQuestion());
                        txtThree.setText(oneResponse.getData().getWeeklyBriefPainInventoryShortFormQuestion().get(2).getWbpisfQuestion());
                        txtFour.setText(oneResponse.getData().getWeeklyBriefPainInventoryShortFormQuestion().get(3).getWbpisfQuestion());
                        txtFive.setText(oneResponse.getData().getWeeklyBriefPainInventoryShortFormQuestion().get(4).getWbpisfQuestion());
                        txtSix.setText(oneResponse.getData().getWeeklyBriefPainInventoryShortFormQuestion().get(5).getWbpisfQuestion());
                        txtSeven.setText(oneResponse.getData().getWeeklyBriefPainInventoryShortFormQuestion().get(6).getWbpisfQuestion());

                    } else {
                        Toast.makeText(WeeklyReportOneActivity.this, "" + oneResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
    }
}
