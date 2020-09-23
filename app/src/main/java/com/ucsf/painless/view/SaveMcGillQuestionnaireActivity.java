package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.ucsf.painless.R;
import com.ucsf.painless.adapter.QuestionnaireAdapter;
import com.ucsf.painless.adapter.SwipeAdapter;
import com.ucsf.painless.model.questionnaire.McGillQuestionnaireResponse;
import com.ucsf.painless.model.questionnaire.TypesPainDataItem;
import com.ucsf.painless.model.saveQuestionnaire.SaveMcGillPainQuesionnaireResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.viewModel.McgillPainQuestionnaireViewModel;
import com.ucsf.painless.viewModel.SaveMcGillPainQuestionnaireViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaveMcGillQuestionnaireActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.img_info)
    ImageView imgInfo;
    @BindView(R.id.btn_next)
    Button btnNext;
    List<TypesPainDataItem> typesPainDataItems;
    SwipeAdapter adapter;
    SaveMcGillPainQuestionnaireViewModel questionnaireViewModel;
    String intensity_id;
    List<TypesPainDataItem> object;

    Dialog myDialog;
    @BindView(R.id.seekbar_show)
    SeekBar seekbarShow;
    @BindView(R.id.card_back)
    CardView cardBack;
    McgillPainQuestionnaireViewModel mcgillPainQuestionnaireViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_questionnaire);
        ButterKnife.bind(this);
        seekbarShow.setEnabled(false);
        sessionManager = new SessionManager(getApplication());
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        questionnaireViewModel = ViewModelProviders.of(this).get(SaveMcGillPainQuestionnaireViewModel.class);
        mcgillPainQuestionnaireViewModel = ViewModelProviders.of(this).get(McgillPainQuestionnaireViewModel.class);
        /*Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        object = (List<TypesPainDataItem>) args.getSerializable("typesPainDataItems");
        Log.e("typesPainDataItems", object.toString());*/
        intensity_id = getIntent().getStringExtra("intensity_id");
        object = new ArrayList<>();


        mcgillPainQuestionnaireViewModel.getQuestionnaire(SaveMcGillQuestionnaireActivity.this).observe(this, new Observer<McGillQuestionnaireResponse>() {
            @Override
            public void onChanged(@Nullable McGillQuestionnaireResponse response) {
                try {
                    if (response.isStatus()) {
                        typesPainDataItems = response.getData().getTypesPainData();
                        object = response.getData().getTypesPainData();
                       /* RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        QuestionnaireAdapter adapter = new QuestionnaireAdapter(typesPainDataItems);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new GridLayoutManager(SaveMcGillQuestionnaireActivity.this, 3));
                        recyclerView.setAdapter(adapter);*/
                        recyclerView.setLayoutManager(new LinearLayoutManager(SaveMcGillQuestionnaireActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(SaveMcGillQuestionnaireActivity.this, LinearLayoutManager.VERTICAL));
                        adapter = new SwipeAdapter(SaveMcGillQuestionnaireActivity.this, typesPainDataItems);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(SaveMcGillQuestionnaireActivity.this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });


    }

    @OnClick({R.id.img_info, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_info:
                Intent n = new Intent(SaveMcGillQuestionnaireActivity.this, McGillInfoActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(SaveMcGillQuestionnaireActivity.this).toBundle());
                break;
            case R.id.btn_next:

                validation();


                break;
        }
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
                myDialog.dismiss();
                /*Intent n = new Intent(SaveMcGillQuestionnaireActivity.this, BodyMapIntroActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                n.putExtra("intensity_id", intensity_id);
                startActivity(n);*/
                Intent n = new Intent(SaveMcGillQuestionnaireActivity.this, BodyMapWebViewActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                n.putExtra("intensity_id", intensity_id);
                startActivity(n);
            }
        });

        myDialog.show();
    }

    @OnClick(R.id.card_back)
    public void onViewClicked() {
        finish();
    }

    private void validation() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        boolean isAllOk = true;

                        JsonArray jsonArray = new JsonArray();
                        if (isAllOk) {
                            for (int i = 0; i < object.size(); i++) {
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("typn_id", object.get(i).getTypnId());
                                    if (object.get(i).getSeverity() != null) {
                                        jsonObject.put("severity_value", object.get(i).getSeverity());
                                    } else {
                                        isAllOk = false;
                                    }
                                } catch (JSONException e) {
                                    isAllOk = false;
                                    e.printStackTrace();
                                }

                                jsonArray.add(String.valueOf(jsonObject));
                            }


                        } else {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("typn_id", "9999");
                                jsonObject.put("severity_value", "9");
                            } catch (JSONException e) {
                                isAllOk = false;
                                e.printStackTrace();
                            }
                            jsonArray.add(String.valueOf(jsonObject));
                        }


                        questionnaireViewModel.saveMcGillPainQuesionnaire(intensity_id, String.valueOf(jsonArray), SaveMcGillQuestionnaireActivity.this).observe(SaveMcGillQuestionnaireActivity.this, new Observer<SaveMcGillPainQuesionnaireResponse>() {
                            @Override
                            public void onChanged(@Nullable SaveMcGillPainQuesionnaireResponse response) {
                                try {
                                    if (response.isStatus()) {
                                        myDialog = new Dialog(SaveMcGillQuestionnaireActivity.this);
                                        //Toast.makeText(SaveMcGillQuestionnaireActivity.this, ""+response.getMessage(), Toast.LENGTH_SHORT).show();
                                        ShowPopup();
                                    } else {
                                        Toast.makeText(SaveMcGillQuestionnaireActivity.this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {

                                    e.printStackTrace();
                                }
                            }
                        });

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(SaveMcGillQuestionnaireActivity.this);
        builder.setMessage("Are you currently experiencing none of the pain described here?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}
