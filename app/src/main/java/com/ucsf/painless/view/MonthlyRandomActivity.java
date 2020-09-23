package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.R;
import com.ucsf.painless.adapter.MonthlyRandomRecyclerViewAdapter;
import com.ucsf.painless.model.monthlyRandom.MonthlyRandomResponse;
import com.ucsf.painless.model.saveMonthlyReport.SaveMonthlyResponse;
import com.ucsf.painless.viewModel.MonthlyRandQuesViewModel;
import com.ucsf.painless.viewModel.SaveMonthlyViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonthlyRandomActivity extends AppCompatActivity {
    @BindView(R.id.package_txt)
    TextView packageTxt;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.card_back)
    CardView cardBack;
    private RecyclerView packageRecyclerView;
    List<MonthlyRandomResponse> modelList;
    MonthlyRandQuesViewModel monthlyRandQuesViewModel;

    MonthlyRandomResponse monthlyRandomResponse;
    SaveMonthlyViewModel saveMonthlyRandomViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly);
        ButterKnife.bind(this);
        monthlyRandQuesViewModel = ViewModelProviders.of(this).get(MonthlyRandQuesViewModel.class);
        saveMonthlyRandomViewModel = ViewModelProviders.of(this).get(SaveMonthlyViewModel.class);

        packageRecyclerView = (RecyclerView) findViewById(R.id.package_lst);


        monthlyRandQuesViewModel.getMonthlyRandQues(MonthlyRandomActivity.this).observe(this, new Observer<MonthlyRandomResponse>() {
            @Override
            public void onChanged(@Nullable MonthlyRandomResponse randomResponse) {
                try {
                    if (randomResponse.isStatus()) {

                        monthlyRandomResponse = randomResponse;
                        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(MonthlyRandomActivity.this);
                        packageRecyclerView.setLayoutManager(recyclerLayoutManager);

                        DividerItemDecoration dividerItemDecoration =
                                new DividerItemDecoration(packageRecyclerView.getContext(),
                                        recyclerLayoutManager.getOrientation());
                        packageRecyclerView.addItemDecoration(dividerItemDecoration);

                        MonthlyRandomRecyclerViewAdapter recyclerViewAdapter = new
                                MonthlyRandomRecyclerViewAdapter(monthlyRandomResponse, MonthlyRandomActivity.this);
                        packageRecyclerView.setAdapter(recyclerViewAdapter);


                    } else {
                        Toast.makeText(MonthlyRandomActivity.this, "" + randomResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });


    }

    private List<MonthlyRandomResponse> getPackages() {
        modelList = new ArrayList<MonthlyRandomResponse>();
        return modelList;
    }

    @OnClick(R.id.package_txt)
    public void onViewClicked() {

        /*for (int i = 0; i <modelList.size() ; i++) {
            if (modelList.get(i).getSelectedOption()!=null)
            Toast.makeText(this, ""+modelList.get(i).getName()+"::"+modelList.get(i).getSelectedOption(), Toast.LENGTH_SHORT).show();
        }*/


    }

    @OnClick(R.id.btn_next)
    public void onNextViewClicked() {

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < monthlyRandomResponse.getData().getMonthlyRand36QuestionData().size(); i++) {
            if (monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getRadioButtonSelected() != null) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("pamr_mrque_id", monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getMrqueQuestionNumber());
                    jsonObject.put("pamr_answer", monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getRadioButtonSelected());
                    //Toast.makeText(this, "" + monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getMrqueQuestionNumber() + "::" + monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getRadioButtonSelected(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
            }
        }


        if (monthlyRandomResponse.getData().getMonthlyRand36QuestionData().size()==jsonArray.length()){

        saveMonthlyRandomViewModel.saveMonthly(jsonArray.toString(), MonthlyRandomActivity.this).observe(this, new Observer<SaveMonthlyResponse>() {
            @Override
            public void onChanged(@Nullable SaveMonthlyResponse saveMonthlyResponse) {
                try {
                    if (saveMonthlyResponse.isStatus()) {
                        Intent n = new Intent(MonthlyRandomActivity.this, MonthlyClinicalImpressionActivity.class);
                        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        n.putExtra("reportId", String.valueOf(saveMonthlyResponse.getData().getReportId()));
                        startActivity(n, ActivityOptions.makeSceneTransitionAnimation(MonthlyRandomActivity.this).toBundle());
                    } else {
                        Toast.makeText(MonthlyRandomActivity.this, "" + saveMonthlyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
        }else {
            Toast.makeText(this, "Please answer all the questions given above", Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick(R.id.card_back)
    public void onBackViewClicked() {
        finish();
    }
}

