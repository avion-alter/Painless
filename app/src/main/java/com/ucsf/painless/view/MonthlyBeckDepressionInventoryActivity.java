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
import com.ucsf.painless.adapter.MonthlyBeckDepressionRecyclerViewAdapter;
import com.ucsf.painless.model.monthlyBackDepression.MonthlyBeckDepressionResponse;
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

public class MonthlyBeckDepressionInventoryActivity extends AppCompatActivity {
    @BindView(R.id.package_txt)
    TextView packageTxt;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.txt_decs)
    TextView txtDecs;
    private RecyclerView packageRecyclerView;
    List<MonthlyRandomResponse> modelList;
    MonthlyRandQuesViewModel monthlyRandQuesViewModel;
    SaveMonthlyViewModel saveMonthlyViewModel;
    MonthlyBeckDepressionResponse monthlyBeckDepressionResponse;

    String reportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_beck_depression);
        ButterKnife.bind(this);
        monthlyRandQuesViewModel = ViewModelProviders.of(this).get(MonthlyRandQuesViewModel.class);
        saveMonthlyViewModel = ViewModelProviders.of(this).get(SaveMonthlyViewModel.class);
        packageRecyclerView = (RecyclerView) findViewById(R.id.package_lst);
        reportId = getIntent().getStringExtra("reportId");

        monthlyRandQuesViewModel.getMonthlyBeckDepression(MonthlyBeckDepressionInventoryActivity.this).observe(this, new Observer<MonthlyBeckDepressionResponse>() {
            @Override
            public void onChanged(@Nullable MonthlyBeckDepressionResponse randomResponse) {
                try {
                    if (randomResponse.isStatus()) {
                        txtDecs.setText(randomResponse.getData().getMonthlyBeckDepressionInventoryQuestionData().get(0).getBdiQuestionDescription());
                        monthlyBeckDepressionResponse = randomResponse;
                        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(MonthlyBeckDepressionInventoryActivity.this);
                        packageRecyclerView.setLayoutManager(recyclerLayoutManager);

                        DividerItemDecoration dividerItemDecoration =
                                new DividerItemDecoration(packageRecyclerView.getContext(),
                                        recyclerLayoutManager.getOrientation());
                        packageRecyclerView.addItemDecoration(dividerItemDecoration);

                        MonthlyBeckDepressionRecyclerViewAdapter recyclerViewAdapter = new
                                MonthlyBeckDepressionRecyclerViewAdapter(monthlyBeckDepressionResponse, MonthlyBeckDepressionInventoryActivity.this);
                        packageRecyclerView.setAdapter(recyclerViewAdapter);


                    } else {
                        Toast.makeText(MonthlyBeckDepressionInventoryActivity.this, "" + randomResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onBackViewClicked() {

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < monthlyBeckDepressionResponse.getData().getMonthlyBeckDepressionInventoryQuestionData().size(); i++) {
            if (monthlyBeckDepressionResponse.getData().getMonthlyBeckDepressionInventoryQuestionData().get(i).getRadioButtonSelected() != null) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("pambai_bai_id", monthlyBeckDepressionResponse.getData().getMonthlyBeckDepressionInventoryQuestionData().get(i).getBdiQuestionNumber());
                    jsonObject.put("pambai_answer", monthlyBeckDepressionResponse.getData().getMonthlyBeckDepressionInventoryQuestionData().get(i).getRadioButtonSelected());
                    //Toast.makeText(this, "" + monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getMrqueQuestionNumber() + "::" + monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getRadioButtonSelected(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
            }
        }

        if (monthlyBeckDepressionResponse.getData().getMonthlyBeckDepressionInventoryQuestionData().size() == jsonArray.length()) {
            saveMonthlyViewModel.saveMonthlyBeckDepression(jsonArray.toString(), reportId, MonthlyBeckDepressionInventoryActivity.this).observe(this, new Observer<SaveMonthlyResponse>() {
                @Override
                public void onChanged(@Nullable SaveMonthlyResponse saveMonthlyResponse) {
                    try {
                        if (saveMonthlyResponse.isStatus()) {
                            Intent n = new Intent(MonthlyBeckDepressionInventoryActivity.this, MonthlyBeckAnxietyInventoryActivity.class);
                            n.putExtra("reportId", String.valueOf(saveMonthlyResponse.getData().getReportId()));
                            n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(n, ActivityOptions.makeSceneTransitionAnimation(MonthlyBeckDepressionInventoryActivity.this).toBundle());
                        } else {
                            Toast.makeText(MonthlyBeckDepressionInventoryActivity.this, "" + saveMonthlyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Please answer all the questions given above", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.card_back)
    public void onFinishViewClicked() {
        finish();

    }
}


