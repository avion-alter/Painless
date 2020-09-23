package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.R;
import com.ucsf.painless.adapter.QuestionnaireAdapter;
import com.ucsf.painless.model.questionnaire.McGillQuestionnaireResponse;
import com.ucsf.painless.model.questionnaire.TypesPainDataItem;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.utils.ValidationUtils;
import com.ucsf.painless.viewModel.McgillPainQuestionnaireViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class McgillPainQuestionnaireActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.img_info)
    ImageView imgInfo;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.seekbar_show)
    SeekBar seekbarShow;
    private Context mContext;

    private SessionManager sessionManager;

    McgillPainQuestionnaireViewModel mcgillPainQuestionnaireViewModel;
    private ValidationUtils validationUtils;

    List<TypesPainDataItem> typesPainDataItems;
    String intensity_id;

    @Override
    protected void onDestroy() {
        mContext = null;
        sessionManager = null;
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcgill_pain_questionnaire);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(McgillPainQuestionnaireActivity.this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        mContext = McgillPainQuestionnaireActivity.this;
        seekbarShow.setEnabled(false);
        validationUtils = new ValidationUtils();
        mcgillPainQuestionnaireViewModel = ViewModelProviders.of(this).get(McgillPainQuestionnaireViewModel.class);
        typesPainDataItems = new ArrayList<>();
        intensity_id = getIntent().getStringExtra("intensity_id");
        mcgillPainQuestionnaireViewModel.getQuestionnaire(McgillPainQuestionnaireActivity.this).observe(this, new Observer<McGillQuestionnaireResponse>() {
            @Override
            public void onChanged(@Nullable McGillQuestionnaireResponse response) {
                try {
                    if (response.isStatus()) {
                        typesPainDataItems = response.getData().getTypesPainData();
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        QuestionnaireAdapter adapter = new QuestionnaireAdapter(typesPainDataItems);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new GridLayoutManager(McgillPainQuestionnaireActivity.this, 3));
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(McgillPainQuestionnaireActivity.this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
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
                Intent n = new Intent(McgillPainQuestionnaireActivity.this, McGillInfoActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(McgillPainQuestionnaireActivity.this).toBundle());
                break;
            case R.id.btn_next:


                List<TypesPainDataItem> typesPainData = new ArrayList<>();
                for (int i = 0; i < typesPainDataItems.size(); i++) {
                    if (typesPainDataItems.get(i).isSelected()) {
                        typesPainData.add(typesPainDataItems.get(i));
                    }
                }
                if (typesPainData.size()>0){
                Bundle args = new Bundle();
                Intent intent = new Intent(McgillPainQuestionnaireActivity.this, SaveMcGillQuestionnaireActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                args.putSerializable("typesPainDataItems", (Serializable) typesPainData);
                intent.putExtra("BUNDLE", args);
                intent.putExtra("intensity_id", intensity_id);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(McgillPainQuestionnaireActivity.this).toBundle());
                }else {
                    Toast.makeText(mContext, "Please select at least one pain", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @OnClick(R.id.card_back)
    public void onViewClicked() {
        finish();
    }
}
