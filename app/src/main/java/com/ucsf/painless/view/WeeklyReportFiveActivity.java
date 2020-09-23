package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.ucsf.painless.adapter.WeeklyFiveSelectionAdapter;
import com.ucsf.painless.model.saveWeekly.SaveWeeklyResponse;
import com.ucsf.painless.model.weeklyFive.WeeklyFiveResponse;
import com.ucsf.painless.utils.ClickListener;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.viewModel.WeeklyFiveViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeeklyReportFiveActivity extends AppCompatActivity {
    List<String> namesList;
    List<Integer> selectedId;

    Integer selectedIdOne;
    WeeklyFiveSelectionAdapter adapterOne;


    WeeklyFiveViewModel weeklyFiveViewModel;

    @BindView(R.id.card_back)
    CardView cardBack;

    @BindView(R.id.recycler_view_one)
    RecyclerView recyclerViewOne;

    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.edit_other)
    EditText editOther;

    String report_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report_five);
        ButterKnife.bind(this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        report_id = getIntent().getStringExtra("report_id");
        weeklyFiveViewModel = ViewModelProviders.of(this).get(WeeklyFiveViewModel.class);


        weeklyFiveViewModel.getWeeklyFive(WeeklyReportFiveActivity.this).observe(this, new Observer<WeeklyFiveResponse>() {
            @Override
            public void onChanged(@Nullable WeeklyFiveResponse fiveResponse) {
                try {
                    if (fiveResponse.isStatus()) {

                        namesList = new ArrayList<>();
                        selectedId = new ArrayList<>();
                        for (int i = 0; i < fiveResponse.getData().getWeeklyEventReportingQuestionData().size(); i++) {
                            namesList.add(fiveResponse.getData().getWeeklyEventReportingQuestionData().get(i).getWerName());
                        }
                        //FirstList
                        selectedIdOne = 0;
                        adapterOne = new WeeklyFiveSelectionAdapter(namesList, selectedId, selectedIdOne);
                        recyclerViewOne.setHasFixedSize(true);
                        recyclerViewOne.setLayoutManager(new LinearLayoutManager(WeeklyReportFiveActivity.this));
                        recyclerViewOne.setAdapter(adapterOne);

                    } else {
                        Toast.makeText(WeeklyReportFiveActivity.this, "" + fiveResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });

        recyclerViewOne.addOnItemTouchListener(new WeeklyFiveSelectionAdapter(WeeklyReportFiveActivity.this,
                recyclerViewOne, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                if (position == namesList.size() - 1) {
                    editOther.setVisibility(View.VISIBLE);
                } else {
                    editOther.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                if (position == namesList.size() - 1) {
                    editOther.setVisibility(View.VISIBLE);
                } else {
                    editOther.setVisibility(View.GONE);
                }
            }
        }));

    }

    @OnClick({R.id.card_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_back:
                finish();
                break;
            case R.id.btn_next:

                /*Log.e("getSelectedId",adapterOne.getSelectedId().toString());
                Log.e("size", String.valueOf(namesList.size()));*/
                if (adapterOne.getSelectedId().contains(namesList.size() - 1) && editOther.getText().toString().equals("")) {
                    editOther.setError("Please add other");
                    if (view.requestFocus()) {
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    }
                } else {
                    JsonArray jsonArray = new JsonArray();
                    JsonObject jsonObjectOne;
                    for (int i = 0; i < adapterOne.getSelectedId().size(); i++) {
                        if (adapterOne.getSelectedId().get(i) == namesList.size() - 1) {
                            jsonObjectOne = new JsonObject();
                            jsonObjectOne.addProperty("pawer_answer", editOther.getText().toString());
                            jsonArray.add(jsonObjectOne);
                        } else {
                            jsonObjectOne = new JsonObject();
                            jsonObjectOne.addProperty("pawer_answer", adapterOne.getSelectedId().get(i) + 1);
                            jsonArray.add(jsonObjectOne);
                        }
                    }
                    Log.e("Selected", jsonArray.toString());
                    if (jsonArray.size() == 0) {
                        Toast.makeText(this, "Please select at lease one event", Toast.LENGTH_SHORT).show();
                    } else {
                        weeklyFiveViewModel.saveWeeklyFive(WeeklyReportFiveActivity.this, jsonArray.toString(), report_id).observe(this, new Observer<SaveWeeklyResponse>() {
                            @Override
                            public void onChanged(@androidx.annotation.Nullable SaveWeeklyResponse saveWeeklyResponse) {
                                try {
                                    if (saveWeeklyResponse.isStatus()) {
                                        Intent n = new Intent(WeeklyReportFiveActivity.this, CongratulationsScreenActivity.class);
                                        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        n.putExtra("msg", "You just completed a weekly report");
                                        startActivity(n);
                                    } else {
                                        Toast.makeText(WeeklyReportFiveActivity.this, "" + saveWeeklyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }


                break;
        }
    }

}
