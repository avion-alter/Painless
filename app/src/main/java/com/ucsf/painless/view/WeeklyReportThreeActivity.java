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
import com.ucsf.painless.model.weeklyThree.WeeklyThreeResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.viewModel.WeeklyThreeViewModel;
import com.ucsf.painless.viewModel.WeeklyTwoViewModel;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeeklyReportThreeActivity extends AppCompatActivity {
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

    Integer selectedIdThirteen;
    WeeklySecondSelectionAdapter adapterThirteen;

    WeeklyThreeViewModel weeklyThreeViewModel;
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

    @BindView(R.id.txt_nine)
    TextView txtNine;
    @BindView(R.id.recycler_view_nine)
    RecyclerView recyclerViewNine;

    @BindView(R.id.txt_ten)
    TextView txtTen;
    @BindView(R.id.recycler_view_ten)
    RecyclerView recyclerViewTen;

    @BindView(R.id.txt_eleven)
    TextView txtEleven;
    @BindView(R.id.recycler_view_eleven)
    RecyclerView recyclerViewEleven;

    @BindView(R.id.txt_twelve)
    TextView txtTwelve;
    @BindView(R.id.recycler_view_twelve)
    RecyclerView recyclerViewTwelve;

    @BindView(R.id.txt_thirteen)
    TextView txtThirteen;
    @BindView(R.id.recycler_view_thirteen)
    RecyclerView recyclerViewThirteen;

    String report_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report_three);
        ButterKnife.bind(this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        report_id = getIntent().getStringExtra("report_id");
        weeklyThreeViewModel = ViewModelProviders.of(this).get(WeeklyThreeViewModel.class);

        namesList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            namesList.add(String.valueOf(i));
        }
        //FirstList
        selectedIdOne = namesList.size() - 1;

        Collections.reverse(namesList);
        adapterOne = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewOne.setHasFixedSize(true);
        recyclerViewOne.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewOne.setAdapter(adapterOne);

        adapterTwo = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewTwo.setHasFixedSize(true);
        recyclerViewTwo.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewTwo.setAdapter(adapterTwo);

        adapterThree = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewThree.setHasFixedSize(true);
        recyclerViewThree.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewThree.setAdapter(adapterThree);

        adapterFour = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewFour.setHasFixedSize(true);
        recyclerViewFour.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewFour.setAdapter(adapterFour);

        adapterFive = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewFive.setHasFixedSize(true);
        recyclerViewFive.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewFive.setAdapter(adapterFive);

        adapterSix = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewSix.setHasFixedSize(true);
        recyclerViewSix.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewSix.setAdapter(adapterSix);

        adapterSeven = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewSeven.setHasFixedSize(true);
        recyclerViewSeven.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewSeven.setAdapter(adapterSeven);

        adapterEight = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewEight.setHasFixedSize(true);
        recyclerViewEight.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewEight.setAdapter(adapterEight);

        adapterNine = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewNine.setHasFixedSize(true);
        recyclerViewNine.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewNine.setAdapter(adapterNine);

        adapterTen = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewTen.setHasFixedSize(true);
        recyclerViewTen.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewTen.setAdapter(adapterTen);

        adapterEleven = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewEleven.setHasFixedSize(true);
        recyclerViewEleven.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewEleven.setAdapter(adapterEleven);

        adapterTwelve = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewTwelve.setHasFixedSize(true);
        recyclerViewTwelve.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewTwelve.setAdapter(adapterTwelve);

        adapterThirteen = new WeeklySecondSelectionAdapter(namesList, selectedIdOne);
        recyclerViewThirteen.setHasFixedSize(true);
        recyclerViewThirteen.setLayoutManager(new LinearLayoutManager(WeeklyReportThreeActivity.this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewThirteen.setAdapter(adapterThirteen);


        getData();
    }


    private void getData() {
        weeklyThreeViewModel.getWeeklyThree(WeeklyReportThreeActivity.this).observe(this, new Observer<WeeklyThreeResponse>() {
            @Override
            public void onChanged(@Nullable WeeklyThreeResponse threeResponse) {
                try {
                    if (threeResponse.isStatus()) {

                        txtOne.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(0).getWpciQuestion());


                        txtTwo.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(1).getWpciQuestion());


                        txtThree.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(2).getWpciQuestion());


                        txtFour.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(3).getWpciQuestion());


                        txtFive.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(4).getWpciQuestion());


                        txtSix.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(5).getWpciQuestion());


                        txtSeven.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(6).getWpciQuestion());


                        txtEight.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(7).getWpciQuestion());


                        txtNine.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(8).getWpciQuestion());


                        txtTen.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(9).getWpciQuestion());


                        txtEleven.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(10).getWpciQuestion());


                        txtTwelve.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(11).getWpciQuestion());


                        txtThirteen.setText(threeResponse.getData().getWeeklyWeeklyPainCatastrophizingIndexQuestionData().get(12).getWpciQuestion());



                    } else {
                        Toast.makeText(WeeklyReportThreeActivity.this, "" + threeResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
                jsonObjectOne.addProperty("pawpci_pci_id", "1");
                jsonObjectOne.addProperty("pawpci_answer", adapterOne.getSelectedId());
                jsonArray.add(jsonObjectOne);

                jsonObjectSecond = new JsonObject();
                jsonObjectSecond.addProperty("pawpci_pci_id", "2");
                jsonObjectSecond.addProperty("pawpci_answer", adapterTwo.getSelectedId());
                jsonArray.add(jsonObjectSecond);

                jsonObjectThree = new JsonObject();
                jsonObjectThree.addProperty("pawpci_pci_id", "3");
                jsonObjectThree.addProperty("pawpci_answer", adapterThree.getSelectedId());
                jsonArray.add(jsonObjectThree);

                jsonObjectFour = new JsonObject();
                jsonObjectFour.addProperty("pawpci_pci_id", "4");
                jsonObjectFour.addProperty("pawpci_answer", adapterFour.getSelectedId());
                jsonArray.add(jsonObjectFour);

                jsonObjectFive = new JsonObject();
                jsonObjectFive.addProperty("pawpci_pci_id", "5");
                jsonObjectFive.addProperty("pawpci_answer", adapterFive.getSelectedId());
                jsonArray.add(jsonObjectFive);

                jsonObjectSix = new JsonObject();
                jsonObjectSix.addProperty("pawpci_pci_id", "6");
                jsonObjectSix.addProperty("pawpci_answer", adapterSix.getSelectedId());
                jsonArray.add(jsonObjectSix);

                jsonObjectSeven = new JsonObject();
                jsonObjectSeven.addProperty("pawpci_pci_id", "7");
                jsonObjectSeven.addProperty("pawpci_answer",adapterSeven.getSelectedId());
                jsonArray.add(jsonObjectSeven);

                jsonObjectEight = new JsonObject();
                jsonObjectEight.addProperty("pawpci_pci_id", "8");
                jsonObjectEight.addProperty("pawpci_answer",adapterEight.getSelectedId());
                jsonArray.add(jsonObjectEight);


                jsonObjectNine = new JsonObject();
                jsonObjectNine.addProperty("pawpci_pci_id", "9");
                jsonObjectNine.addProperty("pawpci_answer",adapterNine.getSelectedId());
                jsonArray.add(jsonObjectNine);

                jsonObjectTen = new JsonObject();
                jsonObjectTen.addProperty("pawpci_pci_id", "10");
                jsonObjectTen.addProperty("pawpci_answer",adapterTen.getSelectedId());
                jsonArray.add(jsonObjectTen);

                jsonObjectEleven = new JsonObject();
                jsonObjectEleven.addProperty("pawpci_pci_id", "11");
                jsonObjectEleven.addProperty("pawpci_answer",adapterEleven.getSelectedId());
                jsonArray.add(jsonObjectEleven);

                jsonObjectTwelve = new JsonObject();
                jsonObjectTwelve.addProperty("pawpci_pci_id", "12");
                jsonObjectTwelve.addProperty("pawpci_answer",adapterTwelve.getSelectedId());
                jsonArray.add(jsonObjectTwelve);

                jsonObjectThirteen = new JsonObject();
                jsonObjectThirteen.addProperty("pawpci_pci_id", "13");
                jsonObjectThirteen.addProperty("pawpci_answer",adapterThirteen.getSelectedId());
                jsonArray.add(jsonObjectThirteen);


                weeklyThreeViewModel.saveWeeklyThree(WeeklyReportThreeActivity.this,jsonArray.toString(),report_id).observe(this, new Observer<SaveWeeklyResponse>() {
                    @Override
                    public void onChanged(@androidx.annotation.Nullable SaveWeeklyResponse saveWeeklyResponse) {
                        try {
                            if (saveWeeklyResponse.isStatus()) {
                                //Toast.makeText(this, "" + adapterOne.getSelectedId(), Toast.LENGTH_SHORT).show();
                                Intent n = new Intent(WeeklyReportThreeActivity.this, WeeklyReportFourActivity.class);
                                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                n.putExtra("report_id",report_id);
                                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(WeeklyReportThreeActivity.this).toBundle());
                            } else {
                                Toast.makeText(WeeklyReportThreeActivity.this, "" + saveWeeklyResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
