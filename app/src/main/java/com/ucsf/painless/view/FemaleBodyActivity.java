package com.ucsf.painless.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.model.bodyMap.BodyMapResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.viewModel.BodyMapViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FemaleBodyActivity extends AppCompatActivity {

    SessionManager sessionManager;
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.card_save)
    CardView cardSave;
    @BindView(R.id.txt_heading)
    TextView txtHeading;
    @BindView(R.id.img_flip)
    ImageView imgFlip;
    @BindView(R.id.linear_head)
    LinearLayout linearHead;
    @BindView(R.id.linear_face)
    LinearLayout linearFace;
    @BindView(R.id.linear_chest)
    LinearLayout linearChest;
    @BindView(R.id.linear_sholder)
    LinearLayout linearSholder;
    @BindView(R.id.linear_arm)
    LinearLayout linearArm;
    @BindView(R.id.linear_cubital)
    LinearLayout linearCubital;
    @BindView(R.id.linear_forearm)
    LinearLayout linearForearm;
    @BindView(R.id.linear_rist)
    LinearLayout linearRist;
    @BindView(R.id.linear_palm)
    LinearLayout linearPalm;
    @BindView(R.id.linear_stomach)
    LinearLayout linearStomach;
    @BindView(R.id.linear_hip)
    LinearLayout linearHip;
    @BindView(R.id.linear_pelvin)
    LinearLayout linearPelvin;
    @BindView(R.id.linear_thigh)
    LinearLayout linearThigh;
    @BindView(R.id.linear_knee)
    LinearLayout linearKnee;
    @BindView(R.id.linear_crus)
    LinearLayout linearCrus;
    @BindView(R.id.relative_female_front)
    RelativeLayout relativeFemaleFront;
    @BindView(R.id.img_undo)
    ImageView imgUndo;
    @BindView(R.id.img_severity)
    ImageView imgSeverity;
    @BindView(R.id.img_erase)
    ImageView imgErase;
    @BindView(R.id.img_clipboard)
    ImageView imgClipboard;
    @BindView(R.id.relative_frame)
    RelativeLayout relativeFrame;
    @BindView(R.id.img_right_head_female)
    ImageView imgRightHeadFemale;
    @BindView(R.id.img_left_head_female)
    ImageView imgLeftHeadFemale;
    @BindView(R.id.img_right_face_female)
    ImageView imgRightFaceFemale;
    @BindView(R.id.img_left_face_female)
    ImageView imgLeftFaceFemale;
    @BindView(R.id.img_right_neck_female)
    ImageView imgRightNeckFemale;
    @BindView(R.id.img_left_neck_female)
    ImageView imgLeftNeckFemale;
    @BindView(R.id.img_right_chest_female)
    ImageView imgRightChestFemale;
    @BindView(R.id.img_left_chest_female)
    ImageView imgLeftChestFemale;
    @BindView(R.id.img_right_shoulder_female)
    ImageView imgRightShoulderFemale;
    @BindView(R.id.img_left_shoulder_female_white)
    ImageView imgLeftShoulderFemaleWhite;
    @BindView(R.id.img_right_arm_female)
    ImageView imgRightArmFemale;
    @BindView(R.id.img_left_arm_female)
    ImageView imgLeftArmFemale;
    @BindView(R.id.img_right_cubital_female)
    ImageView imgRightCubitalFemale;
    @BindView(R.id.img_left_cubital_female)
    ImageView imgLeftCubitalFemale;
    @BindView(R.id.img_right_forearm_female)
    ImageView imgRightForearmFemale;
    @BindView(R.id.img_left_forearm_female)
    ImageView imgLeftForearmFemale;
    @BindView(R.id.img_right_wrist_female)
    ImageView imgRightWristFemale;
    @BindView(R.id.img_left_wrist_female)
    ImageView imgLeftWristFemale;
    @BindView(R.id.img_right_palm_female)
    ImageView imgRightPalmFemale;
    @BindView(R.id.img_left_palm_female)
    ImageView imgLeftPalmFemale;
    @BindView(R.id.img_right_stomach_female)
    ImageView imgRightStomachFemale;
    @BindView(R.id.img_left_stomach_female)
    ImageView imgLeftStomachFemale;
    @BindView(R.id.img_right_hip_female)
    ImageView imgRightHipFemale;
    @BindView(R.id.img_left_hip_female)
    ImageView imgLeftHipFemale;
    @BindView(R.id.img_right_pelvis_female)
    ImageView imgRightPelvisFemale;
    @BindView(R.id.img_left_pelvis_female)
    ImageView imgLeftPelvisFemale;
    @BindView(R.id.img_right_thigh_female)
    ImageView imgRightThighFemale;
    @BindView(R.id.img_left_thigh_female)
    ImageView imgLeftThighFemale;
    @BindView(R.id.img_left_knee_female)
    ImageView imgLeftKneeFemale;
    @BindView(R.id.img_right_knee_female)
    ImageView imgRightKneeFemale;
    @BindView(R.id.img_right_crus_female)
    ImageView imgRightCrusFemale;
    @BindView(R.id.img_left_crus_female)
    ImageView imgLeftCrusFemale;
    @BindView(R.id.img_right_tarsus_female)
    ImageView imgRightTarsusFemale;
    @BindView(R.id.img_left_tarsus_female)
    ImageView imgLeftTarsusFemale;
    @BindView(R.id.img_right_foot_female)
    ImageView imgRightFootFemale;
    @BindView(R.id.img_left_foot_female)
    ImageView imgLeftFootFemale;
    @BindView(R.id.img_left_head_female_back)
    ImageView imgLeftHeadFemaleBack;
    @BindView(R.id.img_right_head_female_back)
    ImageView imgRightHeadFemaleBack;
    @BindView(R.id.img_left_head_2_female_back)
    ImageView imgLeftHead2FemaleBack;
    @BindView(R.id.img_right_head_2_female_back)
    ImageView imgRightHead2FemaleBack;
    @BindView(R.id.img_left_neck_female_back)
    ImageView imgLeftNeckFemaleBack;
    @BindView(R.id.img_right_neck_female_back)
    ImageView imgRightNeckFemaleBack;
    @BindView(R.id.img_left_back_top_female_back)
    ImageView imgLeftBackTopFemaleBack;
    @BindView(R.id.img_right_back_top_female_back)
    ImageView imgRightBackTopFemaleBack;
    @BindView(R.id.img_left_back_middle_female_back)
    ImageView imgLeftBackMiddleFemaleBack;
    @BindView(R.id.img_right_back_middle_female_back)
    ImageView imgRightBackMiddleFemaleBack;
    @BindView(R.id.img_left_shoulder_female_back)
    ImageView imgLeftShoulderFemaleBack;
    @BindView(R.id.img_right_shoulder_female_back)
    ImageView imgRightShoulderFemaleBack;
    @BindView(R.id.img_left_arm_female_back)
    ImageView imgLeftArmFemaleBack;
    @BindView(R.id.img_right_arm_female_back)
    ImageView imgRightArmFemaleBack;
    @BindView(R.id.img_left_cubital_female_back)
    ImageView imgLeftCubitalFemaleBack;
    @BindView(R.id.img_right_cubital_female_back)
    ImageView imgRightCubitalFemaleBack;
    @BindView(R.id.img_left_forearm_female_back)
    ImageView imgLeftForearmFemaleBack;
    @BindView(R.id.img_right_forearm_female_back)
    ImageView imgRightForearmFemaleBack;
    @BindView(R.id.img_left_wrist_female_back)
    ImageView imgLeftWristFemaleBack;
    @BindView(R.id.img_right_wrist_female_back)
    ImageView imgRightWristFemaleBack;
    @BindView(R.id.img_left_palm_female_back)
    ImageView imgLeftPalmFemaleBack;
    @BindView(R.id.img_right_palm_female_back)
    ImageView imgRightPalmFemaleBack;
    @BindView(R.id.img_left_back_lower_female_back)
    ImageView imgLeftBackLowerFemaleBack;
    @BindView(R.id.img_right_back_lower_female_back)
    ImageView imgRightBackLowerFemaleBack;
    @BindView(R.id.img_left_hip_female_back)
    ImageView imgLeftHipFemaleBack;
    @BindView(R.id.img_right_hip_female_back)
    ImageView imgRightHipFemaleBack;
    @BindView(R.id.img_left_hip2_female_back)
    ImageView imgLeftHip2FemaleBack;
    @BindView(R.id.img_right_hip2_female_back)
    ImageView imgRightHip2FemaleBack;
    @BindView(R.id.img_left_thigh_female_back)
    ImageView imgLeftThighFemaleBack;
    @BindView(R.id.img_right_thigh_female_back)
    ImageView imgRightThighFemaleBack;
    @BindView(R.id.img_right_knee_female_back)
    ImageView imgRightKneeFemaleBack;
    @BindView(R.id.img_left_knee_female_back)
    ImageView imgLeftKneeFemaleBack;
    @BindView(R.id.img_left_crus_female_back)
    ImageView imgLeftCrusFemaleBack;
    @BindView(R.id.img_right_crus_female_back)
    ImageView imgRightCrusFemaleBack;
    @BindView(R.id.img_right_tarsus_female_back)
    ImageView imgRightTarsusFemaleBack;
    @BindView(R.id.img_left_tarsus_female_back)
    ImageView imgLeftTarsusFemaleBack;
    @BindView(R.id.img_right_foot_female_back)
    ImageView imgRightFootFemaleBack;
    @BindView(R.id.img_left_foot_female_back)
    ImageView imgLeftFootFemaleBack;


    boolean front = true;
    @BindView(R.id.relative_female_back)
    RelativeLayout relativeFemaleBack;
    Dialog myDialog;
    BodyMapViewModel bodyMapViewModel;
    String intensity_id;

    boolean doubleBackToExitPressedOnce = false;
    boolean tripalBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_body);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        intensity_id = getIntent().getStringExtra("intensity_id");
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        bodyMapViewModel = ViewModelProviders.of(this).get(BodyMapViewModel.class);
        ApplicationTest.getInstance().clipBoardData=new ArrayList<>();
        if (front) {
            relativeFemaleBack.setVisibility(View.GONE);
            relativeFemaleFront.setVisibility(View.VISIBLE);
            front = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ApplicationTest.getInstance().clipBoardData.size()!=0)
            setImgClipboard();
    }

    @OnClick({R.id.card_back, R.id.card_save, R.id.img_flip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_back:
                finish();
                break;
            case R.id.card_save:
                ArrayList<HashMap<String, String>> name = ApplicationTest.getInstance().getSelectedPartName();
                ArrayList<HashMap<String, String>> color = ApplicationTest.getInstance().getSelectedColor();
                ArrayList<HashMap<String, String>> part = ApplicationTest.getInstance().getSelectedPartName();
                ArrayList<HashMap<String, String>> frontback = ApplicationTest.getInstance().getSelectedFrontBack();
                ArrayList<HashMap<String, String>> leftright = ApplicationTest.getInstance().getSelectedRightLeft();
                JsonArray jsonArray=new JsonArray();
                JsonObject jsonObject;
                for (int i = 0; i < color.size(); i++) {
                    jsonObject=new JsonObject();
                    String colorSelected="";
                    if (color.get(i).get("name").equals("mild")){
                        colorSelected="1";
                    }else if (color.get(i).get("name").equals("severe")){
                        colorSelected="2";
                    }else if (color.get(i).get("name").equals("moderate")){
                        colorSelected="2";
                    }
                    jsonObject.addProperty("bpm_color",colorSelected);
                    jsonObject.addProperty("bpm_front_back",frontback.get(i).get("bpm_front_back"));
                    jsonObject.addProperty("bpm_right_left",leftright.get(i).get("bpm_right_left"));
                    jsonObject.addProperty("bpm_body_name",name.get(i).get("name"));
                    jsonArray.add(jsonObject);
                }

                bodyMapViewModel.saveBodyMap(intensity_id, jsonArray.toString(),FemaleBodyActivity.this).observe(this, new Observer<BodyMapResponse>() {
                    @Override
                    public void onChanged(@Nullable BodyMapResponse bodyMapResponse) {
                        try {
                            if (bodyMapResponse.isStatus()){
                                myDialog = new Dialog(FemaleBodyActivity.this);
                                ShowPopupForSaved();
                            }else {
                                Toast.makeText(FemaleBodyActivity.this, ""+bodyMapResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){

                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.img_flip:
                if (front) {
                    //replaceFragment(maleBackFragment, "Male_Front");
                    txtHeading.setText("FRONT");
                    relativeFemaleFront.setVisibility(View.VISIBLE);
                    relativeFemaleBack.setVisibility(View.GONE);
                    imgFlip.setImageResource(R.mipmap.switch_back);
                    front = false;
                } else {
                    txtHeading.setText("BACK");
                    relativeFemaleFront.setVisibility(View.GONE);
                    relativeFemaleBack.setVisibility(View.VISIBLE);
                    imgFlip.setImageResource(R.mipmap.switch_front);
                    //replaceFragment(maleFrontFragment, "Male_Front");
                    front = true;
                }
                break;
        }
    }

    @OnClick({R.id.img_undo, R.id.img_severity, R.id.img_erase, R.id.img_clipboard})
    public void onBootomViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_undo:
                if (ApplicationTest.getInstance().getSelectedSevere().size() > 0) {
                    ArrayList<HashMap<String, String>> idStringHashMap = ApplicationTest.getInstance().getSelectedSevere();
                    undo(idStringHashMap.get(idStringHashMap.size() - 1).get("id"));

                    /*for (int i = 0; i < idStringHashMap.size(); i++) {
                        HashMap<String, String> map = idStringHashMap.get(i);
                        if (map.get("id").equals("imgRightHead")) {
                            imgRightHead.setImageResource(R.drawable.left_arm_male_severe);
                        }
                    }*/
                }
                break;
            case R.id.img_severity:
                Context context;
                // myDialog = new Dialog(FemaleBodyActivity.this);
               // ShowPopup();
                break;
            case R.id.img_erase:
                erase();
                break;
            case R.id.img_clipboard:
                ApplicationTest.getInstance().clipBoardData =new ArrayList<>();
                Intent n = new Intent(FemaleBodyActivity.this, ClipBoardActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(n);
                break;
        }
    }

    public void ShowPopup() {
        Button btn_mild;
        Button btn_moderate;
        Button btn_severe;
        myDialog.setContentView(R.layout.dialog_select_sevarity);
        btn_mild = (Button) myDialog.findViewById(R.id.btn_mild);
        btn_moderate = (Button) myDialog.findViewById(R.id.btn_moderate);
        btn_severe = (Button) myDialog.findViewById(R.id.btn_severe);
        TextView txt_heading = (TextView) myDialog.findViewById(R.id.txt_heading);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.setCancelable(true);
        btn_mild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgSeverity.setImageResource(R.mipmap.paint_bucket_mild);
                ApplicationTest.getInstance().setSelectedSeverity("mild");
                myDialog.dismiss();
            }
        });
        btn_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgSeverity.setImageResource(R.mipmap.paint_bucket_moderate);
                ApplicationTest.getInstance().setSelectedSeverity("moderate");
                myDialog.dismiss();
            }
        });
        btn_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgSeverity.setImageResource(R.mipmap.paint_bucket_severe);
                ApplicationTest.getInstance().setSelectedSeverity("severe");
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }


    @OnClick({R.id.img_right_head_female, R.id.img_left_head_female, R.id.img_right_face_female, R.id.img_left_face_female, R.id.img_right_neck_female, R.id.img_left_neck_female, R.id.img_right_chest_female, R.id.img_left_chest_female, R.id.img_right_shoulder_female, R.id.img_left_shoulder_female_white, R.id.img_right_arm_female, R.id.img_left_arm_female, R.id.img_right_cubital_female, R.id.img_left_cubital_female, R.id.img_right_forearm_female, R.id.img_left_forearm_female, R.id.img_right_wrist_female, R.id.img_left_wrist_female, R.id.img_right_palm_female, R.id.img_left_palm_female, R.id.img_right_stomach_female, R.id.img_left_stomach_female, R.id.img_right_hip_female, R.id.img_left_hip_female, R.id.img_right_pelvis_female, R.id.img_left_pelvis_female, R.id.img_right_thigh_female, R.id.img_left_thigh_female, R.id.img_left_knee_female, R.id.img_right_knee_female, R.id.img_right_crus_female, R.id.img_left_crus_female, R.id.img_right_tarsus_female, R.id.img_left_tarsus_female, R.id.img_right_foot_female, R.id.img_left_foot_female})
    public void onFrontViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_right_head_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHeadFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_head_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHeadFemale.setImageResource(R.drawable.right_head_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHeadFemale.setImageResource(R.drawable.right_head_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHeadFemale.setImageResource(R.drawable.right_head_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_head_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHeadFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_head_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHeadFemale.setImageResource(R.drawable.left_head_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHeadFemale.setImageResource(R.drawable.left_head_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHeadFemale.setImageResource(R.drawable.left_head_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_face_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFaceFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_face_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightFaceFemale.setImageResource(R.drawable.right_face_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightFaceFemale.setImageResource(R.drawable.right_face_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightFaceFemale.setImageResource(R.drawable.right_face_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_face_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFaceFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_face_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftFaceFemale.setImageResource(R.drawable.left_face_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftFaceFemale.setImageResource(R.drawable.left_face_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftFaceFemale.setImageResource(R.drawable.left_face_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_neck_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeckFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_neck_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightNeckFemale.setImageResource(R.drawable.right_neck_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightNeckFemale.setImageResource(R.drawable.right_neck_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightNeckFemale.setImageResource(R.drawable.right_neck_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_neck_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeckFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_neck_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftNeckFemale.setImageResource(R.drawable.left_neck_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftNeckFemale.setImageResource(R.drawable.left_neck_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftNeckFemale.setImageResource(R.drawable.left_neck_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }

                break;
            case R.id.img_right_chest_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightChestFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_chest_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightChestFemale.setImageResource(R.drawable.right_chest_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightChestFemale.setImageResource(R.drawable.right_chest_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightChestFemale.setImageResource(R.drawable.right_chest_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_chest_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftChestFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_chest_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftChestFemale.setImageResource(R.drawable.left_chest_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftChestFemale.setImageResource(R.drawable.left_chest_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftChestFemale.setImageResource(R.drawable.left_chest_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_shoulder_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulderFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_shoulder_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightShoulderFemale.setImageResource(R.drawable.right_shoulder_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightShoulderFemale.setImageResource(R.drawable.right_shoulder_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightShoulderFemale.setImageResource(R.drawable.right_shoulder_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_shoulder_female_white:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulderFemaleWhite");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_shoulder_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftShoulderFemaleWhite.setImageResource(R.drawable.left_shoulder_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftShoulderFemaleWhite.setImageResource(R.drawable.left_shoulder_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftShoulderFemaleWhite.setImageResource(R.drawable.left_shoulder_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_arm_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_arm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightArmFemale.setImageResource(R.drawable.right_arm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightArmFemale.setImageResource(R.drawable.right_arm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightArmFemale.setImageResource(R.drawable.right_arm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_arm_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_arm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftArmFemale.setImageResource(R.drawable.left_arm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftArmFemale.setImageResource(R.drawable.left_arm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftArmFemale.setImageResource(R.drawable.left_arm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_cubital_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubitalFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_cubital_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCubitalFemale.setImageResource(R.drawable.right_cubital_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCubitalFemale.setImageResource(R.drawable.right_cubital_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCubitalFemale.setImageResource(R.drawable.right_cubital_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_cubital_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubitalFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_cubital_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCubitalFemale.setImageResource(R.drawable.left_cubital_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCubitalFemale.setImageResource(R.drawable.left_cubital_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCubitalFemale.setImageResource(R.drawable.left_cubital_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_forearm_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_forearm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightForearmFemale.setImageResource(R.drawable.right_forearm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightForearmFemale.setImageResource(R.drawable.right_forearm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightForearmFemale.setImageResource(R.drawable.right_forearm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_forearm_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_forearm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftForearmFemale.setImageResource(R.drawable.left_forearm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftForearmFemale.setImageResource(R.drawable.left_forearm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftForearmFemale.setImageResource(R.drawable.left_forearm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_wrist_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWristFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_wrist_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightWristFemale.setImageResource(R.drawable.right_wrist_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightWristFemale.setImageResource(R.drawable.right_wrist_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightWristFemale.setImageResource(R.drawable.right_wrist_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_wrist_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWristFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_wrist_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftWristFemale.setImageResource(R.drawable.left_wrist_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftWristFemale.setImageResource(R.drawable.left_wrist_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftWristFemale.setImageResource(R.drawable.left_wrist_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_palm_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_palm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightPalmFemale.setImageResource(R.drawable.right_palm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightPalmFemale.setImageResource(R.drawable.right_palm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightPalmFemale.setImageResource(R.drawable.right_palm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_palm_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_palm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftPalmFemale.setImageResource(R.drawable.left_palm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftPalmFemale.setImageResource(R.drawable.left_palm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftPalmFemale.setImageResource(R.drawable.left_palm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_stomach_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightStomachFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_stomach_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightStomachFemale.setImageResource(R.drawable.right_stomach_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightStomachFemale.setImageResource(R.drawable.right_stomach_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightStomachFemale.setImageResource(R.drawable.right_stomach_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_stomach_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftStomachFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_stomach_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftStomachFemale.setImageResource(R.drawable.left_stomach_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftStomachFemale.setImageResource(R.drawable.left_stomach_female);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftStomachFemale.setImageResource(R.drawable.left_stomach_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_hip_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHipFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHipFemale.setImageResource(R.drawable.right_hip_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHipFemale.setImageResource(R.drawable.right_hip_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHipFemale.setImageResource(R.drawable.right_hip_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_hip_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHipFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHipFemale.setImageResource(R.drawable.left_hip_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHipFemale.setImageResource(R.drawable.left_hip_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHipFemale.setImageResource(R.drawable.left_hip_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_pelvis_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPelvisFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_pelvis_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightPelvisFemale.setImageResource(R.drawable.right_pelvis_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightPelvisFemale.setImageResource(R.drawable.right_pelvis_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightPelvisFemale.setImageResource(R.drawable.right_pelvis_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_pelvis_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPelvisFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_pelvis_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftPelvisFemale.setImageResource(R.drawable.left_pelvis_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftPelvisFemale.setImageResource(R.drawable.left_pelvis_female);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftPelvisFemale.setImageResource(R.drawable.left_pelvis_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_thigh_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThighFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_thigh_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightThighFemale.setImageResource(R.drawable.right_thigh_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightThighFemale.setImageResource(R.drawable.right_thigh_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightThighFemale.setImageResource(R.drawable.right_thigh_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_thigh_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThighFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_thigh_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftThighFemale.setImageResource(R.drawable.left_thigh_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftThighFemale.setImageResource(R.drawable.left_thigh_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftThighFemale.setImageResource(R.drawable.left_thigh_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_knee_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKneeFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_knee_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftKneeFemale.setImageResource(R.drawable.left_knee_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftKneeFemale.setImageResource(R.drawable.left_knee_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftKneeFemale.setImageResource(R.drawable.left_knee_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_knee_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKneeFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_knee_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightKneeFemale.setImageResource(R.drawable.right_knee_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightKneeFemale.setImageResource(R.drawable.right_knee_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightKneeFemale.setImageResource(R.drawable.right_knee_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_crus_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrusFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_crus_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCrusFemale.setImageResource(R.drawable.right_crus_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCrusFemale.setImageResource(R.drawable.right_crus_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCrusFemale.setImageResource(R.drawable.right_crus_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_crus_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrusFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_crus_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCrusFemale.setImageResource(R.drawable.left_crus_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCrusFemale.setImageResource(R.drawable.left_crus_female);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCrusFemale.setImageResource(R.drawable.left_crus_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_tarsus_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsusFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_tarsus_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightTarsusFemale.setImageResource(R.drawable.right_tarsus_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightTarsusFemale.setImageResource(R.drawable.right_tarsus_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightTarsusFemale.setImageResource(R.drawable.right_tarsus_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_tarsus_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsusFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_tarsus_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftTarsusFemale.setImageResource(R.drawable.left_tarsus_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftTarsusFemale.setImageResource(R.drawable.left_tarsus_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftTarsusFemale.setImageResource(R.drawable.left_tarsus_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_foot_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFootFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_foot_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightFootFemale.setImageResource(R.drawable.right_foot_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightFootFemale.setImageResource(R.drawable.right_foot_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightFootFemale.setImageResource(R.drawable.right_foot_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_foot_female:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFootFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_foot_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftFootFemale.setImageResource(R.drawable.left_foot_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftFootFemale.setImageResource(R.drawable.left_foot_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftFootFemale.setImageResource(R.drawable.left_foot_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
        }
    }

    @OnClick({R.id.img_left_head_female_back, R.id.img_right_head_female_back, R.id.img_left_head_2_female_back, R.id.img_right_head_2_female_back, R.id.img_left_neck_female_back, R.id.img_right_neck_female_back, R.id.img_left_back_top_female_back, R.id.img_right_back_top_female_back, R.id.img_left_back_middle_female_back, R.id.img_right_back_middle_female_back, R.id.img_left_shoulder_female_back, R.id.img_right_shoulder_female_back, R.id.img_left_arm_female_back, R.id.img_right_arm_female_back, R.id.img_left_cubital_female_back, R.id.img_right_cubital_female_back, R.id.img_left_forearm_female_back, R.id.img_right_forearm_female_back, R.id.img_left_wrist_female_back, R.id.img_right_wrist_female_back, R.id.img_left_palm_female_back, R.id.img_right_palm_female_back, R.id.img_left_back_lower_female_back, R.id.img_right_back_lower_female_back, R.id.img_left_hip_female_back, R.id.img_right_hip_female_back, R.id.img_left_hip2_female_back, R.id.img_right_hip2_female_back, R.id.img_left_thigh_female_back, R.id.img_right_thigh_female_back, R.id.img_right_knee_female_back, R.id.img_left_knee_female_back, R.id.img_left_crus_female_back, R.id.img_right_crus_female_back, R.id.img_right_tarsus_female_back, R.id.img_left_tarsus_female_back, R.id.img_right_foot_female_back, R.id.img_left_foot_female_back})
    public void onFemaleBackViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left_head_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHeadFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_head_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHeadFemaleBack.setImageResource(R.drawable.left_head_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHeadFemaleBack.setImageResource(R.drawable.left_head_female_back);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHeadFemaleBack.setImageResource(R.drawable.left_head_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_head_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHeadFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_head_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHeadFemaleBack.setImageResource(R.drawable.right_head_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHeadFemaleBack.setImageResource(R.drawable.right_head_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHeadFemaleBack.setImageResource(R.drawable.right_head_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_head_2_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHead2FemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_face_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHead2FemaleBack.setImageResource(R.drawable.left_head_2_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHead2FemaleBack.setImageResource(R.drawable.left_head_2_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHead2FemaleBack.setImageResource(R.drawable.left_head_2_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_head_2_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHead2FemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_face_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHead2FemaleBack.setImageResource(R.drawable.right_head_2_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHead2FemaleBack.setImageResource(R.drawable.right_head_2_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHead2FemaleBack.setImageResource(R.drawable.right_head_2_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_neck_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeckFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_neck_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftNeckFemaleBack.setImageResource(R.drawable.left_neck_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftNeckFemaleBack.setImageResource(R.drawable.left_neck_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftNeckFemaleBack.setImageResource(R.drawable.left_neck_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_neck_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeckFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_neck_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightNeckFemaleBack.setImageResource(R.drawable.right_neck_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightNeckFemaleBack.setImageResource(R.drawable.right_neck_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightNeckFemaleBack.setImageResource(R.drawable.right_neck_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_back_top_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackTopFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_top_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftBackTopFemaleBack.setImageResource(R.drawable.left_back_top_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftBackTopFemaleBack.setImageResource(R.drawable.left_back_top_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftBackTopFemaleBack.setImageResource(R.drawable.left_back_top_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_back_top_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackTopFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_top_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightBackTopFemaleBack.setImageResource(R.drawable.right_back_top_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightBackTopFemaleBack.setImageResource(R.drawable.right_back_top_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightBackTopFemaleBack.setImageResource(R.drawable.right_back_top_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_back_middle_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackMiddleFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_middle_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftBackMiddleFemaleBack.setImageResource(R.drawable.left_back_middle_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftBackMiddleFemaleBack.setImageResource(R.drawable.left_back_middle_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftBackMiddleFemaleBack.setImageResource(R.drawable.left_back_middle_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_back_middle_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackMiddleFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_middle_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightBackMiddleFemaleBack.setImageResource(R.drawable.right_back_middle_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightBackMiddleFemaleBack.setImageResource(R.drawable.right_back_middle_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightBackMiddleFemaleBack.setImageResource(R.drawable.right_back_middle_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_shoulder_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulderFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_shoulder_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftShoulderFemaleBack.setImageResource(R.drawable.left_shoulder_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftShoulderFemaleBack.setImageResource(R.drawable.left_shoulder_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftShoulderFemaleBack.setImageResource(R.drawable.left_shoulder_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_shoulder_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulderFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_shoulder_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightShoulderFemaleBack.setImageResource(R.drawable.right_shoulder_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightShoulderFemaleBack.setImageResource(R.drawable.right_shoulder_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightShoulderFemaleBack.setImageResource(R.drawable.right_shoulder_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_arm_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_arm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftArmFemaleBack.setImageResource(R.drawable.left_arm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftArmFemaleBack.setImageResource(R.drawable.left_arm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftArmFemaleBack.setImageResource(R.drawable.left_arm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_arm_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_arm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightArmFemaleBack.setImageResource(R.drawable.right_arm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightArmFemaleBack.setImageResource(R.drawable.right_arm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightArmFemaleBack.setImageResource(R.drawable.right_arm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }

                break;
            case R.id.img_left_cubital_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubitalFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_cubital_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCubitalFemaleBack.setImageResource(R.drawable.left_cubital_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCubitalFemaleBack.setImageResource(R.drawable.left_cubital_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCubitalFemaleBack.setImageResource(R.drawable.left_cubital_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_cubital_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubitalFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_cubital_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCubitalFemaleBack.setImageResource(R.drawable.right_cubital_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCubitalFemaleBack.setImageResource(R.drawable.right_cubital_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCubitalFemaleBack.setImageResource(R.drawable.right_cubital_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_forearm_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_forearm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftForearmFemaleBack.setImageResource(R.drawable.left_forearm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftForearmFemaleBack.setImageResource(R.drawable.left_forearm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftForearmFemaleBack.setImageResource(R.drawable.left_forearm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_forearm_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_forearm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightForearmFemaleBack.setImageResource(R.drawable.right_forearm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightForearmFemaleBack.setImageResource(R.drawable.right_forearm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightForearmFemaleBack.setImageResource(R.drawable.right_forearm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_wrist_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWristFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_wrist_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftWristFemaleBack.setImageResource(R.drawable.left_wrist_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftWristFemaleBack.setImageResource(R.drawable.left_wrist_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftWristFemaleBack.setImageResource(R.drawable.left_wrist_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_wrist_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWristFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_wrist_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightWristFemaleBack.setImageResource(R.drawable.right_wrist_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightWristFemaleBack.setImageResource(R.drawable.right_wrist_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightWristFemaleBack.setImageResource(R.drawable.right_wrist_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }

                break;
            case R.id.img_left_palm_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_palm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftPalmFemaleBack.setImageResource(R.drawable.left_palm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftPalmFemaleBack.setImageResource(R.drawable.left_palm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftPalmFemaleBack.setImageResource(R.drawable.left_palm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_palm_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_palm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightPalmFemaleBack.setImageResource(R.drawable.right_palm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightPalmFemaleBack.setImageResource(R.drawable.right_palm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightPalmFemaleBack.setImageResource(R.drawable.right_palm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_back_lower_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackLowerFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_lower_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftBackLowerFemaleBack.setImageResource(R.drawable.left_back_lower_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftBackLowerFemaleBack.setImageResource(R.drawable.left_back_lower_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftBackLowerFemaleBack.setImageResource(R.drawable.left_back_lower_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_back_lower_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackLowerFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_lower_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightBackLowerFemaleBack.setImageResource(R.drawable.right_back_lower_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightBackLowerFemaleBack.setImageResource(R.drawable.right_back_lower_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightBackLowerFemaleBack.setImageResource(R.drawable.right_back_lower_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_hip_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHipFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHipFemaleBack.setImageResource(R.drawable.left_hip_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHipFemaleBack.setImageResource(R.drawable.left_hip_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHipFemaleBack.setImageResource(R.drawable.left_hip_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_hip_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHipFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHipFemaleBack.setImageResource(R.drawable.right_hip_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHipFemaleBack.setImageResource(R.drawable.right_hip_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHipFemaleBack.setImageResource(R.drawable.right_hip_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_hip2_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHip2FemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip2_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHip2FemaleBack.setImageResource(R.drawable.left_hip2_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHip2FemaleBack.setImageResource(R.drawable.left_hip2_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHip2FemaleBack.setImageResource(R.drawable.left_hip2_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_hip2_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHip2FemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip2_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHip2FemaleBack.setImageResource(R.drawable.right_hip2_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHip2FemaleBack.setImageResource(R.drawable.right_hip2_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHip2FemaleBack.setImageResource(R.drawable.right_hip2_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_thigh_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThighFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_thigh_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftThighFemaleBack.setImageResource(R.drawable.left_thigh_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftThighFemaleBack.setImageResource(R.drawable.left_thigh_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftThighFemaleBack.setImageResource(R.drawable.left_thigh_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_thigh_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThighFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_thigh_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightThighFemaleBack.setImageResource(R.drawable.right_thigh_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightThighFemaleBack.setImageResource(R.drawable.right_thigh_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightThighFemaleBack.setImageResource(R.drawable.right_thigh_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_knee_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKneeFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_knee_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightKneeFemaleBack.setImageResource(R.drawable.right_knee_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightKneeFemaleBack.setImageResource(R.drawable.right_knee_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightKneeFemaleBack.setImageResource(R.drawable.right_knee_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_knee_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKneeFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_knee_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftKneeFemaleBack.setImageResource(R.drawable.left_knee_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftKneeFemaleBack.setImageResource(R.drawable.left_knee_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftKneeFemaleBack.setImageResource(R.drawable.left_knee_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_crus_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrusFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_crus_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCrusFemaleBack.setImageResource(R.drawable.left_crus_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCrusFemaleBack.setImageResource(R.drawable.left_crus_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCrusFemaleBack.setImageResource(R.drawable.left_crus_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_crus_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrusFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_crus_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCrusFemaleBack.setImageResource(R.drawable.right_crus_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCrusFemaleBack.setImageResource(R.drawable.right_crus_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCrusFemaleBack.setImageResource(R.drawable.right_crus_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_tarsus_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsusFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_tarsus_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightTarsusFemaleBack.setImageResource(R.drawable.right_tarsus_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightTarsusFemaleBack.setImageResource(R.drawable.right_tarsus_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightTarsusFemaleBack.setImageResource(R.drawable.right_tarsus_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_tarsus_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsusFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_tarsus_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftTarsusFemaleBack.setImageResource(R.drawable.left_tarsus_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftTarsusFemaleBack.setImageResource(R.drawable.left_tarsus_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftTarsusFemaleBack.setImageResource(R.drawable.left_tarsus_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_foot_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFootFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_foot_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightFootFemaleBack.setImageResource(R.drawable.right_foot_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightFootFemaleBack.setImageResource(R.drawable.right_foot_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightFootFemaleBack.setImageResource(R.drawable.right_foot_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_foot_female_back:
                 selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFootFemaleBack");
                    ApplicationTest.getInstance().addInFrontBack("name", "left_foot_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftFootFemaleBack.setImageResource(R.drawable.left_foot_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftFootFemaleBack.setImageResource(R.drawable.left_foot_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftFootFemaleBack.setImageResource(R.drawable.left_foot_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
        }
    }


    public void undo(String id) {
        if (ApplicationTest.getInstance().getSelectedSevere().size() > 0) {
            ApplicationTest.getInstance().getSelectedSevere().remove(ApplicationTest.getInstance().getSelectedSevere().size() - 1);
            if (id.equals("imgRightHeadFemale")) {
                imgRightHeadFemale.setImageResource(R.drawable.right_head_female_white);
            }
            if (id.equals("imgLeftHeadFemale")) {
                imgLeftHeadFemale.setImageResource(R.drawable.left_head_female_white);
            }
            if (id.equals("imgRightFaceFemale")) {
                imgRightFaceFemale.setImageResource(R.drawable.right_face_female_white);
            }
            if (id.equals("imgLeftFaceFemale")) {
                imgLeftFaceFemale.setImageResource(R.drawable.left_face_female_white);
            }
            if (id.equals("imgRightNeckFemale")) {
                imgRightNeckFemale.setImageResource(R.drawable.right_neck_female_white);
            }
            if (id.equals("imgLeftNeckFemale")) {
                imgLeftNeckFemale.setImageResource(R.drawable.left_neck_female_white);
            }
            if (id.equals("imgRightChestFemale")) {
                imgRightChestFemale.setImageResource(R.drawable.right_chest_female_white);
            }
            if (id.equals("imgLeftChestFemale")) {
                imgLeftChestFemale.setImageResource(R.drawable.left_chest_female_white);
            }
            if (id.equals("imgRightShoulderFemale")) {
                imgRightShoulderFemale.setImageResource(R.drawable.right_shoulder_female_white);
            }
            if (id.equals("imgLeftShoulderFemaleWhite")) {
                imgLeftShoulderFemaleWhite.setImageResource(R.drawable.left_shoulder_female_white);
            }
            if (id.equals("imgRightArmFemale")) {
                imgRightArmFemale.setImageResource(R.drawable.right_arm_female_white);
            }
            if (id.equals("imgLeftArmFemale")) {
                imgLeftArmFemale.setImageResource(R.drawable.left_arm_female_white);
            }
            if (id.equals("imgRightCubitalFemale")) {
                imgRightCubitalFemale.setImageResource(R.drawable.right_cubital_female_white);
            }
            if (id.equals("imgLeftCubitalFemale")) {
                imgLeftCubitalFemale.setImageResource(R.drawable.left_cubital_female_white);
            }
            if (id.equals("imgRightForearmFemale")) {
                imgRightForearmFemale.setImageResource(R.drawable.right_forearm_female_white);
            }
            if (id.equals("imgLeftForearmFemale")) {
                imgLeftForearmFemale.setImageResource(R.drawable.left_forearm_female_white);
            }
            if (id.equals("imgRightWristFemale")) {
                imgRightWristFemale.setImageResource(R.drawable.right_wrist_female_white);
            }
            if (id.equals("imgLeftWristFemale")) {
                imgLeftWristFemale.setImageResource(R.drawable.left_wrist_female_white);
            }
            if (id.equals("imgRightPalmFemale")) {
                imgRightPalmFemale.setImageResource(R.drawable.right_palm_female_white);
            }
            if (id.equals("imgLeftPalmFemale")) {
                imgLeftPalmFemale.setImageResource(R.drawable.left_palm_female_white);
            }
            if (id.equals("imgRightStomachFemale")) {
                imgRightStomachFemale.setImageResource(R.drawable.right_stomach_female_white);
            }
            if (id.equals("imgLeftStomachFemale")) {
                imgLeftStomachFemale.setImageResource(R.drawable.left_stomach_female_white);
            }
            if (id.equals("imgRightHipFemale")) {
                imgRightHipFemale.setImageResource(R.drawable.right_hip_female_white);
            }
            if (id.equals("imgLeftHipFemale")) {
                imgLeftHipFemale.setImageResource(R.drawable.left_hip_female_white);
            }
            if (id.equals("imgRightPelvisFemale")) {
                imgRightPelvisFemale.setImageResource(R.drawable.right_pelvis_female_white);
            }
            if (id.equals("imgLeftPelvisFemale")) {
                imgLeftPelvisFemale.setImageResource(R.drawable.left_pelvis_female_white);
            }
            if (id.equals("imgRightThighFemale")) {
                imgRightThighFemale.setImageResource(R.drawable.right_thigh_female_white);
            }
            if (id.equals("imgLeftThighFemale")) {
                imgLeftThighFemale.setImageResource(R.drawable.left_thigh_female_white);
            }
            if (id.equals("imgLeftKneeFemale")) {
                imgLeftKneeFemale.setImageResource(R.drawable.left_knee_female_white);
            }
            if (id.equals("imgRightKneeFemale")) {
                imgRightKneeFemale.setImageResource(R.drawable.right_knee_female_white);
            }
            if (id.equals("imgRightCrusFemale")) {
                imgRightCrusFemale.setImageResource(R.drawable.right_crus_female_white);
            }
            if (id.equals("imgLeftCrusFemale")) {
                imgLeftCrusFemale.setImageResource(R.drawable.left_crus_female_white);
            }
            if (id.equals("imgRightTarsusFemale")) {
                imgRightTarsusFemale.setImageResource(R.drawable.right_tarsus_female_white);
            }
            if (id.equals("imgLeftTarsusFemale")) {
                imgLeftTarsusFemale.setImageResource(R.drawable.left_tarsus_female_white);
            }
            if (id.equals("imgRightFootFemale")) {
                imgRightFootFemale.setImageResource(R.drawable.right_foot_female_white);
            }
            if (id.equals("imgLeftFootFemale")) {
                imgLeftFootFemale.setImageResource(R.drawable.left_foot_female_white);
            }


            if (id.equals("imgLeftHeadFemaleBack")) {
                imgLeftHeadFemaleBack.setImageResource(R.drawable.left_head_female_back_white);
            }
            if (id.equals("imgRightHeadFemaleBack")) {
                imgRightHeadFemaleBack.setImageResource(R.drawable.right_head_female_back_white);
            }
            if (id.equals("imgLeftHead2FemaleBack")) {
                imgLeftHead2FemaleBack.setImageResource(R.drawable.left_head_2_female_back_white);
            }
            if (id.equals("imgRightHead2FemaleBack")) {
                imgRightHead2FemaleBack.setImageResource(R.drawable.right_head_2_female_back_white);
            }
            if (id.equals("imgLeftNeckFemaleBack")) {
                imgLeftNeckFemaleBack.setImageResource(R.drawable.left_neck_female_back_white);
            }
            if (id.equals("imgRightNeckFemaleBack")) {
                imgRightNeckFemaleBack.setImageResource(R.drawable.right_neck_female_back_white);
            }
            if (id.equals("imgLeftBackTopFemaleBack")) {
                imgLeftBackTopFemaleBack.setImageResource(R.drawable.left_back_top_female_back_white);
            }
            if (id.equals("imgRightBackTopFemaleBack")) {
                imgRightBackTopFemaleBack.setImageResource(R.drawable.right_back_top_female_back_white);
            }
            if (id.equals("imgLeftBackMiddleFemaleBack")) {
                imgLeftBackMiddleFemaleBack.setImageResource(R.drawable.left_back_middle_female_back_white);
            }
            if (id.equals("imgRightBackMiddleFemaleBack")) {
                imgRightBackMiddleFemaleBack.setImageResource(R.drawable.right_back_middle_female_back_white);
            }
            if (id.equals("imgLeftShoulderFemaleBack")) {
                imgLeftShoulderFemaleBack.setImageResource(R.drawable.left_shoulder_female_back_white);
            }
            if (id.equals("imgRightShoulderFemaleBack")) {
                imgRightShoulderFemaleBack.setImageResource(R.drawable.right_shoulder_female_back_white);
            }
            if (id.equals("imgRightArmFemaleBack")) {
                imgRightArmFemaleBack.setImageResource(R.drawable.left_arm_female_white);
            }
            if (id.equals("imgLeftArmFemaleBack")) {
                imgLeftArmFemaleBack.setImageResource(R.drawable.right_arm_female_white);
            }
            if (id.equals("imgRightCubitalFemaleBack")) {
                imgRightCubitalFemaleBack.setImageResource(R.drawable.right_cubital_female_back_white);
            }
            if (id.equals("imgLeftCubitalFemaleBack")) {
                imgLeftCubitalFemaleBack.setImageResource(R.drawable.left_cubital_female_back_white);
            }
            if (id.equals("imgRightForearmFemaleBack")) {
                imgRightForearmFemaleBack.setImageResource(R.drawable.right_forearm_female_back_white);
            }
            if (id.equals("imgLeftForearmFemaleBack")) {
                imgLeftForearmFemaleBack.setImageResource(R.drawable.left_forearm_female_back_white);
            }
            if (id.equals("imgRightWristFemaleBack")) {
                imgRightWristFemaleBack.setImageResource(R.drawable.right_wrist_female_back_white);
            }
            if (id.equals("imgLeftWristFemaleBack")) {
                imgLeftWristFemaleBack.setImageResource(R.drawable.left_wrist_female_back_white);
            }
            if (id.equals("imgRightPalmFemaleBack")) {
                imgRightPalmFemaleBack.setImageResource(R.drawable.right_palm_female_back_white);
            }
            if (id.equals("imgLeftPalmFemaleBack")) {
                imgLeftPalmFemaleBack.setImageResource(R.drawable.left_palm_female_back_white);
            }
            if (id.equals("imgLeftBackLowerFemaleBack")) {
                imgLeftBackLowerFemaleBack.setImageResource(R.drawable.left_back_lower_female_back_white);
            }
            if (id.equals("imgRightBackLowerFemaleBack")) {
                imgRightBackLowerFemaleBack.setImageResource(R.drawable.right_back_lower_female_back_white);
            }
            if (id.equals("imgLeftHipFemaleBack")) {
                imgLeftHipFemaleBack.setImageResource(R.drawable.left_hip_female_back_white);
            }
            if (id.equals("imgRightHipFemaleBack")) {
                imgRightHipFemaleBack.setImageResource(R.drawable.right_hip_female_back_white);
            }
            if (id.equals("imgLeftHip2FemaleBack")) {
                imgLeftHip2FemaleBack.setImageResource(R.drawable.left_hip2_female_back_white);
            }
            if (id.equals("imgRightHip2FemaleBack")) {
                imgRightHip2FemaleBack.setImageResource(R.drawable.right_hip2_female_back_white);
            }
            if (id.equals("imgLeftThighFemaleBack")) {
                imgLeftThighFemaleBack.setImageResource(R.drawable.left_thigh_female_back_white);
            }
            if (id.equals("imgRightThighFemaleBack")) {
                imgRightThighFemaleBack.setImageResource(R.drawable.right_thigh_female_back_white);
            }
            if (id.equals("imgRightKneeFemaleBack")) {
                imgRightKneeFemaleBack.setImageResource(R.drawable.right_knee_female_back_white);
            }
            if (id.equals("imgLeftKneeFemaleBack")) {
                imgLeftKneeFemaleBack.setImageResource(R.drawable.left_knee_female_back_white);
            }
            if (id.equals("imgLeftCrusFemaleBack")) {
                imgLeftCrusFemaleBack.setImageResource(R.drawable.left_crus_female_back_white);
            }
            if (id.equals("imgRightCrusFemaleBack")) {
                imgRightCrusFemaleBack.setImageResource(R.drawable.right_crus_female_back_white);
            }
            if (id.equals("imgRightTarsusFemaleBack")) {
                imgRightTarsusFemaleBack.setImageResource(R.drawable.right_tarsus_female_back_white);
            }
            if (id.equals("imgLeftTarsusFemaleBack")) {
                imgLeftTarsusFemaleBack.setImageResource(R.drawable.left_tarsus_female_back_white);
            }
            if (id.equals("imgRightFootFemaleBack")) {
                imgRightFootFemaleBack.setImageResource(R.drawable.right_foot_female_back_white);
            }
            if (id.equals("imgLeftFootFemaleBack")) {
                imgLeftFootFemaleBack.setImageResource(R.drawable.left_foot_female_back_white);
            }

        }
    }

    public void erase() {
        if (ApplicationTest.getInstance().getSelectedSevere().size() > 0) {
            ApplicationTest.getInstance().getSelectedSevere().clear();
            ApplicationTest.getInstance().getSelectedPartName().clear();
            ApplicationTest.getInstance().getSelectedColor().clear();
                imgRightHeadFemale.setImageResource(R.drawable.right_head_female_white);
                imgLeftHeadFemale.setImageResource(R.drawable.left_head_female_white);
                imgRightFaceFemale.setImageResource(R.drawable.right_face_female_white);
                imgLeftFaceFemale.setImageResource(R.drawable.left_face_female_white);
                imgRightNeckFemale.setImageResource(R.drawable.right_neck_female_white);
                imgLeftNeckFemale.setImageResource(R.drawable.left_neck_female_white);
                imgRightChestFemale.setImageResource(R.drawable.right_chest_female_white);
                imgLeftChestFemale.setImageResource(R.drawable.left_chest_female_white);
                imgRightShoulderFemale.setImageResource(R.drawable.right_shoulder_female_white);
                imgLeftShoulderFemaleWhite.setImageResource(R.drawable.left_shoulder_female_white);
                imgRightArmFemale.setImageResource(R.drawable.right_arm_female_white);
                imgLeftArmFemale.setImageResource(R.drawable.left_arm_female_white);
                imgRightCubitalFemale.setImageResource(R.drawable.right_cubital_female_white);
                imgLeftCubitalFemale.setImageResource(R.drawable.left_cubital_female_white);
                imgRightForearmFemale.setImageResource(R.drawable.right_forearm_female_white);
                imgLeftForearmFemale.setImageResource(R.drawable.left_forearm_female_white);
                imgRightWristFemale.setImageResource(R.drawable.right_wrist_female_white);
                imgLeftWristFemale.setImageResource(R.drawable.left_wrist_female_white);
                imgRightPalmFemale.setImageResource(R.drawable.right_palm_female_white);
                imgLeftPalmFemale.setImageResource(R.drawable.left_palm_female_white);
                imgRightStomachFemale.setImageResource(R.drawable.right_stomach_female_white);
                imgLeftStomachFemale.setImageResource(R.drawable.left_stomach_female_white);
                imgRightHipFemale.setImageResource(R.drawable.right_hip_female_white);
                imgLeftHipFemale.setImageResource(R.drawable.left_hip_female_white);
                imgRightPelvisFemale.setImageResource(R.drawable.right_pelvis_female_white);
                imgLeftPelvisFemale.setImageResource(R.drawable.left_pelvis_female_white);
                imgRightThighFemale.setImageResource(R.drawable.right_thigh_female_white);
                imgLeftThighFemale.setImageResource(R.drawable.left_thigh_female_white);
                imgLeftKneeFemale.setImageResource(R.drawable.left_knee_female_white);
                imgRightKneeFemale.setImageResource(R.drawable.right_knee_female_white);
                imgRightCrusFemale.setImageResource(R.drawable.right_crus_female_white);
                imgLeftCrusFemale.setImageResource(R.drawable.left_crus_female_white);
                imgRightTarsusFemale.setImageResource(R.drawable.right_tarsus_female_white);
                imgLeftTarsusFemale.setImageResource(R.drawable.left_tarsus_female_white);
                imgRightFootFemale.setImageResource(R.drawable.right_foot_female_white);
                imgLeftFootFemale.setImageResource(R.drawable.left_foot_female_white);


                imgLeftHeadFemaleBack.setImageResource(R.drawable.left_head_female_back_white);
                imgRightHeadFemaleBack.setImageResource(R.drawable.right_head_female_back_white);
                imgLeftHead2FemaleBack.setImageResource(R.drawable.left_head_2_female_back_white);
                imgRightHead2FemaleBack.setImageResource(R.drawable.right_head_2_female_back_white);
                imgLeftNeckFemaleBack.setImageResource(R.drawable.left_neck_female_back_white);
                imgRightNeckFemaleBack.setImageResource(R.drawable.right_neck_female_back_white);
                imgLeftBackTopFemaleBack.setImageResource(R.drawable.left_back_top_female_back_white);
                imgRightBackTopFemaleBack.setImageResource(R.drawable.right_back_top_female_back_white);
                imgLeftBackMiddleFemaleBack.setImageResource(R.drawable.left_back_middle_female_back_white);
                imgRightBackMiddleFemaleBack.setImageResource(R.drawable.right_back_middle_female_back_white);
                imgLeftShoulderFemaleBack.setImageResource(R.drawable.left_shoulder_female_back_white);
                imgRightShoulderFemaleBack.setImageResource(R.drawable.right_shoulder_female_back_white);
                imgRightArmFemaleBack.setImageResource(R.drawable.left_arm_female_white);
                imgLeftArmFemaleBack.setImageResource(R.drawable.right_arm_female_white);
                imgRightCubitalFemaleBack.setImageResource(R.drawable.right_cubital_female_back_white);
                imgLeftCubitalFemaleBack.setImageResource(R.drawable.left_cubital_female_back_white);
                imgRightForearmFemaleBack.setImageResource(R.drawable.right_forearm_female_back_white);
                imgLeftForearmFemaleBack.setImageResource(R.drawable.left_forearm_female_back_white);
                imgRightWristFemaleBack.setImageResource(R.drawable.right_wrist_female_back_white);
                imgLeftWristFemaleBack.setImageResource(R.drawable.left_wrist_female_back_white);
                imgRightPalmFemaleBack.setImageResource(R.drawable.right_palm_female_back_white);
                imgLeftPalmFemaleBack.setImageResource(R.drawable.left_palm_female_back_white);
                imgLeftBackLowerFemaleBack.setImageResource(R.drawable.left_back_lower_female_back_white);
                imgRightBackLowerFemaleBack.setImageResource(R.drawable.right_back_lower_female_back_white);
                imgLeftHipFemaleBack.setImageResource(R.drawable.left_hip_female_back_white);
                imgRightHipFemaleBack.setImageResource(R.drawable.right_hip_female_back_white);
                imgLeftHip2FemaleBack.setImageResource(R.drawable.left_hip2_female_back_white);
                imgRightHip2FemaleBack.setImageResource(R.drawable.right_hip2_female_back_white);
                imgLeftThighFemaleBack.setImageResource(R.drawable.left_thigh_female_back_white);
                imgRightThighFemaleBack.setImageResource(R.drawable.right_thigh_female_back_white);
                imgRightKneeFemaleBack.setImageResource(R.drawable.right_knee_female_back_white);
                imgLeftKneeFemaleBack.setImageResource(R.drawable.left_knee_female_back_white);
                imgLeftCrusFemaleBack.setImageResource(R.drawable.left_crus_female_back_white);
                imgRightCrusFemaleBack.setImageResource(R.drawable.right_crus_female_back_white);
                imgRightTarsusFemaleBack.setImageResource(R.drawable.right_tarsus_female_back_white);
                imgLeftTarsusFemaleBack.setImageResource(R.drawable.left_tarsus_female_back_white);
                imgRightFootFemaleBack.setImageResource(R.drawable.right_foot_female_back_white);
                imgLeftFootFemaleBack.setImageResource(R.drawable.left_foot_female_back_white);

        }
    }


    public void ShowPopupForSaved() {

        Button next;
        myDialog.setContentView(R.layout.dialog_intensity_saved);
        next = (Button) myDialog.findViewById(R.id.btn_next);
        TextView txt_heading = (TextView) myDialog.findViewById(R.id.txt_heading);
        TextView txt_main_content = (TextView) myDialog.findViewById(R.id.txt_main_content);
        TextView txt_date = (TextView) myDialog.findViewById(R.id.txt_date);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        txt_heading.setText("Pain Report");
        txt_main_content.setText("SAVED");


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy- hh:mm a", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());

        txt_date.setText(currentDateandTime);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(FemaleBodyActivity.this, VideoIntroActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                n.putExtra("intensity_id", intensity_id);
                startActivity(n);
            }
        });

        myDialog.show();
    }


    private void selectColour(){
        if (tripalBackToExitPressedOnce) {
            tripalBackToExitPressedOnce=false;
            doubleBackToExitPressedOnce=false;
            imgSeverity.setImageResource(R.mipmap.paint_bucket_severe);
            ApplicationTest.getInstance().setSelectedSeverity("severe");
        }else if (doubleBackToExitPressedOnce) {
            tripalBackToExitPressedOnce=true;
            doubleBackToExitPressedOnce=false;
            imgSeverity.setImageResource(R.mipmap.paint_bucket_moderate);
            ApplicationTest.getInstance().setSelectedSeverity("moderate");
        }else {
            imgSeverity.setImageResource(R.mipmap.paint_bucket_mild);
            ApplicationTest.getInstance().setSelectedSeverity("mild");
            doubleBackToExitPressedOnce = true;
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }

    private void setImgClipboard() {
        if (ApplicationTest.getInstance().clipBoardData.size() != 0) {

            for (int i = 0; i < ApplicationTest.getInstance().clipBoardData.size(); i++) {

                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_head_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHeadFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_head_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHeadFemale.setImageResource(R.drawable.right_head_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHeadFemale.setImageResource(R.drawable.right_head_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHeadFemale.setImageResource(R.drawable.right_head_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_head_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHeadFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_head_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHeadFemale.setImageResource(R.drawable.left_head_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHeadFemale.setImageResource(R.drawable.left_head_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHeadFemale.setImageResource(R.drawable.left_head_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_face_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFaceFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_face_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightFaceFemale.setImageResource(R.drawable.right_face_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightFaceFemale.setImageResource(R.drawable.right_face_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightFaceFemale.setImageResource(R.drawable.right_face_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_face_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFaceFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_face_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftFaceFemale.setImageResource(R.drawable.left_face_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftFaceFemale.setImageResource(R.drawable.left_face_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftFaceFemale.setImageResource(R.drawable.left_face_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_neck_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeckFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_neck_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightNeckFemale.setImageResource(R.drawable.right_neck_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightNeckFemale.setImageResource(R.drawable.right_neck_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightNeckFemale.setImageResource(R.drawable.right_neck_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_neck_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeckFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_neck_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftNeckFemale.setImageResource(R.drawable.left_neck_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftNeckFemale.setImageResource(R.drawable.left_neck_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftNeckFemale.setImageResource(R.drawable.left_neck_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_chest_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightChestFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_chest_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightChestFemale.setImageResource(R.drawable.right_chest_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightChestFemale.setImageResource(R.drawable.right_chest_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightChestFemale.setImageResource(R.drawable.right_chest_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_chest_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftChestFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_chest_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftChestFemale.setImageResource(R.drawable.left_chest_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftChestFemale.setImageResource(R.drawable.left_chest_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftChestFemale.setImageResource(R.drawable.left_chest_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_shoulder_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulderFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_shoulder_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightShoulderFemale.setImageResource(R.drawable.right_shoulder_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightShoulderFemale.setImageResource(R.drawable.right_shoulder_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightShoulderFemale.setImageResource(R.drawable.right_shoulder_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_shoulder_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulderFemaleWhite");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_shoulder_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftShoulderFemaleWhite.setImageResource(R.drawable.left_shoulder_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftShoulderFemaleWhite.setImageResource(R.drawable.left_shoulder_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftShoulderFemaleWhite.setImageResource(R.drawable.left_shoulder_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_arm_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_arm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightArmFemale.setImageResource(R.drawable.right_arm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightArmFemale.setImageResource(R.drawable.right_arm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightArmFemale.setImageResource(R.drawable.right_arm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_arm_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_arm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftArmFemale.setImageResource(R.drawable.left_arm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftArmFemale.setImageResource(R.drawable.left_arm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftArmFemale.setImageResource(R.drawable.left_arm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_cubital_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubitalFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_cubital_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCubitalFemale.setImageResource(R.drawable.right_cubital_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCubitalFemale.setImageResource(R.drawable.right_cubital_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCubitalFemale.setImageResource(R.drawable.right_cubital_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_cubital_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubitalFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_cubital_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCubitalFemale.setImageResource(R.drawable.left_cubital_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCubitalFemale.setImageResource(R.drawable.left_cubital_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCubitalFemale.setImageResource(R.drawable.left_cubital_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_forearm_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_forearm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightForearmFemale.setImageResource(R.drawable.right_forearm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightForearmFemale.setImageResource(R.drawable.right_forearm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightForearmFemale.setImageResource(R.drawable.right_forearm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_forearm_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_forearm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftForearmFemale.setImageResource(R.drawable.left_forearm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftForearmFemale.setImageResource(R.drawable.left_forearm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftForearmFemale.setImageResource(R.drawable.left_forearm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_wrist_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWristFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_wrist_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightWristFemale.setImageResource(R.drawable.right_wrist_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightWristFemale.setImageResource(R.drawable.right_wrist_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightWristFemale.setImageResource(R.drawable.right_wrist_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_wrist_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWristFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_wrist_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftWristFemale.setImageResource(R.drawable.left_wrist_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftWristFemale.setImageResource(R.drawable.left_wrist_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftWristFemale.setImageResource(R.drawable.left_wrist_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_palm_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_palm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightPalmFemale.setImageResource(R.drawable.right_palm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightPalmFemale.setImageResource(R.drawable.right_palm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightPalmFemale.setImageResource(R.drawable.right_palm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_palm_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalmFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_palm_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftPalmFemale.setImageResource(R.drawable.left_palm_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftPalmFemale.setImageResource(R.drawable.left_palm_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftPalmFemale.setImageResource(R.drawable.left_palm_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_stomach_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightStomachFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_stomach_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightStomachFemale.setImageResource(R.drawable.right_stomach_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightStomachFemale.setImageResource(R.drawable.right_stomach_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightStomachFemale.setImageResource(R.drawable.right_stomach_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_stomach_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftStomachFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_stomach_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftStomachFemale.setImageResource(R.drawable.left_stomach_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftStomachFemale.setImageResource(R.drawable.left_stomach_female);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftStomachFemale.setImageResource(R.drawable.left_stomach_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_hip_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHipFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHipFemale.setImageResource(R.drawable.right_hip_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHipFemale.setImageResource(R.drawable.right_hip_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHipFemale.setImageResource(R.drawable.right_hip_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_hip_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHipFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHipFemale.setImageResource(R.drawable.left_hip_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHipFemale.setImageResource(R.drawable.left_hip_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHipFemale.setImageResource(R.drawable.left_hip_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_pelvis_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPelvisFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_pelvis_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightPelvisFemale.setImageResource(R.drawable.right_pelvis_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightPelvisFemale.setImageResource(R.drawable.right_pelvis_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightPelvisFemale.setImageResource(R.drawable.right_pelvis_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_pelvis_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPelvisFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_pelvis_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftPelvisFemale.setImageResource(R.drawable.left_pelvis_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftPelvisFemale.setImageResource(R.drawable.left_pelvis_female);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftPelvisFemale.setImageResource(R.drawable.left_pelvis_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_thigh_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThighFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_thigh_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightThighFemale.setImageResource(R.drawable.right_thigh_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightThighFemale.setImageResource(R.drawable.right_thigh_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightThighFemale.setImageResource(R.drawable.right_thigh_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_thigh_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThighFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_thigh_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftThighFemale.setImageResource(R.drawable.left_thigh_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftThighFemale.setImageResource(R.drawable.left_thigh_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftThighFemale.setImageResource(R.drawable.left_thigh_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_knee_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKneeFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_knee_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftKneeFemale.setImageResource(R.drawable.left_knee_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftKneeFemale.setImageResource(R.drawable.left_knee_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftKneeFemale.setImageResource(R.drawable.left_knee_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_knee_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKneeFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_knee_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightKneeFemale.setImageResource(R.drawable.right_knee_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightKneeFemale.setImageResource(R.drawable.right_knee_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightKneeFemale.setImageResource(R.drawable.right_knee_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_crus_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrusFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_crus_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCrusFemale.setImageResource(R.drawable.right_crus_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCrusFemale.setImageResource(R.drawable.right_crus_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCrusFemale.setImageResource(R.drawable.right_crus_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_crus_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrusFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_crus_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCrusFemale.setImageResource(R.drawable.left_crus_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCrusFemale.setImageResource(R.drawable.left_crus_female);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCrusFemale.setImageResource(R.drawable.left_crus_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_tarsus_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsusFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_tarsus_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightTarsusFemale.setImageResource(R.drawable.right_tarsus_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightTarsusFemale.setImageResource(R.drawable.right_tarsus_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightTarsusFemale.setImageResource(R.drawable.right_tarsus_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_tarsus_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsusFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_tarsus_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftTarsusFemale.setImageResource(R.drawable.left_tarsus_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftTarsusFemale.setImageResource(R.drawable.left_tarsus_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftTarsusFemale.setImageResource(R.drawable.left_tarsus_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_foot_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFootFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_foot_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightFootFemale.setImageResource(R.drawable.right_foot_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightFootFemale.setImageResource(R.drawable.right_foot_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightFootFemale.setImageResource(R.drawable.right_foot_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_foot_female_front")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFootFemale");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_foot_female_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftFootFemale.setImageResource(R.drawable.left_foot_female_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftFootFemale.setImageResource(R.drawable.left_foot_female_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftFootFemale.setImageResource(R.drawable.left_foot_female_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }




                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_head_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHeadFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_head_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHeadFemaleBack.setImageResource(R.drawable.left_head_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHeadFemaleBack.setImageResource(R.drawable.left_head_female_back);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHeadFemaleBack.setImageResource(R.drawable.left_head_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_head_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHeadFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_head_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHeadFemaleBack.setImageResource(R.drawable.right_head_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHeadFemaleBack.setImageResource(R.drawable.right_head_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHeadFemaleBack.setImageResource(R.drawable.right_head_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_face_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHead2FemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_face_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHead2FemaleBack.setImageResource(R.drawable.left_head_2_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHead2FemaleBack.setImageResource(R.drawable.left_head_2_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHead2FemaleBack.setImageResource(R.drawable.left_head_2_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_face_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHead2FemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_face_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHead2FemaleBack.setImageResource(R.drawable.right_head_2_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHead2FemaleBack.setImageResource(R.drawable.right_head_2_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHead2FemaleBack.setImageResource(R.drawable.right_head_2_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_neck_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeckFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_neck_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftNeckFemaleBack.setImageResource(R.drawable.left_neck_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftNeckFemaleBack.setImageResource(R.drawable.left_neck_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftNeckFemaleBack.setImageResource(R.drawable.left_neck_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_neck_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeckFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_neck_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightNeckFemaleBack.setImageResource(R.drawable.right_neck_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightNeckFemaleBack.setImageResource(R.drawable.right_neck_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightNeckFemaleBack.setImageResource(R.drawable.right_neck_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_back_top_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackTopFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_top_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftBackTopFemaleBack.setImageResource(R.drawable.left_back_top_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftBackTopFemaleBack.setImageResource(R.drawable.left_back_top_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftBackTopFemaleBack.setImageResource(R.drawable.left_back_top_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_back_top_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackTopFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_top_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightBackTopFemaleBack.setImageResource(R.drawable.right_back_top_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightBackTopFemaleBack.setImageResource(R.drawable.right_back_top_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightBackTopFemaleBack.setImageResource(R.drawable.right_back_top_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_back_middle_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackMiddleFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_middle_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftBackMiddleFemaleBack.setImageResource(R.drawable.left_back_middle_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftBackMiddleFemaleBack.setImageResource(R.drawable.left_back_middle_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftBackMiddleFemaleBack.setImageResource(R.drawable.left_back_middle_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_back_middle_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackMiddleFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_middle_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightBackMiddleFemaleBack.setImageResource(R.drawable.right_back_middle_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightBackMiddleFemaleBack.setImageResource(R.drawable.right_back_middle_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightBackMiddleFemaleBack.setImageResource(R.drawable.right_back_middle_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_shoulder_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulderFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_shoulder_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftShoulderFemaleBack.setImageResource(R.drawable.left_shoulder_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftShoulderFemaleBack.setImageResource(R.drawable.left_shoulder_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftShoulderFemaleBack.setImageResource(R.drawable.left_shoulder_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_shoulder_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulderFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_shoulder_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightShoulderFemaleBack.setImageResource(R.drawable.right_shoulder_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightShoulderFemaleBack.setImageResource(R.drawable.right_shoulder_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightShoulderFemaleBack.setImageResource(R.drawable.right_shoulder_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_arm_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_arm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftArmFemaleBack.setImageResource(R.drawable.left_arm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftArmFemaleBack.setImageResource(R.drawable.left_arm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftArmFemaleBack.setImageResource(R.drawable.left_arm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_arm_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_arm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightArmFemaleBack.setImageResource(R.drawable.right_arm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightArmFemaleBack.setImageResource(R.drawable.right_arm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightArmFemaleBack.setImageResource(R.drawable.right_arm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_cubital_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubitalFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_cubital_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCubitalFemaleBack.setImageResource(R.drawable.left_cubital_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCubitalFemaleBack.setImageResource(R.drawable.left_cubital_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCubitalFemaleBack.setImageResource(R.drawable.left_cubital_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_cubital_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubitalFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_cubital_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCubitalFemaleBack.setImageResource(R.drawable.right_cubital_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCubitalFemaleBack.setImageResource(R.drawable.right_cubital_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCubitalFemaleBack.setImageResource(R.drawable.right_cubital_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_forearm_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_forearm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftForearmFemaleBack.setImageResource(R.drawable.left_forearm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftForearmFemaleBack.setImageResource(R.drawable.left_forearm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftForearmFemaleBack.setImageResource(R.drawable.left_forearm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_forearm_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_forearm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightForearmFemaleBack.setImageResource(R.drawable.right_forearm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightForearmFemaleBack.setImageResource(R.drawable.right_forearm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightForearmFemaleBack.setImageResource(R.drawable.right_forearm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_wrist_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWristFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_wrist_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftWristFemaleBack.setImageResource(R.drawable.left_wrist_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftWristFemaleBack.setImageResource(R.drawable.left_wrist_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftWristFemaleBack.setImageResource(R.drawable.left_wrist_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_wrist_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWristFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_wrist_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightWristFemaleBack.setImageResource(R.drawable.right_wrist_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightWristFemaleBack.setImageResource(R.drawable.right_wrist_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightWristFemaleBack.setImageResource(R.drawable.right_wrist_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_palm_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_palm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftPalmFemaleBack.setImageResource(R.drawable.left_palm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftPalmFemaleBack.setImageResource(R.drawable.left_palm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftPalmFemaleBack.setImageResource(R.drawable.left_palm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_palm_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalmFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_palm_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightPalmFemaleBack.setImageResource(R.drawable.right_palm_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightPalmFemaleBack.setImageResource(R.drawable.right_palm_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightPalmFemaleBack.setImageResource(R.drawable.right_palm_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_back_lower_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackLowerFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_lower_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftBackLowerFemaleBack.setImageResource(R.drawable.left_back_lower_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftBackLowerFemaleBack.setImageResource(R.drawable.left_back_lower_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftBackLowerFemaleBack.setImageResource(R.drawable.left_back_lower_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_back_lower_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackLowerFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_lower_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightBackLowerFemaleBack.setImageResource(R.drawable.right_back_lower_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightBackLowerFemaleBack.setImageResource(R.drawable.right_back_lower_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightBackLowerFemaleBack.setImageResource(R.drawable.right_back_lower_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_hip_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHipFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHipFemaleBack.setImageResource(R.drawable.right_hip_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHipFemaleBack.setImageResource(R.drawable.right_hip_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHipFemaleBack.setImageResource(R.drawable.right_hip_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_hip2_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHip2FemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip2_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHip2FemaleBack.setImageResource(R.drawable.left_hip2_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHip2FemaleBack.setImageResource(R.drawable.left_hip2_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHip2FemaleBack.setImageResource(R.drawable.left_hip2_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_hip2_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHip2FemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip2_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHip2FemaleBack.setImageResource(R.drawable.right_hip2_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHip2FemaleBack.setImageResource(R.drawable.right_hip2_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHip2FemaleBack.setImageResource(R.drawable.right_hip2_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_thigh_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThighFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_thigh_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftThighFemaleBack.setImageResource(R.drawable.left_thigh_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftThighFemaleBack.setImageResource(R.drawable.left_thigh_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftThighFemaleBack.setImageResource(R.drawable.left_thigh_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_thigh_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThighFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_thigh_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightThighFemaleBack.setImageResource(R.drawable.right_thigh_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightThighFemaleBack.setImageResource(R.drawable.right_thigh_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightThighFemaleBack.setImageResource(R.drawable.right_thigh_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_knee_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKneeFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_knee_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightKneeFemaleBack.setImageResource(R.drawable.right_knee_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightKneeFemaleBack.setImageResource(R.drawable.right_knee_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightKneeFemaleBack.setImageResource(R.drawable.right_knee_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_knee_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKneeFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_knee_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftKneeFemaleBack.setImageResource(R.drawable.left_knee_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftKneeFemaleBack.setImageResource(R.drawable.left_knee_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftKneeFemaleBack.setImageResource(R.drawable.left_knee_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_crus_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrusFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_crus_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCrusFemaleBack.setImageResource(R.drawable.left_crus_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCrusFemaleBack.setImageResource(R.drawable.left_crus_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCrusFemaleBack.setImageResource(R.drawable.left_crus_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_crus_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrusFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_crus_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCrusFemaleBack.setImageResource(R.drawable.right_crus_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCrusFemaleBack.setImageResource(R.drawable.right_crus_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCrusFemaleBack.setImageResource(R.drawable.right_crus_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_tarsus_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsusFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_tarsus_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightTarsusFemaleBack.setImageResource(R.drawable.right_tarsus_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightTarsusFemaleBack.setImageResource(R.drawable.right_tarsus_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightTarsusFemaleBack.setImageResource(R.drawable.right_tarsus_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_tarsus_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsusFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_tarsus_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftTarsusFemaleBack.setImageResource(R.drawable.left_tarsus_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftTarsusFemaleBack.setImageResource(R.drawable.left_tarsus_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftTarsusFemaleBack.setImageResource(R.drawable.left_tarsus_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_foot_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFootFemaleBack");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_foot_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightFootFemaleBack.setImageResource(R.drawable.right_foot_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightFootFemaleBack.setImageResource(R.drawable.right_foot_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightFootFemaleBack.setImageResource(R.drawable.right_foot_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_foot_female_back")){
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFootFemaleBack");
                    ApplicationTest.getInstance().addInFrontBack("name", "left_foot_female_back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftFootFemaleBack.setImageResource(R.drawable.left_foot_female_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftFootFemaleBack.setImageResource(R.drawable.left_foot_female_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftFootFemaleBack.setImageResource(R.drawable.left_foot_female_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }

            }
        }
    }

}
