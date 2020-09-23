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
import com.ucsf.painless.adapter.MonthlyClinicalGlobalImpressionsRecyclerViewAdapter;
import com.ucsf.painless.model.clinicalGlobalImpressions.ClinicalGlobalImpressionsResponse;
import com.ucsf.painless.model.monthlyRandom.MonthlyRandomResponse;
import com.ucsf.painless.model.saveMonthlyReport.SaveMonthlyResponse;
import com.ucsf.painless.viewModel.MonthlyRandQuesViewModel;
import com.ucsf.painless.viewModel.SaveMonthlyViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonthlyClinicalImpressionActivity extends AppCompatActivity {
    @BindView(R.id.package_txt)
    TextView packageTxt;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.card_back)
    CardView cardBack;
    private RecyclerView packageRecyclerView;
    List<MonthlyRandomResponse> modelList;
    MonthlyRandQuesViewModel monthlyRandQuesViewModel;
    ClinicalGlobalImpressionsResponse globalImpressionsResponse;
    SaveMonthlyViewModel saveMonthlyViewModel;
    String reportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_clinical);
        ButterKnife.bind(this);
        monthlyRandQuesViewModel = ViewModelProviders.of(this).get(MonthlyRandQuesViewModel.class);
        saveMonthlyViewModel = ViewModelProviders.of(this).get(SaveMonthlyViewModel.class);
        reportId = getIntent().getStringExtra("reportId");
        packageRecyclerView = (RecyclerView) findViewById(R.id.package_lst);


        monthlyRandQuesViewModel.getClinicalGlobalImpressionsQuestion(MonthlyClinicalImpressionActivity.this).observe(this, new Observer<ClinicalGlobalImpressionsResponse>() {
            @Override
            public void onChanged(@Nullable ClinicalGlobalImpressionsResponse clinicalGlobalImpressionsResponse) {
                try {
                    if (clinicalGlobalImpressionsResponse.isStatus()) {

                        globalImpressionsResponse = clinicalGlobalImpressionsResponse;

                        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(MonthlyClinicalImpressionActivity.this);
                        packageRecyclerView.setLayoutManager(recyclerLayoutManager);

                        DividerItemDecoration dividerItemDecoration =
                                new DividerItemDecoration(packageRecyclerView.getContext(),
                                        recyclerLayoutManager.getOrientation());
                        packageRecyclerView.addItemDecoration(dividerItemDecoration);

                        MonthlyClinicalGlobalImpressionsRecyclerViewAdapter recyclerViewAdapter = new
                                MonthlyClinicalGlobalImpressionsRecyclerViewAdapter(clinicalGlobalImpressionsResponse, MonthlyClinicalImpressionActivity.this);
                        packageRecyclerView.setAdapter(recyclerViewAdapter);


                    } else {
                        Toast.makeText(MonthlyClinicalImpressionActivity.this, "" + clinicalGlobalImpressionsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });


    }


    @OnClick(R.id.package_txt)
    public void onViewClicked() {


    }

    @OnClick(R.id.btn_next)
    public void onNextViewClicked() {

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < globalImpressionsResponse.getData().getMonthlyClinicalGlobalImpressionScalesQuestionData().size(); i++) {
            if (globalImpressionsResponse.getData().getMonthlyClinicalGlobalImpressionScalesQuestionData().get(i).getRadioButtonSelected() != null) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("pamcgis_cgis_id", globalImpressionsResponse.getData().getMonthlyClinicalGlobalImpressionScalesQuestionData().get(i).getCgisQuestionNumber());
                    jsonObject.put("pamcgis_answer", globalImpressionsResponse.getData().getMonthlyClinicalGlobalImpressionScalesQuestionData().get(i).getRadioButtonSelected());
                    //Toast.makeText(this, "" + monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getMrqueQuestionNumber() + "::" + monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getRadioButtonSelected(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
            }
        }

        if (globalImpressionsResponse.getData().getMonthlyClinicalGlobalImpressionScalesQuestionData().size()==jsonArray.length()){

        saveMonthlyViewModel.saveMonthlyClinical(jsonArray.toString(), reportId, MonthlyClinicalImpressionActivity.this).observe(this, new Observer<SaveMonthlyResponse>() {
            @Override
            public void onChanged(@Nullable SaveMonthlyResponse saveMonthlyResponse) {
                try {
                    if (saveMonthlyResponse.isStatus()) {
                        Intent n = new Intent(MonthlyClinicalImpressionActivity.this, MonthlyBeckDepressionInventoryActivity.class);
                        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        n.putExtra("reportId", String.valueOf(saveMonthlyResponse.getData().getReportId()));
                        startActivity(n, ActivityOptions.makeSceneTransitionAnimation(MonthlyClinicalImpressionActivity.this).toBundle());
                    } else {
                        Toast.makeText(MonthlyClinicalImpressionActivity.this, "" + saveMonthlyResponse.getMessage(), Toast.LENGTH_SHORT).show();
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

