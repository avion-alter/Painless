package com.ucsf.painless.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
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
import com.ucsf.painless.adapter.MonthlyBeckAnxietyInventoryRecyclerViewAdapter;
import com.ucsf.painless.model.monthlyBeckAnxiety.MonthlyBeckAnxietyInventoryQuestionResponse;
import com.ucsf.painless.model.monthlyRandom.MonthlyRandomResponse;
import com.ucsf.painless.model.saveMonthlyReport.SaveMonthlyResponse;
import com.ucsf.painless.viewModel.MonthlyRandQuesViewModel;
import com.ucsf.painless.viewModel.SaveMonthlyViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonthlyBeckAnxietyInventoryActivity extends AppCompatActivity {
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
    MonthlyBeckAnxietyInventoryQuestionResponse monthlyBeckAnxietyInventoryQuestionResponse;

    String reportId;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_beck);
        ButterKnife.bind(this);
        monthlyRandQuesViewModel = ViewModelProviders.of(this).get(MonthlyRandQuesViewModel.class);
        saveMonthlyViewModel = ViewModelProviders.of(this).get(SaveMonthlyViewModel.class);
        packageRecyclerView = (RecyclerView) findViewById(R.id.package_lst);
        reportId = getIntent().getStringExtra("reportId");

        monthlyRandQuesViewModel.getMonthlyBeckAnxietyInventoryQuestion(MonthlyBeckAnxietyInventoryActivity.this).observe(this, new Observer<MonthlyBeckAnxietyInventoryQuestionResponse>() {
            @Override
            public void onChanged(@Nullable MonthlyBeckAnxietyInventoryQuestionResponse randomResponse) {
                try {
                    if (randomResponse.isStatus()) {
                        txtDecs.setText(randomResponse.getData().getMonthlyBeckAnxietyInventoryQuestionData().get(0).getBaiQuestionDescription());
                        monthlyBeckAnxietyInventoryQuestionResponse = randomResponse;
                        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(MonthlyBeckAnxietyInventoryActivity.this);
                        packageRecyclerView.setLayoutManager(recyclerLayoutManager);

                        DividerItemDecoration dividerItemDecoration =
                                new DividerItemDecoration(packageRecyclerView.getContext(),
                                        recyclerLayoutManager.getOrientation());
                        packageRecyclerView.addItemDecoration(dividerItemDecoration);

                        MonthlyBeckAnxietyInventoryRecyclerViewAdapter recyclerViewAdapter = new
                                MonthlyBeckAnxietyInventoryRecyclerViewAdapter(monthlyBeckAnxietyInventoryQuestionResponse, MonthlyBeckAnxietyInventoryActivity.this);
                        packageRecyclerView.setAdapter(recyclerViewAdapter);


                    } else {
                        Toast.makeText(MonthlyBeckAnxietyInventoryActivity.this, "" + randomResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
        for (int i = 0; i < monthlyBeckAnxietyInventoryQuestionResponse.getData().getMonthlyBeckAnxietyInventoryQuestionData().size(); i++) {
            if (monthlyBeckAnxietyInventoryQuestionResponse.getData().getMonthlyBeckAnxietyInventoryQuestionData().get(i).getRadioButtonSelected() != null) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("pambai_bai_id", monthlyBeckAnxietyInventoryQuestionResponse.getData().getMonthlyBeckAnxietyInventoryQuestionData().get(i).getBaiQuestionNumber());
                    jsonObject.put("pambai_answer", monthlyBeckAnxietyInventoryQuestionResponse.getData().getMonthlyBeckAnxietyInventoryQuestionData().get(i).getRadioButtonSelected());
                    //Toast.makeText(this, "" + monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getMrqueQuestionNumber() + "::" + monthlyRandomResponse.getData().getMonthlyRand36QuestionData().get(i).getRadioButtonSelected(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
            }
        }

        if (monthlyBeckAnxietyInventoryQuestionResponse.getData().getMonthlyBeckAnxietyInventoryQuestionData().size() == jsonArray.length()) {
            saveMonthlyViewModel.saveMonthlyBeckAnxiety(jsonArray.toString(), reportId, MonthlyBeckAnxietyInventoryActivity.this).observe(this, new Observer<SaveMonthlyResponse>() {
                @Override
                public void onChanged(@Nullable SaveMonthlyResponse saveMonthlyResponse) {
                    try {
                        if (saveMonthlyResponse.isStatus()) {
                       /* myDialog = new Dialog(MonthlyBeckAnxietyInventoryActivity.this);
                        ShowPopupForSaved();*/
                            Intent n = new Intent(MonthlyBeckAnxietyInventoryActivity.this, CongratulationsScreenActivity.class);
                            n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            n.putExtra("msg", "You just completed a weekly report");
                            startActivity(n);

                        } else {
                            Toast.makeText(MonthlyBeckAnxietyInventoryActivity.this, "" + saveMonthlyResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onBackPressViewClicked() {
        finish();
    }

    public void ShowPopupForSaved() {

        Button next;
        myDialog.setContentView(R.layout.dialog_intensity_saved);
        next = (Button) myDialog.findViewById(R.id.btn_next);
        TextView txt_heading = (TextView) myDialog.findViewById(R.id.txt_heading);
        TextView txt_main_content = (TextView) myDialog.findViewById(R.id.txt_main_content);
        TextView txt_date = (TextView) myDialog.findViewById(R.id.txt_date);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        txt_heading.setText("Monthly Report");
        txt_main_content.setText("SAVED");


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy- hh:mm a", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        txt_date.setText(currentDateandTime);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(MonthlyBeckAnxietyInventoryActivity.this, CongratulationsScreenActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                n.putExtra("msg", "You just completed a weekly report");
                startActivity(n);
            }
        });

        myDialog.show();
    }
}


