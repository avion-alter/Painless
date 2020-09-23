package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.ucsf.painless.adapter.WeeklyYesNoSelectionAdapter;
import com.ucsf.painless.model.saveWeekly.SaveWeeklyResponse;
import com.ucsf.painless.model.weeklyFour.WeeklyFourResponse;
import com.ucsf.painless.utils.ClickListener;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.viewModel.WeeklyFourViewModel;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeeklyReportFourActivity extends AppCompatActivity {
    List<String> namesList;

    Integer selectedIdOne;
    WeeklyYesNoSelectionAdapter adapterOne;

    Integer selectedIdTwo = 0;
    WeeklyYesNoSelectionAdapter adapterTwo;

    Integer selectedIdThree;
    WeeklyYesNoSelectionAdapter adapterThree;

    Integer selectedIdFour;
    WeeklyYesNoSelectionAdapter adapterFour;

    Integer selectedIdFive = 0;
    WeeklyYesNoSelectionAdapter adapterFive;

    Integer selectedIdSix;
    WeeklyYesNoSelectionAdapter adapterSix;

    Integer selectedIdSeven;
    WeeklyYesNoSelectionAdapter adapterSeven;

    Integer selectedIdEight;
    WeeklyYesNoSelectionAdapter adapterEight;


    WeeklyFourViewModel weeklyFourViewModel;
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.recycler_view_one)
    RecyclerView recyclerViewOne;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.txt_one)
    TextView txtOne;

    @BindView(R.id.txt_two)
    TextView txtTwo;
    @BindView(R.id.recycler_view_two)
    RecyclerView recyclerViewTwo;

    @BindView(R.id.txt_three)
    TextView txtThree;
    @BindView(R.id.recycler_view_three)
    RecyclerView recyclerViewThree;

    @BindView(R.id.txt_four)
    TextView txtFour;
    @BindView(R.id.recycler_view_four)
    RecyclerView recyclerViewFour;

    @BindView(R.id.txt_five)
    TextView txtFive;
    @BindView(R.id.recycler_view_five)
    RecyclerView recyclerViewFive;

    @BindView(R.id.txt_six)
    TextView txtSix;
    @BindView(R.id.recycler_view_six)
    RecyclerView recyclerViewSix;

    @BindView(R.id.txt_seven)
    TextView txtSeven;
    @BindView(R.id.recycler_view_seven)
    RecyclerView recyclerViewSeven;
    @BindView(R.id.txt_eight)
    TextView txtEight;
    @BindView(R.id.recycler_view_eight)
    RecyclerView recyclerViewEight;
    String report_id;
    @BindView(R.id.linear_two)
    LinearLayout linearTwo;
    @BindView(R.id.linear_five)
    LinearLayout linearFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report_four);
        ButterKnife.bind(this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        report_id = getIntent().getStringExtra("report_id");
        weeklyFourViewModel = ViewModelProviders.of(this).get(WeeklyFourViewModel.class);

        namesList = new ArrayList<>();
        namesList.add("Yes");
        namesList.add("No");
        //FirstList
        selectedIdOne = namesList.size() - 1;

        Collections.reverse(namesList);
        adapterOne = new WeeklyYesNoSelectionAdapter(namesList, selectedIdOne);
        recyclerViewOne.setHasFixedSize(true);
        recyclerViewOne.setLayoutManager(new LinearLayoutManager(WeeklyReportFourActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewOne.setAdapter(adapterOne);

        adapterTwo = new WeeklyYesNoSelectionAdapter(namesList, selectedIdTwo);
        recyclerViewTwo.setHasFixedSize(true);
        recyclerViewTwo.setLayoutManager(new LinearLayoutManager(WeeklyReportFourActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewTwo.setAdapter(adapterTwo);

        adapterThree = new WeeklyYesNoSelectionAdapter(namesList, selectedIdOne);
        recyclerViewThree.setHasFixedSize(true);
        recyclerViewThree.setLayoutManager(new LinearLayoutManager(WeeklyReportFourActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewThree.setAdapter(adapterThree);

        adapterFour = new WeeklyYesNoSelectionAdapter(namesList, selectedIdOne);
        recyclerViewFour.setHasFixedSize(true);
        recyclerViewFour.setLayoutManager(new LinearLayoutManager(WeeklyReportFourActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewFour.setAdapter(adapterFour);

        adapterFive = new WeeklyYesNoSelectionAdapter(namesList, selectedIdFive);
        recyclerViewFive.setHasFixedSize(true);
        recyclerViewFive.setLayoutManager(new LinearLayoutManager(WeeklyReportFourActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewFive.setAdapter(adapterFive);

        adapterSix = new WeeklyYesNoSelectionAdapter(namesList, selectedIdOne);
        recyclerViewSix.setHasFixedSize(true);
        recyclerViewSix.setLayoutManager(new LinearLayoutManager(WeeklyReportFourActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewSix.setAdapter(adapterSix);

        adapterSeven = new WeeklyYesNoSelectionAdapter(namesList, selectedIdOne);
        recyclerViewSeven.setHasFixedSize(true);
        recyclerViewSeven.setLayoutManager(new LinearLayoutManager(WeeklyReportFourActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewSeven.setAdapter(adapterSeven);

        adapterEight = new WeeklyYesNoSelectionAdapter(namesList, selectedIdOne);
        recyclerViewEight.setHasFixedSize(true);
        recyclerViewEight.setLayoutManager(new LinearLayoutManager(WeeklyReportFourActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewEight.setAdapter(adapterEight);

        recyclerViewTwo.addOnItemTouchListener(new WeeklyYesNoSelectionAdapter(WeeklyReportFourActivity.this,
                recyclerViewOne, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                    if (position==1){
                        linearTwo.setVisibility(View.VISIBLE);
                    }else {
                        linearTwo.setVisibility(View.GONE);
                        linearFive.setVisibility(View.GONE);
                    }
            }

            @Override
            public void onLongClick(View view, int position) {
               /* if (position == namesList.size() - 1) {
                    editOther.setVisibility(View.VISIBLE);
                } else {
                    editOther.setVisibility(View.GONE);
                }*/
            }
        }));

        recyclerViewFive.addOnItemTouchListener(new WeeklyYesNoSelectionAdapter(WeeklyReportFourActivity.this,
                recyclerViewOne, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                    if (position==1){
                        linearFive.setVisibility(View.VISIBLE);
                    }else {
                        linearFive.setVisibility(View.GONE);
                    }
            }

            @Override
            public void onLongClick(View view, int position) {
               /* if (position == namesList.size() - 1) {
                    editOther.setVisibility(View.VISIBLE);
                } else {
                    editOther.setVisibility(View.GONE);
                }*/
            }
        }));


        getData();
    }


    private void getData() {
        weeklyFourViewModel.getWeeklyFour(WeeklyReportFourActivity.this).observe(this, new Observer<WeeklyFourResponse>() {
            @Override
            public void onChanged(@Nullable WeeklyFourResponse fourResponse) {
                try {
                    if (fourResponse.isStatus()) {

                        txtOne.setText(fourResponse.getData().getWeeklyColumbiaSuicideSeverityRatingScaleQuestionData().get(0).getCssrsQuestion());


                        txtTwo.setText(fourResponse.getData().getWeeklyColumbiaSuicideSeverityRatingScaleQuestionData().get(1).getCssrsQuestion());


                        txtThree.setText(fourResponse.getData().getWeeklyColumbiaSuicideSeverityRatingScaleQuestionData().get(2).getCssrsQuestion());


                        txtFour.setText(fourResponse.getData().getWeeklyColumbiaSuicideSeverityRatingScaleQuestionData().get(3).getCssrsQuestion());


                        txtFive.setText(fourResponse.getData().getWeeklyColumbiaSuicideSeverityRatingScaleQuestionData().get(4).getCssrsQuestion());


                        txtSix.setText(fourResponse.getData().getWeeklyColumbiaSuicideSeverityRatingScaleQuestionData().get(5).getCssrsQuestion());


                        txtSeven.setText(fourResponse.getData().getWeeklyColumbiaSuicideSeverityRatingScaleQuestionData().get(6).getCssrsQuestion());


                        txtEight.setText(fourResponse.getData().getWeeklyColumbiaSuicideSeverityRatingScaleQuestionData().get(7).getCssrsQuestion());


                    } else {
                        Toast.makeText(WeeklyReportFourActivity.this, "" + fourResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
                        jsonObjectNine, jsonObjectTen, jsonObjectEleven, jsonObjectTwelve, jsonObjectThirteen;

                jsonObjectOne = new JsonObject();
                jsonObjectOne.addProperty("pawcssrs_cssrs_id", "1");
                jsonObjectOne.addProperty("pawcssrs_answer", adapterOne.getSelectedId());
                jsonArray.add(jsonObjectOne);

                jsonObjectSecond = new JsonObject();
                jsonObjectSecond.addProperty("pawcssrs_cssrs_id", "2");
                jsonObjectSecond.addProperty("pawcssrs_answer", adapterTwo.getSelectedId());
                jsonArray.add(jsonObjectSecond);

                jsonObjectThree = new JsonObject();
                jsonObjectThree.addProperty("pawcssrs_cssrs_id", "3");
                jsonObjectThree.addProperty("pawcssrs_answer", adapterThree.getSelectedId());
                jsonArray.add(jsonObjectThree);

                jsonObjectFour = new JsonObject();
                jsonObjectFour.addProperty("pawcssrs_cssrs_id", "4");
                jsonObjectFour.addProperty("pawcssrs_answer", adapterFour.getSelectedId());
                jsonArray.add(jsonObjectFour);

                jsonObjectFive = new JsonObject();
                jsonObjectFive.addProperty("pawcssrs_cssrs_id", "5");
                jsonObjectFive.addProperty("pawcssrs_answer", adapterFive.getSelectedId());
                jsonArray.add(jsonObjectFive);

                jsonObjectSix = new JsonObject();
                jsonObjectSix.addProperty("pawcssrs_cssrs_id", "6");
                jsonObjectSix.addProperty("pawcssrs_answer", adapterSix.getSelectedId());
                jsonArray.add(jsonObjectSix);

                jsonObjectSeven = new JsonObject();
                jsonObjectSeven.addProperty("pawcssrs_cssrs_id", "7");
                jsonObjectSeven.addProperty("pawcssrs_answer", adapterSeven.getSelectedId());
                jsonArray.add(jsonObjectSeven);

                jsonObjectEight = new JsonObject();
                jsonObjectEight.addProperty("pawcssrs_cssrs_id", "8");
                jsonObjectEight.addProperty("pawcssrs_answer", adapterEight.getSelectedId());
                jsonArray.add(jsonObjectEight);

                weeklyFourViewModel.saveWeeklyFour(WeeklyReportFourActivity.this, jsonArray.toString(), report_id).observe(this, new Observer<SaveWeeklyResponse>() {
                    @Override
                    public void onChanged(@androidx.annotation.Nullable SaveWeeklyResponse saveWeeklyResponse) {
                        try {
                            if (saveWeeklyResponse.isStatus()) {
                                //Toast.makeText(this, "" + adapterOne.getSelectedId(), Toast.LENGTH_SHORT).show();
                                Intent n = new Intent(WeeklyReportFourActivity.this, WeeklyReportFiveActivity.class);
                                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                n.putExtra("report_id", report_id);
                                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(WeeklyReportFourActivity.this).toBundle());
                            } else {
                                Toast.makeText(WeeklyReportFourActivity.this, "" + saveWeeklyResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
