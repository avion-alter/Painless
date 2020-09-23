package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ucsf.painless.R;
import com.ucsf.painless.adapter.WeeklySecondSelectionAdapter;
import com.ucsf.painless.model.saveWeekly.SaveWeeklyResponse;
import com.ucsf.painless.model.weeklySecond.WeeklyTwoResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.viewModel.WeeklyOneViewModel;
import com.ucsf.painless.viewModel.WeeklyTwoViewModel;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeeklyReportTwoActivity extends AppCompatActivity {
    List<String> namesList;

    Integer selectedIdOne;
    WeeklySecondSelectionAdapter adapterOne;

    Integer selectedIdTwo;
    WeeklySecondSelectionAdapter adapterTwo;

    Integer selectedIdThree;
    WeeklySecondSelectionAdapter adapterThree;

    Integer selectedIdFour;
    WeeklySecondSelectionAdapter adapterFour;

    Integer selectedIdFive;
    WeeklySecondSelectionAdapter adapterFive;

    Integer selectedIdSix;
    WeeklySecondSelectionAdapter adapterSix;

    Integer selectedIdSeven;
    WeeklySecondSelectionAdapter adapterSeven;

    Integer selectedIdEight;
    WeeklySecondSelectionAdapter adapterEight;

    Integer selectedIdNine;
    WeeklySecondSelectionAdapter adapterNine;

    Integer selectedIdTen;
    WeeklySecondSelectionAdapter adapterTen;

    Integer selectedIdEleven;
    WeeklySecondSelectionAdapter adapterEleven;

    Integer selectedIdTwelve;
    WeeklySecondSelectionAdapter adapterTwelve;

    WeeklyTwoViewModel weeklyTwoViewModel;
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.recycler_view_one)
    RecyclerView recyclerViewOne;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.txt_one)
    TextView txtOne;
    @BindView(R.id.txt_start_one)
    TextView txtStartOne;
    @BindView(R.id.txt_end_one)
    TextView txtEndOne;
    @BindView(R.id.txt_two)
    TextView txtTwo;
    @BindView(R.id.recycler_view_two)
    RecyclerView recyclerViewTwo;
    @BindView(R.id.txt_start_two)
    TextView txtStartTwo;
    @BindView(R.id.txt_end_two)
    TextView txtEndTwo;
    @BindView(R.id.txt_three)
    TextView txtThree;
    @BindView(R.id.recycler_view_three)
    RecyclerView recyclerViewThree;
    @BindView(R.id.txt_start_three)
    TextView txtStartThree;
    @BindView(R.id.txt_end_three)
    TextView txtEndThree;
    @BindView(R.id.txt_four)
    TextView txtFour;
    @BindView(R.id.recycler_view_four)
    RecyclerView recyclerViewFour;
    @BindView(R.id.txt_start_four)
    TextView txtStartFour;
    @BindView(R.id.txt_end_four)
    TextView txtEndFour;
    @BindView(R.id.txt_five)
    TextView txtFive;
    @BindView(R.id.recycler_view_five)
    RecyclerView recyclerViewFive;
    @BindView(R.id.txt_start_five)
    TextView txtStartFive;
    @BindView(R.id.txt_end_five)
    TextView txtEndFive;
    @BindView(R.id.txt_six)
    TextView txtSix;
    @BindView(R.id.recycler_view_six)
    RecyclerView recyclerViewSix;
    @BindView(R.id.txt_start_six)
    TextView txtStartSix;
    @BindView(R.id.txt_end_six)
    TextView txtEndSix;
    @BindView(R.id.txt_seven)
    TextView txtSeven;
    @BindView(R.id.recycler_view_seven)
    RecyclerView recyclerViewSeven;
    @BindView(R.id.txt_start_seven)
    TextView txtStartSeven;
    @BindView(R.id.txt_end_seven)
    TextView txtEndSeven;
    @BindView(R.id.txt_eight)
    TextView txtEight;
    @BindView(R.id.recycler_view_eight)
    RecyclerView recyclerViewEight;
    @BindView(R.id.txt_start_eight)
    TextView txtStartEight;
    @BindView(R.id.txt_end_eight)
    TextView txtEndEight;
    @BindView(R.id.txt_nine)
    TextView txtNine;
    @BindView(R.id.recycler_view_nine)
    RecyclerView recyclerViewNine;
    @BindView(R.id.txt_start_nine)
    TextView txtStartNine;
    @BindView(R.id.txt_end_nine)
    TextView txtEndNine;
    @BindView(R.id.txt_ten)
    TextView txtTen;
    @BindView(R.id.recycler_view_ten)
    RecyclerView recyclerViewTen;
    @BindView(R.id.txt_start_ten)
    TextView txtStartTen;
    @BindView(R.id.txt_end_ten)
    TextView txtEndTen;
    @BindView(R.id.txt_eleven)
    TextView txtEleven;
    @BindView(R.id.recycler_view_eleven)
    RecyclerView recyclerViewEleven;
    @BindView(R.id.txt_start_eleven)
    TextView txtStartEleven;
    @BindView(R.id.txt_end_eleven)
    TextView txtEndEleven;
    @BindView(R.id.txt_twelve)
    TextView txtTwelve;
    @BindView(R.id.recycler_view_twelve)
    RecyclerView recyclerViewTwelve;
    @BindView(R.id.txt_start_twelve)
    TextView txtStartTwelve;
    @BindView(R.id.txt_end_twelve)
    TextView txtEndTwelve;


    String report_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report_two);
        ButterKnife.bind(this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());

        weeklyTwoViewModel = ViewModelProviders.of(this).get(WeeklyTwoViewModel.class);
        report_id = getIntent().getStringExtra("report_id");
        namesList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            namesList.add(String.valueOf(i + 1));
        }
        //FirstList
        selectedIdOne = namesList.size() - 1;

        Collections.reverse(namesList);
        adapterOne = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewOne.setHasFixedSize(true);
        recyclerViewOne.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewOne.setAdapter(adapterOne);

        adapterTwo = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewTwo.setHasFixedSize(true);
        recyclerViewTwo.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewTwo.setAdapter(adapterTwo);

        adapterThree = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewThree.setHasFixedSize(true);
        recyclerViewThree.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewThree.setAdapter(adapterThree);

        adapterFour = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewFour.setHasFixedSize(true);
        recyclerViewFour.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewFour.setAdapter(adapterFour);

        adapterFive = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewFive.setHasFixedSize(true);
        recyclerViewFive.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewFive.setAdapter(adapterFive);

        adapterSix = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewSix.setHasFixedSize(true);
        recyclerViewSix.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewSix.setAdapter(adapterSix);

        adapterSeven = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewSeven.setHasFixedSize(true);
        recyclerViewSeven.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewSeven.setAdapter(adapterSeven);

        adapterEight = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewEight.setHasFixedSize(true);
        recyclerViewEight.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewEight.setAdapter(adapterEight);

        adapterNine = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewNine.setHasFixedSize(true);
        recyclerViewNine.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewNine.setAdapter(adapterNine);

        adapterTen = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewTen.setHasFixedSize(true);
        recyclerViewTen.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewTen.setAdapter(adapterTen);

        adapterEleven = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewEleven.setHasFixedSize(true);
        recyclerViewEleven.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewEleven.setAdapter(adapterEleven);

        adapterTwelve = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewTwelve.setHasFixedSize(true);
        recyclerViewTwelve.setLayoutManager(new LinearLayoutManager(WeeklyReportTwoActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewTwelve.setAdapter(adapterTwelve);


        getData();
    }




    private void getData() {
        weeklyTwoViewModel.getWeeklyTwo(WeeklyReportTwoActivity.this).observe(this, new Observer<WeeklyTwoResponse>() {
            @Override
            public void onChanged(@Nullable WeeklyTwoResponse twoResponse) {
                try {
                    if (twoResponse.isStatus()) {

                        txtOne.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(0).getWimsQuestion());
                        txtStartOne.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(0).getWimsStartText());
                        txtEndOne.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(0).getWimsEndText());

                        txtTwo.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(1).getWimsQuestion());
                        txtStartTwo.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(1).getWimsStartText());
                        txtEndTwo.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(1).getWimsEndText());

                        txtThree.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(2).getWimsQuestion());
                        txtStartThree.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(2).getWimsStartText());
                        txtEndThree.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(2).getWimsEndText());

                        txtFour.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(3).getWimsQuestion());
                        txtStartFour.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(3).getWimsStartText());
                        txtEndFour.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(3).getWimsEndText());

                        txtFive.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(4).getWimsQuestion());
                        txtStartFive.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(4).getWimsStartText());
                        txtEndFive.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(4).getWimsEndText());

                        txtSix.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(5).getWimsQuestion());
                        txtStartSix.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(5).getWimsStartText());
                        txtEndSix.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(5).getWimsEndText());

                        txtSeven.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(6).getWimsQuestion());
                        txtStartSeven.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(6).getWimsStartText());
                        txtEndSeven.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(6).getWimsEndText());

                        txtEight.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(7).getWimsQuestion());
                        txtStartEight.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(7).getWimsStartText());
                        txtEndEight.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(7).getWimsEndText());

                        txtNine.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(8).getWimsQuestion());
                        txtStartNine.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(8).getWimsStartText());
                        txtEndNine.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(8).getWimsEndText());

                        txtTen.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(9).getWimsQuestion());
                        txtStartTen.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(9).getWimsStartText());
                        txtEndTen.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(9).getWimsEndText());

                        txtEleven.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(10).getWimsQuestion());
                        txtStartEleven.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(10).getWimsStartText());
                        txtEndEleven.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(10).getWimsEndText());

                        txtTwelve.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(11).getWimsQuestion());
                        txtStartTwelve.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(11).getWimsStartText());
                        txtEndTwelve.setText(twoResponse.getData().getWeeklyWeeklyIMS12QuestionData().get(11).getWimsEndText());


                    } else {
                        Toast.makeText(WeeklyReportTwoActivity.this, "" + twoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick({R.id.card_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_back:
                finish();
                break;
            case R.id.btn_next:


                JsonArray jsonArray = new JsonArray();
                JsonObject jsonObjectOne, jsonObjectSecond, jsonObjectThree, jsonObjectFour,
                           jsonObjectFive, jsonObjectSix, jsonObjectSeven, jsonObjectEight,
                           jsonObjectNine, jsonObjectTen, jsonObjectEleven, jsonObjectTwelve;

                jsonObjectOne = new JsonObject();
                jsonObjectOne.addProperty("pawims_ims_id", "1");
                jsonObjectOne.addProperty("pawims_answer", adapterOne.getSelectedId());
                jsonArray.add(jsonObjectOne);

                jsonObjectSecond = new JsonObject();
                jsonObjectSecond.addProperty("pawims_ims_id", "2");
                jsonObjectSecond.addProperty("pawims_answer", adapterTwo.getSelectedId());
                jsonArray.add(jsonObjectSecond);

                jsonObjectThree = new JsonObject();
                jsonObjectThree.addProperty("pawims_ims_id", "3");
                jsonObjectThree.addProperty("pawims_answer", adapterThree.getSelectedId());
                jsonArray.add(jsonObjectThree);

                jsonObjectFour = new JsonObject();
                jsonObjectFour.addProperty("pawims_ims_id", "4");
                jsonObjectFour.addProperty("pawims_answer", adapterFour.getSelectedId());
                jsonArray.add(jsonObjectFour);

                jsonObjectFive = new JsonObject();
                jsonObjectFive.addProperty("pawims_ims_id", "5");
                jsonObjectFive.addProperty("pawims_answer", adapterFive.getSelectedId());
                jsonArray.add(jsonObjectFive);

                jsonObjectSix = new JsonObject();
                jsonObjectSix.addProperty("pawims_ims_id", "6");
                jsonObjectSix.addProperty("pawims_answer", adapterSix.getSelectedId());
                jsonArray.add(jsonObjectSix);

                jsonObjectSeven = new JsonObject();
                jsonObjectSeven.addProperty("pawims_ims_id", "7");
                jsonObjectSeven.addProperty("pawims_answer",adapterSeven.getSelectedId());
                jsonArray.add(jsonObjectSeven);

                jsonObjectEight = new JsonObject();
                jsonObjectEight.addProperty("pawims_ims_id", "8");
                jsonObjectEight.addProperty("pawims_answer",adapterEight.getSelectedId());
                jsonArray.add(jsonObjectEight);


                jsonObjectNine = new JsonObject();
                jsonObjectNine.addProperty("pawims_ims_id", "9");
                jsonObjectNine.addProperty("pawims_answer",adapterNine.getSelectedId());
                jsonArray.add(jsonObjectNine);

                jsonObjectTen = new JsonObject();
                jsonObjectTen.addProperty("pawims_ims_id", "10");
                jsonObjectTen.addProperty("pawims_answer",adapterTen.getSelectedId());
                jsonArray.add(jsonObjectTen);

                jsonObjectEleven = new JsonObject();
                jsonObjectEleven.addProperty("pawims_ims_id", "11");
                jsonObjectEleven.addProperty("pawims_answer",adapterEleven.getSelectedId());
                jsonArray.add(jsonObjectEleven);

                jsonObjectTwelve = new JsonObject();
                jsonObjectTwelve.addProperty("pawims_ims_id", "12");
                jsonObjectTwelve.addProperty("pawims_answer",adapterTwelve.getSelectedId());
                jsonArray.add(jsonObjectTwelve);


                weeklyTwoViewModel.saveWeeklyTwo(WeeklyReportTwoActivity.this,jsonArray.toString(),report_id).observe(this, new Observer<SaveWeeklyResponse>() {
                    @Override
                    public void onChanged(@androidx.annotation.Nullable SaveWeeklyResponse saveWeeklyResponse) {
                        try {
                            if (saveWeeklyResponse.isStatus()) {
                                //  Toast.makeText(this, "" + adapterOne.getSelectedId(), Toast.LENGTH_SHORT).show();
                                Intent n = new Intent(WeeklyReportTwoActivity.this, WeeklyReportThreeActivity.class);
                                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                n.putExtra("report_id",report_id);
                                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(WeeklyReportTwoActivity.this).toBundle());
                            } else {
                                Toast.makeText(WeeklyReportTwoActivity.this, "" + saveWeeklyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                });




                break;
        }
    }
}
