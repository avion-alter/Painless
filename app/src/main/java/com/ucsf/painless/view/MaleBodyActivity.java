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
import com.ucsf.painless.model.login.LoginResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.view.fragments.MaleBackFragment;
import com.ucsf.painless.view.fragments.MaleFrontFragment;
import com.ucsf.painless.viewModel.BodyMapViewModel;
import com.ucsf.painless.viewModel.LoginViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaleBodyActivity extends AppCompatActivity {


    @BindView(R.id.card_back)
    CardView cardBack;
    /* @BindView(R.id.frameLayout_container)
     FrameLayout frameLayoutContainer;*/
    @BindView(R.id.img_flip)
    ImageView imgFlip;

    boolean front = true;
    @BindView(R.id.card_save)
    CardView cardSave;
    @BindView(R.id.txt_heading)
    TextView txtHeading;
    @BindView(R.id.img_undo)
    ImageView imgUndo;
    @BindView(R.id.img_severity)
    ImageView imgSeverity;
    @BindView(R.id.img_erase)
    ImageView imgErase;
    @BindView(R.id.img_clipboard)
    ImageView imgClipboard;

    Dialog myDialog;

    MaleBackFragment maleBackFragment;
    MaleFrontFragment maleFrontFragment;
    @BindView(R.id.img_right_head)
    ImageView imgRightHead;
    @BindView(R.id.img_left_head)
    ImageView imgLeftHead;
    @BindView(R.id.linear_head)
    LinearLayout linearHead;
    @BindView(R.id.img_right_face)
    ImageView imgRightFace;
    @BindView(R.id.img_left_face)
    ImageView imgLeftFace;
    @BindView(R.id.linear_face)
    LinearLayout linearFace;
    @BindView(R.id.img_right_neck)
    ImageView imgRightNeck;
    @BindView(R.id.img_left_neck)
    ImageView imgLeftNeck;
    @BindView(R.id.img_right_chest)
    ImageView imgRightChest;
    @BindView(R.id.img_left_chest)
    ImageView imgLeftChest;
    @BindView(R.id.linear_chest)
    LinearLayout linearChest;
    @BindView(R.id.img_right_shoulder)
    ImageView imgRightShoulder;
    @BindView(R.id.img_left_shoulder)
    ImageView imgLeftShoulder;
    @BindView(R.id.linear_sholder)
    LinearLayout linearSholder;
    @BindView(R.id.img_right_arm)
    ImageView imgRightArm;
    @BindView(R.id.img_left_arm)
    ImageView imgLeftArm;
    @BindView(R.id.linear_arm)
    LinearLayout linearArm;
    @BindView(R.id.img_right_cubital)
    ImageView imgRightCubital;
    @BindView(R.id.img_left_cubital)
    ImageView imgLeftCubital;
    @BindView(R.id.linear_cubital)
    LinearLayout linearCubital;
    @BindView(R.id.img_right_forearm)
    ImageView imgRightForearm;
    @BindView(R.id.img_left_forearm)
    ImageView imgLeftForearm;
    @BindView(R.id.linear_forearm)
    LinearLayout linearForearm;
    @BindView(R.id.img_right_wrist)
    ImageView imgRightWrist;
    @BindView(R.id.img_left_wrist)
    ImageView imgLeftWrist;
    @BindView(R.id.linear_rist)
    LinearLayout linearRist;
    @BindView(R.id.img_right_palm)
    ImageView imgRightPalm;
    @BindView(R.id.img_left_palm)
    ImageView imgLeftPalm;
    @BindView(R.id.linear_palm)
    LinearLayout linearPalm;
    @BindView(R.id.img_right_stomach)
    ImageView imgRightStomach;
    @BindView(R.id.img_left_stomach)
    ImageView imgLeftStomach;
    @BindView(R.id.linear_stomach)
    LinearLayout linearStomach;
    @BindView(R.id.img_right_hip)
    ImageView imgRightHip;
    @BindView(R.id.img_left_hip)
    ImageView imgLeftHip;
    @BindView(R.id.linear_hip)
    LinearLayout linearHip;
    @BindView(R.id.img_right_pelvis)
    ImageView imgRightPelvis;
    @BindView(R.id.img_left_pelvis)
    ImageView imgLeftPelvis;
    @BindView(R.id.linear_pelvin)
    LinearLayout linearPelvin;
    @BindView(R.id.img_right_thigh)
    ImageView imgRightThigh;
    @BindView(R.id.img_left_thigh)
    ImageView imgLeftThigh;
    @BindView(R.id.linear_thigh)
    LinearLayout linearThigh;
    @BindView(R.id.img_left_knee)
    ImageView imgLeftKnee;
    @BindView(R.id.img_right_knee)
    ImageView imgRightKnee;
    @BindView(R.id.linear_knee)
    LinearLayout linearKnee;
    @BindView(R.id.img_right_crus)
    ImageView imgRightCrus;
    @BindView(R.id.img_left_crus)
    ImageView imgLeftCrus;
    @BindView(R.id.linear_crus)
    LinearLayout linearCrus;
    @BindView(R.id.img_right_tarsus)
    ImageView imgRightTarsus;
    @BindView(R.id.img_left_tarsus)
    ImageView imgLeftTarsus;
    @BindView(R.id.img_right_foot)
    ImageView imgRightFoot;
    @BindView(R.id.img_left_foot)
    ImageView imgLeftFoot;
    @BindView(R.id.relative_mail_front)
    RelativeLayout relativeMailFront;
    @BindView(R.id.relative_frame)
    RelativeLayout relativeFrame;
    @BindView(R.id.img_left_head_back)
    ImageView imgLeftHeadBack;
    @BindView(R.id.img_right_head_back)
    ImageView imgRightHeadBack;
    @BindView(R.id.img_left_head_2_back)
    ImageView imgLeftHead2Back;
    @BindView(R.id.img_right_head_2_back)
    ImageView imgRightHead2Back;
    @BindView(R.id.img_left_neck_back)
    ImageView imgLeftNeckBack;
    @BindView(R.id.img_right_neck_back)
    ImageView imgRightNeckBack;
    @BindView(R.id.img_left_back_top_back)
    ImageView imgLeftBackTopBack;
    @BindView(R.id.img_right_back_top_back)
    ImageView imgRightBackTopBack;
    @BindView(R.id.img_left_back_middle_back)
    ImageView imgLeftBackMiddleBack;
    @BindView(R.id.img_right_back_middle_back)
    ImageView imgRightBackMiddleBack;
    @BindView(R.id.img_left_shoulder_back)
    ImageView imgLeftShoulderBack;
    @BindView(R.id.img_right_shoulder_back)
    ImageView imgRightShoulderBack;
    @BindView(R.id.img_right_arm_back)
    ImageView imgRightArmBack;
    @BindView(R.id.img_left_arm_back)
    ImageView imgLeftArmBack;
    @BindView(R.id.img_right_cubital_back)
    ImageView imgRightCubitalBack;
    @BindView(R.id.img_left_cubital_back)
    ImageView imgLeftCubitalBack;
    @BindView(R.id.img_right_forearm_back)
    ImageView imgRightForearmBack;
    @BindView(R.id.img_left_forearm_back)
    ImageView imgLeftForearmBack;
    @BindView(R.id.img_right_wris_backt)
    ImageView imgRightWrisBackt;
    @BindView(R.id.img_left_wrist_back)
    ImageView imgLeftWristBack;
    @BindView(R.id.img_right_palm_back)
    ImageView imgRightPalmBack;
    @BindView(R.id.img_left_palm_back)
    ImageView imgLeftPalmBack;
    @BindView(R.id.img_left_back_lower_back)
    ImageView imgLeftBackLowerBack;
    @BindView(R.id.img_right_back_lower_back)
    ImageView imgRightBackLowerBack;
    @BindView(R.id.img_left_hip_back)
    ImageView imgLeftHipBack;
    @BindView(R.id.img_right_hip_back)
    ImageView imgRightHipBack;
    @BindView(R.id.img_left_hip2)
    ImageView imgLeftHip2;
    @BindView(R.id.img_right_hip2_back)
    ImageView imgRightHip2Back;
    @BindView(R.id.img_left_thigh_back)
    ImageView imgLeftThighBack;
    @BindView(R.id.img_right_thigh_back)
    ImageView imgRightThighBack;
    @BindView(R.id.img_right_knee_back)
    ImageView imgRightKneeBack;
    @BindView(R.id.img_left_knee_back)
    ImageView imgLeftKneeBack;
    @BindView(R.id.img_left_crus_back)
    ImageView imgLeftCrusBack;
    @BindView(R.id.img_right_crus_back)
    ImageView imgRightCrusBack;
    @BindView(R.id.img_right_tarsus_back)
    ImageView imgRightTarsusBack;
    @BindView(R.id.img_left_tarsus_back)
    ImageView imgLeftTarsusBack;
    @BindView(R.id.img_right_foot_back)
    ImageView imgRightFootBack;
    @BindView(R.id.img_left_foot_back)
    ImageView imgLeftFootBack;
    @BindView(R.id.relative_male_back)
    RelativeLayout relativeMaleBack;

    BodyMapViewModel bodyMapViewModel;
    String intensity_id;

    boolean doubleBackToExitPressedOnce = false;
    boolean tripalBackToExitPressedOnce = false;

    boolean doubleLeftHead = false;
    boolean tripaleLeftHead = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_body);
        ButterKnife.bind(this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        bodyMapViewModel = ViewModelProviders.of(this).get(BodyMapViewModel.class);
        maleFrontFragment = new MaleFrontFragment();
        maleBackFragment = new MaleBackFragment();
        intensity_id = getIntent().getStringExtra("intensity_id");
        ApplicationTest.getInstance().clipBoardData = new ArrayList<>();

        if (front) {
            relativeMailFront.setVisibility(View.VISIBLE);
            relativeMaleBack.setVisibility(View.GONE);
            front = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ApplicationTest.getInstance().clipBoardData.size() != 0)
            setImgClipboard();
    }

    @OnClick(R.id.card_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick(R.id.img_flip)
    public void onFlipViewClicked() {
        if (front) {
            txtHeading.setText("FRONT");
            relativeMailFront.setVisibility(View.VISIBLE);
            relativeMaleBack.setVisibility(View.GONE);
            imgFlip.setImageResource(R.mipmap.switch_back);
            front = false;
        } else {
            txtHeading.setText("BACK");
            relativeMailFront.setVisibility(View.GONE);
            relativeMaleBack.setVisibility(View.VISIBLE);
            imgFlip.setImageResource(R.mipmap.switch_front);
            front = true;
        }
    }

    @OnClick({R.id.card_save, R.id.img_undo, R.id.img_severity, R.id.img_erase, R.id.img_clipboard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_save:
                JsonArray jsonArray = null;
                try {
                    ArrayList<HashMap<String, String>> name = ApplicationTest.getInstance().getSelectedPartName();
                    ArrayList<HashMap<String, String>> color = ApplicationTest.getInstance().getSelectedColor();
                    ArrayList<HashMap<String, String>> part = ApplicationTest.getInstance().getSelectedPartName();
                    ArrayList<HashMap<String, String>> frontback = ApplicationTest.getInstance().getSelectedFrontBack();
                    ArrayList<HashMap<String, String>> leftright = ApplicationTest.getInstance().getSelectedRightLeft();
                    jsonArray = new JsonArray();
                    JsonObject jsonObject;
                    for (int i = 0; i < color.size(); i++) {
                        jsonObject = new JsonObject();
                        String colorSelected = "";
                        if (color.get(i).get("name").equals("mild")) {
                            colorSelected = "1";
                        } else if (color.get(i).get("name").equals("severe")) {
                            colorSelected = "2";
                        } else if (color.get(i).get("name").equals("moderate")) {
                            colorSelected = "2";
                        }
                        jsonObject.addProperty("bpm_color", colorSelected);
                        jsonObject.addProperty("bpm_front_back", frontback.get(i).get("bpm_front_back"));
                        jsonObject.addProperty("bpm_right_left", leftright.get(i).get("bpm_right_left"));
                        jsonObject.addProperty("bpm_body_name", name.get(i).get("name"));
                        jsonArray.add(jsonObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                bodyMapViewModel.saveBodyMap(intensity_id, jsonArray.toString(), MaleBodyActivity.this).observe(this, new Observer<BodyMapResponse>() {
                    @Override
                    public void onChanged(@Nullable BodyMapResponse bodyMapResponse) {
                        try {
                            if (bodyMapResponse.isStatus()) {
                                Context context;
                                //myDialog = new Dialog(MaleBodyActivity.this);
                                //ShowPopupForSaved();
                                Intent n = new Intent(MaleBodyActivity.this, CongratulationsScreenActivity.class);
                                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                n.putExtra("msg", "You just completed a pain report");
                                startActivity(n);
                            } else {
                                Toast.makeText(MaleBodyActivity.this, "" + bodyMapResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                });


                break;
            case R.id.img_undo:
                if (ApplicationTest.getInstance().getSelectedSevere().size() > 0) {
                    ArrayList<HashMap<String, String>> idStringHashMap = ApplicationTest.getInstance().getSelectedSevere();
                    undo(idStringHashMap.get(idStringHashMap.size() - 1).get("id"));
                }
                break;
            case R.id.img_severity:
                Context context;
                myDialog = new Dialog(MaleBodyActivity.this);
                ShowPopup();
                break;
            case R.id.img_erase:
                erase();
                break;
            case R.id.img_clipboard:
                ApplicationTest.getInstance().clipBoardData = new ArrayList<>();
                Intent n = new Intent(MaleBodyActivity.this, ClipBoardActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(n);
                break;
        }

    }


    @OnClick({R.id.img_right_head, R.id.img_left_head, R.id.img_right_face, R.id.img_left_face, R.id.img_right_neck, R.id.img_left_neck, R.id.img_right_chest, R.id.img_left_chest, R.id.img_right_shoulder, R.id.img_left_shoulder, R.id.img_right_arm, R.id.img_left_arm, R.id.img_right_cubital, R.id.img_left_cubital, R.id.img_right_forearm, R.id.img_left_forearm, R.id.img_right_wrist, R.id.img_left_wrist, R.id.img_right_palm, R.id.img_left_palm, R.id.img_right_stomach, R.id.img_left_stomach, R.id.img_right_hip, R.id.img_left_hip, R.id.img_right_pelvis, R.id.img_left_pelvis, R.id.img_right_thigh, R.id.img_left_thigh, R.id.img_left_knee, R.id.img_right_knee, R.id.img_right_crus, R.id.img_left_crus, R.id.img_right_tarsus, R.id.img_left_tarsus, R.id.img_right_foot, R.id.img_left_foot})
    public void onFrontMaleViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_right_head:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHead");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_head_male_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHead.setImageResource(R.drawable.right_head_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHead.setImageResource(R.drawable.right_head_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHead.setImageResource(R.drawable.right_head_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }

                break;
            case R.id.img_left_head:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHead");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_head_male_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHead.setImageResource(R.drawable.left_head_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHead.setImageResource(R.drawable.left_head_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHead.setImageResource(R.drawable.left_head_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_face:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFace");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_face_male_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightFace.setImageResource(R.drawable.right_face_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightFace.setImageResource(R.drawable.right_face_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightFace.setImageResource(R.drawable.right_face_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_face:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_face_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFace");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftFace.setImageResource(R.drawable.left_face_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftFace.setImageResource(R.drawable.left_face_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftFace.setImageResource(R.drawable.left_face_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_neck:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_neck_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeck");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightNeck.setImageResource(R.drawable.right_neck_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightNeck.setImageResource(R.drawable.right_neck_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightNeck.setImageResource(R.drawable.right_neck_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_neck:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_neck_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeck");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftNeck.setImageResource(R.drawable.left_neck_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftNeck.setImageResource(R.drawable.left_neck_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftNeck.setImageResource(R.drawable.left_neck_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_chest:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_chest_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightChest");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightChest.setImageResource(R.drawable.right_chest_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightChest.setImageResource(R.drawable.right_chest_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightChest.setImageResource(R.drawable.right_chest_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_chest:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_chest_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftChest");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftChest.setImageResource(R.drawable.left_chest_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftChest.setImageResource(R.drawable.left_chest_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftChest.setImageResource(R.drawable.left_chest_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_shoulder:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_shoulder_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulder");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightShoulder.setImageResource(R.drawable.right_shoulder_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightShoulder.setImageResource(R.drawable.right_shoulder_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightShoulder.setImageResource(R.drawable.right_shoulder_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_shoulder:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_shoulder_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulder");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftShoulder.setImageResource(R.drawable.left_shoulder_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftShoulder.setImageResource(R.drawable.left_shoulder_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftShoulder.setImageResource(R.drawable.left_shoulder_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_arm:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_arm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightArm.setImageResource(R.drawable.right_arm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightArm.setImageResource(R.drawable.right_arm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightArm.setImageResource(R.drawable.right_arm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_arm:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_arm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftArm.setImageResource(R.drawable.left_arm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftArm.setImageResource(R.drawable.left_arm_male_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftArm.setImageResource(R.drawable.left_arm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_cubital:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_cubital_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubital");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCubital.setImageResource(R.drawable.right_cubital_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCubital.setImageResource(R.drawable.right_cubital_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCubital.setImageResource(R.drawable.right_cubital_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_cubital:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_cubital_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubital");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCubital.setImageResource(R.drawable.left_cubital_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCubital.setImageResource(R.drawable.left_cubital_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCubital.setImageResource(R.drawable.left_cubital_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_forearm:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_forarm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightForearm.setImageResource(R.drawable.right_forearm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightForearm.setImageResource(R.drawable.right_forearm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightForearm.setImageResource(R.drawable.right_forearm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_forearm:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_forarm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftForearm.setImageResource(R.drawable.left_forearm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftForearm.setImageResource(R.drawable.left_forearm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftForearm.setImageResource(R.drawable.left_forearm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_wrist:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_wrist_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWrist");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightWrist.setImageResource(R.drawable.right_wrist_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightWrist.setImageResource(R.drawable.right_wrist_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightWrist.setImageResource(R.drawable.right_wrist_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_wrist:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_wrist_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWrist");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftWrist.setImageResource(R.drawable.left_wrist_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftWrist.setImageResource(R.drawable.left_wrist_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftWrist.setImageResource(R.drawable.left_wrist_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_palm:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_palm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightPalm.setImageResource(R.drawable.right_palm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightPalm.setImageResource(R.drawable.right_palm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightPalm.setImageResource(R.drawable.right_palm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_palm:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_palm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftPalm.setImageResource(R.drawable.left_palm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftPalm.setImageResource(R.drawable.left_palm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftPalm.setImageResource(R.drawable.left_palm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_stomach:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_stomatch_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightStomach");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightStomach.setImageResource(R.drawable.right_stomach_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightStomach.setImageResource(R.drawable.right_stomach_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightStomach.setImageResource(R.drawable.right_stomach_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_stomach:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_stomatch_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftStomach");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftStomach.setImageResource(R.drawable.left_stomach_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftStomach.setImageResource(R.drawable.left_stomach_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftStomach.setImageResource(R.drawable.left_stomach_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_hip:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHip");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHip.setImageResource(R.drawable.right_hip_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHip.setImageResource(R.drawable.right_hip_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHip.setImageResource(R.drawable.right_hip_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_hip:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHip");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHip.setImageResource(R.drawable.left_hip_male_mild_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHip.setImageResource(R.drawable.left_hip_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHip.setImageResource(R.drawable.left_hip_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_pelvis:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_pelvis_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPelvis");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightPelvis.setImageResource(R.drawable.right_pelvis_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightPelvis.setImageResource(R.drawable.right_pelvis_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightPelvis.setImageResource(R.drawable.right_pelvis_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_pelvis:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_pelvis_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPelvis");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftPelvis.setImageResource(R.drawable.left_pelvis_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftPelvis.setImageResource(R.drawable.left_pelvis_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftPelvis.setImageResource(R.drawable.left_pelvis_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_thigh:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_thigh_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThigh");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightThigh.setImageResource(R.drawable.right_thigh_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightThigh.setImageResource(R.drawable.right_thigh_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightThigh.setImageResource(R.drawable.right_thigh_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_thigh:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_thigh_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThigh");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftThigh.setImageResource(R.drawable.left_thigh_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftThigh.setImageResource(R.drawable.left_thigh_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftThigh.setImageResource(R.drawable.left_thigh_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_knee:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_knee_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKnee");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftKnee.setImageResource(R.drawable.left_knee_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftKnee.setImageResource(R.drawable.left_knee_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftKnee.setImageResource(R.drawable.left_knee_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_knee:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_knee_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKnee");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightKnee.setImageResource(R.drawable.right_knee_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightKnee.setImageResource(R.drawable.right_knee_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightKnee.setImageResource(R.drawable.right_knee_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_crus:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_crus_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrus");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCrus.setImageResource(R.drawable.right_crus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCrus.setImageResource(R.drawable.right_crus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCrus.setImageResource(R.drawable.right_crus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_crus:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_crus_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrus");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCrus.setImageResource(R.drawable.left_crus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCrus.setImageResource(R.drawable.left_crus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCrus.setImageResource(R.drawable.left_crus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_tarsus:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_tarsus_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsus");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightTarsus.setImageResource(R.drawable.right_tarsus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightTarsus.setImageResource(R.drawable.right_tarsus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightTarsus.setImageResource(R.drawable.right_tarsus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_tarsus:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_tarsus_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsus");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftTarsus.setImageResource(R.drawable.left_tarsus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftTarsus.setImageResource(R.drawable.left_tarsus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftTarsus.setImageResource(R.drawable.left_tarsus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_foot:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_foot_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFoot");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightFoot.setImageResource(R.drawable.right_foot_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightFoot.setImageResource(R.drawable.right_foot_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightFoot.setImageResource(R.drawable.right_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_foot:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_foot_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFoot");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftFoot.setImageResource(R.drawable.left_foot_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftFoot.setImageResource(R.drawable.left_foot_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftFoot.setImageResource(R.drawable.left_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
        }
    }

    @OnClick({R.id.img_left_head_back, R.id.img_right_head_back, R.id.img_left_head_2_back, R.id.img_right_head_2_back, R.id.img_left_neck_back, R.id.img_right_neck_back, R.id.img_left_back_top_back, R.id.img_right_back_top_back, R.id.img_left_back_middle_back, R.id.img_right_back_middle_back, R.id.img_left_shoulder_back, R.id.img_right_shoulder_back, R.id.img_right_arm_back, R.id.img_left_arm_back, R.id.img_right_cubital_back, R.id.img_left_cubital_back, R.id.img_right_forearm_back, R.id.img_left_forearm_back, R.id.img_right_wris_backt, R.id.img_left_wrist_back, R.id.img_right_palm_back, R.id.img_left_palm_back, R.id.img_left_back_lower_back, R.id.img_right_back_lower_back, R.id.img_left_hip_back, R.id.img_right_hip_back, R.id.img_left_hip2, R.id.img_right_hip2_back, R.id.img_left_thigh_back, R.id.img_right_thigh_back, R.id.img_right_knee_back, R.id.img_left_knee_back, R.id.img_left_crus_back, R.id.img_right_crus_back, R.id.img_right_tarsus_back, R.id.img_left_tarsus_back, R.id.img_right_foot_back, R.id.img_left_foot_back})
    public void onMaleBackViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left_head_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_head_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHeadBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHeadBack.setImageResource(R.drawable.left_head_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHeadBack.setImageResource(R.drawable.left_head_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHeadBack.setImageResource(R.drawable.left_head_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_head_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_head_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHeadBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHeadBack.setImageResource(R.drawable.right_head_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHeadBack.setImageResource(R.drawable.right_head_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHeadBack.setImageResource(R.drawable.right_head_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_head_2_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_face_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHead2Back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHead2Back.setImageResource(R.drawable.left_head_2_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHead2Back.setImageResource(R.drawable.left_head_2_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHead2Back.setImageResource(R.drawable.left_head_2_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_head_2_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_face_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHead2Back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHead2Back.setImageResource(R.drawable.right_head_2_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHead2Back.setImageResource(R.drawable.right_head_2_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHead2Back.setImageResource(R.drawable.right_head_2_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_neck_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_neck_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeckBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftNeckBack.setImageResource(R.drawable.left_neck_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftNeckBack.setImageResource(R.drawable.left_neck_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftNeckBack.setImageResource(R.drawable.left_neck_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_neck_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_neck_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeckBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightNeckBack.setImageResource(R.drawable.right_neck_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightNeckBack.setImageResource(R.drawable.right_neck_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightNeckBack.setImageResource(R.drawable.right_neck_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_back_top_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_top_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackTopBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftBackTopBack.setImageResource(R.drawable.left_back_top_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftBackTopBack.setImageResource(R.drawable.left_back_top__men_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftBackTopBack.setImageResource(R.drawable.left_back_top_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_back_top_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_top_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackTopBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightBackTopBack.setImageResource(R.drawable.right_back_top_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightBackTopBack.setImageResource(R.drawable.right_back_top_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightBackTopBack.setImageResource(R.drawable.right_back_top_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_back_middle_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_middle_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackMiddleBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftBackMiddleBack.setImageResource(R.drawable.left_back_middle_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftBackMiddleBack.setImageResource(R.drawable.left_back_middle_men_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftBackMiddleBack.setImageResource(R.drawable.left_back_middle_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_back_middle_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_middle_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackMiddleBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightBackMiddleBack.setImageResource(R.drawable.right_back_middle_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightBackMiddleBack.setImageResource(R.drawable.right_back_middle_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightBackMiddleBack.setImageResource(R.drawable.right_back_middle_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_shoulder_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_shoulder_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulderBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftShoulderBack.setImageResource(R.drawable.left_shoulder_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftShoulderBack.setImageResource(R.drawable.left_shoulder_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftShoulderBack.setImageResource(R.drawable.left_shoulder_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_shoulder_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_shoulder_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulderBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightShoulderBack.setImageResource(R.drawable.right_shoulder_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightShoulderBack.setImageResource(R.drawable.right_shoulder_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightShoulderBack.setImageResource(R.drawable.right_shoulder_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_arm_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_arm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightArmBack.setImageResource(R.drawable.right_arm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightArmBack.setImageResource(R.drawable.right_arm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightArmBack.setImageResource(R.drawable.right_arm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_arm_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_arm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftArmBack.setImageResource(R.drawable.left_arm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftArmBack.setImageResource(R.drawable.left_arm_male_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftArmBack.setImageResource(R.drawable.left_arm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_cubital_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_cubital_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubitalBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCubitalBack.setImageResource(R.drawable.left_cubital_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCubitalBack.setImageResource(R.drawable.left_cubital_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCubitalBack.setImageResource(R.drawable.left_cubital_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_cubital_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_cubital_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubitalBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCubitalBack.setImageResource(R.drawable.right_cubital_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCubitalBack.setImageResource(R.drawable.right_cubital_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCubitalBack.setImageResource(R.drawable.right_cubital_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_forearm_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_forearm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightForearmBack.setImageResource(R.drawable.left_forearm_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightForearmBack.setImageResource(R.drawable.left_forearm_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightForearmBack.setImageResource(R.drawable.left_forearm_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_forearm_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_forearm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftForearmBack.setImageResource(R.drawable.right_forearm_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftForearmBack.setImageResource(R.drawable.right_forearm_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftForearmBack.setImageResource(R.drawable.right_forearm_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_wris_backt:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_wrist_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWrisBackt");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightWrisBackt.setImageResource(R.drawable.left_wrist_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightWrisBackt.setImageResource(R.drawable.left_wrist_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightWrisBackt.setImageResource(R.drawable.left_wrist_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_wrist_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_wrist_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWristBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftWristBack.setImageResource(R.drawable.right_wrist_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftWristBack.setImageResource(R.drawable.right_wrist_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftWristBack.setImageResource(R.drawable.right_wrist_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_palm_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_palm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightPalmBack.setImageResource(R.drawable.left_palm_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightPalmBack.setImageResource(R.drawable.left_palm_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightPalmBack.setImageResource(R.drawable.left_palm_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_palm_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_palm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftPalmBack.setImageResource(R.drawable.right_palm_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftPalmBack.setImageResource(R.drawable.right_palm_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftPalmBack.setImageResource(R.drawable.right_palm_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_back_lower_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_lower_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackLowerBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftBackLowerBack.setImageResource(R.drawable.left_back_lower_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftBackLowerBack.setImageResource(R.drawable.left_back_lower_men_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftBackLowerBack.setImageResource(R.drawable.left_back_lower_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_back_lower_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_lower_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackLowerBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightBackLowerBack.setImageResource(R.drawable.right_back_lower_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightBackLowerBack.setImageResource(R.drawable.right_back_lower_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightBackLowerBack.setImageResource(R.drawable.right_back_lower_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_hip_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHipBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHipBack.setImageResource(R.drawable.left_hip_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHipBack.setImageResource(R.drawable.left_hip_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHipBack.setImageResource(R.drawable.left_hip_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_hip_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHipBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHipBack.setImageResource(R.drawable.right_hip_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHipBack.setImageResource(R.drawable.right_hip_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHipBack.setImageResource(R.drawable.right_hip_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_hip2:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip2_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHip2");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftHip2.setImageResource(R.drawable.left_hip2_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftHip2.setImageResource(R.drawable.left_hip2_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftHip2.setImageResource(R.drawable.left_hip2_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_hip2_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip2_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHip2Back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightHip2Back.setImageResource(R.drawable.right_hip2_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightHip2Back.setImageResource(R.drawable.right_hip2_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightHip2Back.setImageResource(R.drawable.right_hip2_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_thigh_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_thigh_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThighBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftThighBack.setImageResource(R.drawable.left_thigh_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftThighBack.setImageResource(R.drawable.left_thigh_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftThighBack.setImageResource(R.drawable.left_thigh_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_thigh_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_thigh_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThighBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightThighBack.setImageResource(R.drawable.right_thigh_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightThighBack.setImageResource(R.drawable.right_thigh_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightThighBack.setImageResource(R.drawable.right_thigh_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_knee_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_knee_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKneeBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightKneeBack.setImageResource(R.drawable.right_knee_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightKneeBack.setImageResource(R.drawable.right_knee_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightKneeBack.setImageResource(R.drawable.right_knee_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_knee_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_knee_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKneeBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftKneeBack.setImageResource(R.drawable.left_knee_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftKneeBack.setImageResource(R.drawable.left_knee_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftKneeBack.setImageResource(R.drawable.left_knee_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_crus_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_crus_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrusBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftCrusBack.setImageResource(R.drawable.left_crus_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftCrusBack.setImageResource(R.drawable.left_crus_men_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftCrusBack.setImageResource(R.drawable.left_crus_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_crus_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_crus_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrusBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightCrusBack.setImageResource(R.drawable.right_crus_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightCrusBack.setImageResource(R.drawable.right_crus_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightCrusBack.setImageResource(R.drawable.right_crus_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_tarsus_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_tarsus_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsusBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightTarsusBack.setImageResource(R.drawable.right_tarsus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightTarsusBack.setImageResource(R.drawable.right_tarsus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightTarsusBack.setImageResource(R.drawable.right_tarsus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_tarsus_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_tarsus_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsusBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftTarsusBack.setImageResource(R.drawable.left_tarsus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftTarsusBack.setImageResource(R.drawable.left_tarsus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftTarsusBack.setImageResource(R.drawable.left_tarsus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_right_foot_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_foot_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFootBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgRightFootBack.setImageResource(R.drawable.right_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgRightFootBack.setImageResource(R.drawable.right_foot_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgRightFootBack.setImageResource(R.drawable.right_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
            case R.id.img_left_foot_back:
                selectColour();
                if (!ApplicationTest.getInstance().getSelectedSeverity().equals("")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_foot_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFootBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                        imgLeftFootBack.setImageResource(R.drawable.left_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                        imgLeftFootBack.setImageResource(R.drawable.left_foot_back);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                        imgLeftFootBack.setImageResource(R.drawable.left_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                break;
        }
    }


    public void undo(String id) {
        if (ApplicationTest.getInstance().getSelectedSevere().size() > 0) {
            ApplicationTest.getInstance().getSelectedSevere().remove(ApplicationTest.getInstance().getSelectedSevere().size() - 1);
            ApplicationTest.getInstance().getSelectedColor().remove(ApplicationTest.getInstance().getSelectedColor().size() - 1);
            ApplicationTest.getInstance().getSelectedPartName().remove(ApplicationTest.getInstance().getSelectedPartName().size() - 1);
            ApplicationTest.getInstance().getSelectedRightLeft().remove(ApplicationTest.getInstance().getSelectedRightLeft().size() - 1);
            ApplicationTest.getInstance().getSelectedFrontBack().remove(ApplicationTest.getInstance().getSelectedFrontBack().size() - 1);
            if (id.equals("imgRightHead")) {
                imgRightHead.setImageResource(R.drawable.right_head_white);
            }
            if (id.equals("imgLeftHead")) {
                imgLeftHead.setImageResource(R.drawable.left_head_white);
            }
            if (id.equals("imgRightFace")) {
                imgRightFace.setImageResource(R.drawable.right_face_white);
            }
            if (id.equals("imgLeftFace")) {
                imgLeftFace.setImageResource(R.drawable.left_face_white);
            }
            if (id.equals("imgRightNeck")) {
                imgRightNeck.setImageResource(R.drawable.right_neck_white);
            }
            if (id.equals("imgLeftNeck")) {
                imgLeftNeck.setImageResource(R.drawable.left_neck_white);
            }
            if (id.equals("imgRightChest")) {
                imgRightChest.setImageResource(R.drawable.right_chest_white);
            }
            if (id.equals("imgLeftChest")) {
                imgLeftChest.setImageResource(R.drawable.left_chest_white);
            }
            if (id.equals("imgRightShoulder")) {
                imgRightShoulder.setImageResource(R.drawable.right_shoulder_white);
            }
            if (id.equals("imgLeftShoulder")) {
                imgLeftShoulder.setImageResource(R.drawable.left_shoulder_white);
            }
            if (id.equals("imgRightArm")) {
                imgRightArm.setImageResource(R.drawable.right_arm_white);
            }
            if (id.equals("imgLeftArm")) {
                imgLeftArm.setImageResource(R.drawable.left_arm_white);
            }
            if (id.equals("imgRightCubital")) {
                imgRightCubital.setImageResource(R.drawable.right_cubital_white);
            }
            if (id.equals("imgLeftCubital")) {
                imgLeftCubital.setImageResource(R.drawable.left_cubital_white);
            }
            if (id.equals("imgRightForearm")) {
                imgRightForearm.setImageResource(R.drawable.right_forearm_white);
            }
            if (id.equals("imgLeftForearm")) {
                imgLeftForearm.setImageResource(R.drawable.left_forearm_white);
            }
            if (id.equals("imgRightWrist")) {
                imgRightWrist.setImageResource(R.drawable.right_wrist_white);
            }
            if (id.equals("imgLeftWrist")) {
                imgLeftWrist.setImageResource(R.drawable.left_wrist_white);
            }
            if (id.equals("imgRightPalm")) {
                imgRightPalm.setImageResource(R.drawable.right_palm_white);
            }
            if (id.equals("imgLeftPalm")) {
                imgLeftPalm.setImageResource(R.drawable.left_palm_white);
            }
            if (id.equals("imgRightStomach")) {
                imgRightStomach.setImageResource(R.drawable.right_stomach_white);
            }
            if (id.equals("imgLeftStomach")) {
                imgLeftStomach.setImageResource(R.drawable.left_stomach_white);
            }
            if (id.equals("imgRightHip")) {
                imgRightHip.setImageResource(R.drawable.right_hip_white);
            }
            if (id.equals("imgLeftHip")) {
                imgLeftHip.setImageResource(R.drawable.left_hip_white);
            }
            if (id.equals("imgRightPelvis")) {
                imgRightPelvis.setImageResource(R.drawable.right_pelvis_white);
            }
            if (id.equals("imgLeftPelvis")) {
                imgLeftPelvis.setImageResource(R.drawable.left_pelvis_white);
            }
            if (id.equals("imgRightThigh")) {
                imgRightThigh.setImageResource(R.drawable.right_thigh_white);
            }
            if (id.equals("imgLeftThigh")) {
                imgLeftThigh.setImageResource(R.drawable.left_thigh_white);
            }
            if (id.equals("imgLeftKnee")) {
                imgLeftKnee.setImageResource(R.drawable.left_knee_white);
            }
            if (id.equals("imgRightKnee")) {
                imgRightKnee.setImageResource(R.drawable.right_knee_white);
            }
            if (id.equals("imgRightCrus")) {
                imgRightCrus.setImageResource(R.drawable.right_crus_white);
            }
            if (id.equals("imgLeftCrus")) {
                imgLeftCrus.setImageResource(R.drawable.left_crus_white);
            }
            if (id.equals("imgRightTarsus")) {
                imgRightTarsus.setImageResource(R.drawable.right_tarsus_white);
            }
            if (id.equals("imgLeftTarsus")) {
                imgLeftTarsus.setImageResource(R.drawable.left_tarsus_white);
            }
            if (id.equals("imgRightFoot")) {
                imgRightFoot.setImageResource(R.drawable.right_foot_white);
            }
            if (id.equals("imgLeftFoot")) {
                imgLeftFoot.setImageResource(R.drawable.left_foot_white);
            }


            if (id.equals("imgLeftHeadBack")) {
                imgLeftHeadBack.setImageResource(R.drawable.left_head_men_back_white);
            }
            if (id.equals("imgRightHeadBack")) {
                imgRightHeadBack.setImageResource(R.drawable.right_head_men_back_white);
            }
            if (id.equals("imgLeftHead2Back")) {
                imgLeftHead2Back.setImageResource(R.drawable.left_head_2_men_back_white);
            }
            if (id.equals("imgRightHead2Back")) {
                imgRightHead2Back.setImageResource(R.drawable.right_head_2_men_back_white);
            }
            if (id.equals("imgLeftNeckBack")) {
                imgLeftNeckBack.setImageResource(R.drawable.left_neck_men_back_white);
            }
            if (id.equals("imgRightNeckBack")) {
                imgRightNeckBack.setImageResource(R.drawable.right_neck_men_back_white);
            }
            if (id.equals("imgLeftBackTopBack")) {
                imgLeftBackTopBack.setImageResource(R.drawable.left_back_top_men_back_white);
            }
            if (id.equals("imgRightBackTopBack")) {
                imgRightBackTopBack.setImageResource(R.drawable.right_back_top_men_back_white);
            }
            if (id.equals("imgLeftBackMiddleBack")) {
                imgLeftBackMiddleBack.setImageResource(R.drawable.left_back_middle_men_back_white);
            }
            if (id.equals("imgRightBackMiddleBack")) {
                imgRightBackMiddleBack.setImageResource(R.drawable.right_back_middle_men_back_white);
            }
            if (id.equals("imgLeftShoulderBack")) {
                imgLeftShoulderBack.setImageResource(R.drawable.left_shoulder_men_back_white);
            }
            if (id.equals("imgRightShoulderBack")) {
                imgRightShoulderBack.setImageResource(R.drawable.right_shoulder_men_back_white);
            }
            if (id.equals("imgRightArmBack")) {
                imgRightArmBack.setImageResource(R.drawable.right_arm_white);
            }
            if (id.equals("imgLeftArmBack")) {
                imgLeftArmBack.setImageResource(R.drawable.left_arm_white);
            }
            if (id.equals("imgRightCubitalBack")) {
                imgRightCubitalBack.setImageResource(R.drawable.left_cubital_men_back_white);
            }
            if (id.equals("imgLeftCubitalBack")) {
                imgLeftCubitalBack.setImageResource(R.drawable.right_cubital_men_back_white);
            }
            if (id.equals("imgRightForearmBack")) {
                imgRightForearmBack.setImageResource(R.drawable.left_forearm_men_back_white);
            }
            if (id.equals("imgLeftForearmBack")) {
                imgLeftForearmBack.setImageResource(R.drawable.right_forearm_men_back_white);
            }
            if (id.equals("imgRightWrisBackt")) {
                imgRightWrisBackt.setImageResource(R.drawable.left_wrist_men_back_white);
            }
            if (id.equals("imgLeftWristBack")) {
                imgLeftWristBack.setImageResource(R.drawable.right_wrist_men_back_white);
            }
            if (id.equals("imgRightPalmBack")) {
                imgRightPalmBack.setImageResource(R.drawable.left_palm_men_back_white);
            }
            if (id.equals("imgLeftPalmBack")) {
                imgLeftPalmBack.setImageResource(R.drawable.right_palm_men_back_white);
            }
            if (id.equals("imgLeftBackLowerBack")) {
                imgLeftBackLowerBack.setImageResource(R.drawable.left_back_lower_men_back_white);
            }
            if (id.equals("imgRightBackLowerBack")) {
                imgRightBackLowerBack.setImageResource(R.drawable.right_back_lower_men_back_white);
            }
            if (id.equals("imgLeftHipBack")) {
                imgLeftHipBack.setImageResource(R.drawable.left_hip_men_back_white);
            }
            if (id.equals("imgRightHipBack")) {
                imgRightHipBack.setImageResource(R.drawable.right_hip_men_back_white);
            }
            if (id.equals("imgLeftHip2")) {
                imgLeftHip2.setImageResource(R.drawable.left_hip2_men_back_white);
            }
            if (id.equals("imgRightHip2Back")) {
                imgRightHip2Back.setImageResource(R.drawable.right_hip2_men_back_white);
            }
            if (id.equals("imgLeftThighBack")) {
                imgLeftThighBack.setImageResource(R.drawable.left_thigh_men_back_white);
            }
            if (id.equals("imgRightThighBack")) {
                imgRightThighBack.setImageResource(R.drawable.right_thigh_men_back_white);
            }
            if (id.equals("imgRightKneeBack")) {
                imgRightKneeBack.setImageResource(R.drawable.right_knee_men_back_white);
            }
            if (id.equals("imgLeftKneeBack")) {
                imgLeftKneeBack.setImageResource(R.drawable.left_knee_men_back_white);
            }
            if (id.equals("imgLeftCrusBack")) {
                imgLeftCrusBack.setImageResource(R.drawable.left_crus_men_back_white);
            }
            if (id.equals("imgRightCrusBack")) {
                imgRightCrusBack.setImageResource(R.drawable.right_crus_men_back_white);
            }
            if (id.equals("imgRightTarsusBack")) {
                imgRightTarsusBack.setImageResource(R.drawable.right_tarsus_men_back_white);
            }
            if (id.equals("imgLeftTarsusBack")) {
                imgLeftTarsusBack.setImageResource(R.drawable.left_tarsus_men_back_white);
            }
            if (id.equals("imgRightFootBack")) {
                imgRightFootBack.setImageResource(R.drawable.right_foot_white);
            }
            if (id.equals("imgLeftFootBack")) {
                imgLeftFootBack.setImageResource(R.drawable.left_foot_white);
            }

        }
    }


    public void erase() {
        if (ApplicationTest.getInstance().getSelectedSevere().size() > 0) {
            ApplicationTest.getInstance().getSelectedSevere().clear();
            ApplicationTest.getInstance().getSelectedColor().clear();
            ApplicationTest.getInstance().getSelectedPartName().clear();
            ApplicationTest.getInstance().getSelectedRightLeft().clear();
            ApplicationTest.getInstance().getSelectedFrontBack().clear();
            imgRightHead.setImageResource(R.drawable.right_head_white);
            imgLeftHead.setImageResource(R.drawable.left_head_white);
            imgRightFace.setImageResource(R.drawable.right_face_white);
            imgLeftFace.setImageResource(R.drawable.left_face_white);
            imgRightNeck.setImageResource(R.drawable.right_neck_white);
            imgLeftNeck.setImageResource(R.drawable.left_neck_white);
            imgRightChest.setImageResource(R.drawable.right_chest_white);
            imgLeftChest.setImageResource(R.drawable.left_chest_white);
            imgRightShoulder.setImageResource(R.drawable.right_shoulder_white);
            imgLeftShoulder.setImageResource(R.drawable.left_shoulder_white);
            imgRightArm.setImageResource(R.drawable.right_arm_white);
            imgLeftArm.setImageResource(R.drawable.left_arm_white);
            imgRightCubital.setImageResource(R.drawable.right_cubital_white);
            imgLeftCubital.setImageResource(R.drawable.left_cubital_white);
            imgRightForearm.setImageResource(R.drawable.right_forearm_white);
            imgLeftForearm.setImageResource(R.drawable.left_forearm_white);
            imgRightWrist.setImageResource(R.drawable.right_wrist_white);
            imgLeftWrist.setImageResource(R.drawable.left_wrist_white);
            imgRightPalm.setImageResource(R.drawable.right_palm_white);
            imgLeftPalm.setImageResource(R.drawable.left_palm_white);
            imgRightStomach.setImageResource(R.drawable.right_stomach_white);
            imgLeftStomach.setImageResource(R.drawable.left_stomach_white);
            imgRightHip.setImageResource(R.drawable.right_hip_white);
            imgLeftHip.setImageResource(R.drawable.left_hip_white);
            imgRightPelvis.setImageResource(R.drawable.right_pelvis_white);
            imgLeftPelvis.setImageResource(R.drawable.left_pelvis_white);
            imgRightThigh.setImageResource(R.drawable.right_thigh_white);
            imgLeftThigh.setImageResource(R.drawable.left_thigh_white);
            imgLeftKnee.setImageResource(R.drawable.left_knee_white);
            imgRightKnee.setImageResource(R.drawable.right_knee_white);
            imgRightCrus.setImageResource(R.drawable.right_crus_white);
            imgLeftCrus.setImageResource(R.drawable.left_crus_white);
            imgRightTarsus.setImageResource(R.drawable.right_tarsus_white);
            imgLeftTarsus.setImageResource(R.drawable.left_tarsus_white);
            imgRightFoot.setImageResource(R.drawable.right_foot_white);
            imgLeftFoot.setImageResource(R.drawable.left_foot_white);


            imgLeftHeadBack.setImageResource(R.drawable.left_head_men_back_white);
            imgRightHeadBack.setImageResource(R.drawable.right_head_men_back_white);
            imgLeftHead2Back.setImageResource(R.drawable.left_head_2_men_back_white);
            imgRightHead2Back.setImageResource(R.drawable.right_head_2_men_back_white);
            imgLeftNeckBack.setImageResource(R.drawable.left_neck_men_back_white);
            imgRightNeckBack.setImageResource(R.drawable.right_neck_men_back_white);
            imgLeftBackTopBack.setImageResource(R.drawable.left_back_top_men_back_white);
            imgRightBackTopBack.setImageResource(R.drawable.right_back_top_men_back_white);
            imgLeftBackMiddleBack.setImageResource(R.drawable.left_back_middle_men_back_white);
            imgRightBackMiddleBack.setImageResource(R.drawable.right_back_middle_men_back_white);
            imgLeftShoulderBack.setImageResource(R.drawable.left_shoulder_men_back_white);
            imgRightShoulderBack.setImageResource(R.drawable.right_shoulder_men_back_white);
            imgRightArmBack.setImageResource(R.drawable.right_arm_white);
            imgLeftArmBack.setImageResource(R.drawable.left_arm_white);
            imgRightCubitalBack.setImageResource(R.drawable.left_cubital_men_back_white);
            imgLeftCubitalBack.setImageResource(R.drawable.right_cubital_men_back_white);
            imgRightForearmBack.setImageResource(R.drawable.left_forearm_men_back_white);
            imgLeftForearmBack.setImageResource(R.drawable.right_forearm_men_back_white);
            imgRightWrisBackt.setImageResource(R.drawable.left_wrist_men_back_white);
            imgLeftWristBack.setImageResource(R.drawable.right_wrist_men_back_white);
            imgRightPalmBack.setImageResource(R.drawable.left_palm_men_back_white);
            imgLeftPalmBack.setImageResource(R.drawable.right_palm_men_back_white);
            imgLeftBackLowerBack.setImageResource(R.drawable.left_back_lower_men_back_white);
            imgRightBackLowerBack.setImageResource(R.drawable.right_back_lower_men_back_white);
            imgLeftHipBack.setImageResource(R.drawable.left_hip_men_back_white);
            imgRightHipBack.setImageResource(R.drawable.right_hip_men_back_white);
            imgLeftHip2.setImageResource(R.drawable.left_hip2_men_back_white);
            imgRightHip2Back.setImageResource(R.drawable.right_hip2_men_back_white);
            imgLeftThighBack.setImageResource(R.drawable.left_thigh_men_back_white);
            imgRightThighBack.setImageResource(R.drawable.right_thigh_men_back_white);
            imgRightKneeBack.setImageResource(R.drawable.right_knee_men_back_white);
            imgLeftKneeBack.setImageResource(R.drawable.left_knee_men_back_white);
            imgLeftCrusBack.setImageResource(R.drawable.left_crus_men_back_white);
            imgRightCrusBack.setImageResource(R.drawable.right_crus_men_back_white);
            imgRightTarsusBack.setImageResource(R.drawable.right_tarsus_men_back_white);
            imgLeftTarsusBack.setImageResource(R.drawable.left_tarsus_men_back_white);
            imgRightFootBack.setImageResource(R.drawable.right_foot_white);
            imgLeftFootBack.setImageResource(R.drawable.left_foot_white);
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
                Intent n = new Intent(MaleBodyActivity.this, VideoIntroActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                n.putExtra("intensity_id", intensity_id);
                startActivity(n);
            }
        });

        myDialog.show();
    }


    private void selectColour() {
        if (tripalBackToExitPressedOnce) {
            tripalBackToExitPressedOnce = false;
            doubleBackToExitPressedOnce = false;
            imgSeverity.setImageResource(R.mipmap.paint_bucket_severe);
            ApplicationTest.getInstance().setSelectedSeverity("severe");
        } else if (doubleBackToExitPressedOnce) {
            tripalBackToExitPressedOnce = true;
            doubleBackToExitPressedOnce = false;
            imgSeverity.setImageResource(R.mipmap.paint_bucket_moderate);
            ApplicationTest.getInstance().setSelectedSeverity("moderate");
        } else {
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

                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_head_male_front")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHead");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_head_male_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightHead.setImageResource(R.drawable.right_head_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightHead.setImageResource(R.drawable.right_head_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightHead.setImageResource(R.drawable.right_head_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_head_male_front")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHead");
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_head_male_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftHead.setImageResource(R.drawable.left_head_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftHead.setImageResource(R.drawable.left_head_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftHead.setImageResource(R.drawable.left_head_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_face_male_front")) {
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFace");
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_face_male_front");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightFace.setImageResource(R.drawable.right_face_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightFace.setImageResource(R.drawable.right_face_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightFace.setImageResource(R.drawable.right_face_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_face_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_face_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFace");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftFace.setImageResource(R.drawable.left_face_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftFace.setImageResource(R.drawable.left_face_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftFace.setImageResource(R.drawable.left_face_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_neck_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_neck_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeck");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightNeck.setImageResource(R.drawable.right_neck_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightNeck.setImageResource(R.drawable.right_neck_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightNeck.setImageResource(R.drawable.right_neck_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_neck_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_neck_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeck");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftNeck.setImageResource(R.drawable.left_neck_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftNeck.setImageResource(R.drawable.left_neck_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftNeck.setImageResource(R.drawable.left_neck_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_chest_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_chest_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightChest");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightChest.setImageResource(R.drawable.right_chest_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightChest.setImageResource(R.drawable.right_chest_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightChest.setImageResource(R.drawable.right_chest_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_chest_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_chest_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftChest");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftChest.setImageResource(R.drawable.left_chest_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftChest.setImageResource(R.drawable.left_chest_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftChest.setImageResource(R.drawable.left_chest_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_shoulder_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_shoulder_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulder");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightShoulder.setImageResource(R.drawable.right_shoulder_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightShoulder.setImageResource(R.drawable.right_shoulder_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightShoulder.setImageResource(R.drawable.right_shoulder_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_shoulder_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_shoulder_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulder");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftShoulder.setImageResource(R.drawable.left_shoulder_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftShoulder.setImageResource(R.drawable.left_shoulder_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftShoulder.setImageResource(R.drawable.left_shoulder_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_arm_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_arm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightArm.setImageResource(R.drawable.right_arm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightArm.setImageResource(R.drawable.right_arm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightArm.setImageResource(R.drawable.right_arm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_arm_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_arm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftArm.setImageResource(R.drawable.left_arm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftArm.setImageResource(R.drawable.left_arm_male_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftArm.setImageResource(R.drawable.left_arm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_cubital_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_cubital_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubital");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightCubital.setImageResource(R.drawable.right_cubital_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightCubital.setImageResource(R.drawable.right_cubital_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightCubital.setImageResource(R.drawable.right_cubital_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_cubital_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_cubital_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubital");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftCubital.setImageResource(R.drawable.left_cubital_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftCubital.setImageResource(R.drawable.left_cubital_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftCubital.setImageResource(R.drawable.left_cubital_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_forarm_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_forarm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightForearm.setImageResource(R.drawable.right_forearm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightForearm.setImageResource(R.drawable.right_forearm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightForearm.setImageResource(R.drawable.right_forearm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_forarm_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_forarm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftForearm.setImageResource(R.drawable.left_forearm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftForearm.setImageResource(R.drawable.left_forearm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftForearm.setImageResource(R.drawable.left_forearm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_wrist_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_wrist_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWrist");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightWrist.setImageResource(R.drawable.right_wrist_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightWrist.setImageResource(R.drawable.right_wrist_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightWrist.setImageResource(R.drawable.right_wrist_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_wrist_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_wrist_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWrist");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftWrist.setImageResource(R.drawable.left_wrist_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftWrist.setImageResource(R.drawable.left_wrist_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftWrist.setImageResource(R.drawable.left_wrist_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_palm_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_palm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightPalm.setImageResource(R.drawable.right_palm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightPalm.setImageResource(R.drawable.right_palm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightPalm.setImageResource(R.drawable.right_palm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_palm_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_palm_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalm");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftPalm.setImageResource(R.drawable.left_palm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftPalm.setImageResource(R.drawable.left_palm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftPalm.setImageResource(R.drawable.left_palm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_stomatch_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_stomatch_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightStomach");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightStomach.setImageResource(R.drawable.right_stomach_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightStomach.setImageResource(R.drawable.right_stomach_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightStomach.setImageResource(R.drawable.right_stomach_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_stomatch_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_stomatch_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftStomach");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftStomach.setImageResource(R.drawable.left_stomach_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftStomach.setImageResource(R.drawable.left_stomach_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftStomach.setImageResource(R.drawable.left_stomach_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_hip_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHip");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightHip.setImageResource(R.drawable.right_hip_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightHip.setImageResource(R.drawable.right_hip_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightHip.setImageResource(R.drawable.right_hip_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_hip_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHip");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftHip.setImageResource(R.drawable.left_hip_male_mild_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftHip.setImageResource(R.drawable.left_hip_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftHip.setImageResource(R.drawable.left_hip_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_pelvis_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_pelvis_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPelvis");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightPelvis.setImageResource(R.drawable.right_pelvis_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightPelvis.setImageResource(R.drawable.right_pelvis_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightPelvis.setImageResource(R.drawable.right_pelvis_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_pelvis_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_pelvis_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPelvis");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftPelvis.setImageResource(R.drawable.left_pelvis_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftPelvis.setImageResource(R.drawable.left_pelvis_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftPelvis.setImageResource(R.drawable.left_pelvis_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_thigh_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_thigh_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThigh");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightThigh.setImageResource(R.drawable.right_thigh_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightThigh.setImageResource(R.drawable.right_thigh_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightThigh.setImageResource(R.drawable.right_thigh_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_thigh_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_thigh_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThigh");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftThigh.setImageResource(R.drawable.left_thigh_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftThigh.setImageResource(R.drawable.left_thigh_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftThigh.setImageResource(R.drawable.left_thigh_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_knee_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_knee_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKnee");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftKnee.setImageResource(R.drawable.left_knee_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftKnee.setImageResource(R.drawable.left_knee_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftKnee.setImageResource(R.drawable.left_knee_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_knee_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_knee_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKnee");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightKnee.setImageResource(R.drawable.right_knee_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightKnee.setImageResource(R.drawable.right_knee_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightKnee.setImageResource(R.drawable.right_knee_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_crus_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_crus_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrus");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightCrus.setImageResource(R.drawable.right_crus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightCrus.setImageResource(R.drawable.right_crus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightCrus.setImageResource(R.drawable.right_crus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_crus_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_crus_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrus");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftCrus.setImageResource(R.drawable.left_crus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftCrus.setImageResource(R.drawable.left_crus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftCrus.setImageResource(R.drawable.left_crus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_tarsus_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_tarsus_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsus");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightTarsus.setImageResource(R.drawable.right_tarsus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightTarsus.setImageResource(R.drawable.right_tarsus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightTarsus.setImageResource(R.drawable.right_tarsus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_tarsus_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_tarsus_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsus");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftTarsus.setImageResource(R.drawable.left_tarsus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftTarsus.setImageResource(R.drawable.left_tarsus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftTarsus.setImageResource(R.drawable.left_tarsus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_foot_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_foot_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFoot");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightFoot.setImageResource(R.drawable.right_foot_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightFoot.setImageResource(R.drawable.right_foot_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightFoot.setImageResource(R.drawable.right_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_foot_male_front")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_foot_male_front");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFoot");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Front");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftFoot.setImageResource(R.drawable.left_foot_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftFoot.setImageResource(R.drawable.left_foot_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftFoot.setImageResource(R.drawable.left_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }


                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_head_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_head_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHeadBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftHeadBack.setImageResource(R.drawable.left_head_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftHeadBack.setImageResource(R.drawable.left_head_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftHeadBack.setImageResource(R.drawable.left_head_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_head_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_head_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHeadBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightHeadBack.setImageResource(R.drawable.right_head_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightHeadBack.setImageResource(R.drawable.right_head_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightHeadBack.setImageResource(R.drawable.right_head_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_face_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_face_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHead2Back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftHead2Back.setImageResource(R.drawable.left_head_2_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftHead2Back.setImageResource(R.drawable.left_head_2_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftHead2Back.setImageResource(R.drawable.left_head_2_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_face_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_face_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHead2Back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightHead2Back.setImageResource(R.drawable.right_head_2_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightHead2Back.setImageResource(R.drawable.right_head_2_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightHead2Back.setImageResource(R.drawable.right_head_2_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_neck_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_neck_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeckBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftNeckBack.setImageResource(R.drawable.left_neck_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftNeckBack.setImageResource(R.drawable.left_neck_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftNeckBack.setImageResource(R.drawable.left_neck_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_neck_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_neck_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeckBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightNeckBack.setImageResource(R.drawable.right_neck_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightNeckBack.setImageResource(R.drawable.right_neck_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightNeckBack.setImageResource(R.drawable.right_neck_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_back_top_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_top_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackTopBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftBackTopBack.setImageResource(R.drawable.left_back_top_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftBackTopBack.setImageResource(R.drawable.left_back_top__men_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftBackTopBack.setImageResource(R.drawable.left_back_top_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_back_top_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_top_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackTopBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightBackTopBack.setImageResource(R.drawable.right_back_top_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightBackTopBack.setImageResource(R.drawable.right_back_top_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightBackTopBack.setImageResource(R.drawable.right_back_top_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_back_middle_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_middle_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackMiddleBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftBackMiddleBack.setImageResource(R.drawable.left_back_middle_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftBackMiddleBack.setImageResource(R.drawable.left_back_middle_men_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftBackMiddleBack.setImageResource(R.drawable.left_back_middle_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_back_middle_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_back_middle_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightBackMiddleBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightBackMiddleBack.setImageResource(R.drawable.right_back_middle_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightBackMiddleBack.setImageResource(R.drawable.right_back_middle_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightBackMiddleBack.setImageResource(R.drawable.right_back_middle_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_shoulder_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_shoulder_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulderBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftShoulderBack.setImageResource(R.drawable.left_shoulder_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftShoulderBack.setImageResource(R.drawable.left_shoulder_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftShoulderBack.setImageResource(R.drawable.left_shoulder_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_shoulder_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_shoulder_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulderBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightShoulderBack.setImageResource(R.drawable.right_shoulder_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightShoulderBack.setImageResource(R.drawable.right_shoulder_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightShoulderBack.setImageResource(R.drawable.right_shoulder_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_arm_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_arm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightArmBack.setImageResource(R.drawable.right_arm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightArmBack.setImageResource(R.drawable.right_arm_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightArmBack.setImageResource(R.drawable.right_arm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_arm_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_arm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftArmBack.setImageResource(R.drawable.left_arm_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftArmBack.setImageResource(R.drawable.left_arm_male_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftArmBack.setImageResource(R.drawable.left_arm_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_cubital_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_cubital_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubitalBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightCubitalBack.setImageResource(R.drawable.left_cubital_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightCubitalBack.setImageResource(R.drawable.left_cubital_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightCubitalBack.setImageResource(R.drawable.left_cubital_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_cubital_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_cubital_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubitalBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftCubitalBack.setImageResource(R.drawable.right_cubital_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftCubitalBack.setImageResource(R.drawable.right_cubital_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftCubitalBack.setImageResource(R.drawable.right_cubital_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_forearm_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_forearm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightForearmBack.setImageResource(R.drawable.left_forearm_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightForearmBack.setImageResource(R.drawable.left_forearm_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightForearmBack.setImageResource(R.drawable.left_forearm_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_forearm_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_forearm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftForearmBack.setImageResource(R.drawable.right_forearm_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftForearmBack.setImageResource(R.drawable.right_forearm_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftForearmBack.setImageResource(R.drawable.right_forearm_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_wrist_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_wrist_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWrisBackt");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightWrisBackt.setImageResource(R.drawable.left_wrist_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightWrisBackt.setImageResource(R.drawable.left_wrist_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightWrisBackt.setImageResource(R.drawable.left_wrist_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_wrist_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_wrist_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWristBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftWristBack.setImageResource(R.drawable.right_wrist_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftWristBack.setImageResource(R.drawable.right_wrist_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftWristBack.setImageResource(R.drawable.right_wrist_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_palm_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_palm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightPalmBack.setImageResource(R.drawable.left_palm_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightPalmBack.setImageResource(R.drawable.left_palm_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightPalmBack.setImageResource(R.drawable.left_palm_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_palm_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_palm_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalmBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftPalmBack.setImageResource(R.drawable.right_palm_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftPalmBack.setImageResource(R.drawable.right_palm_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftPalmBack.setImageResource(R.drawable.right_palm_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_back_lower_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_back_lower_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftBackLowerBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftBackLowerBack.setImageResource(R.drawable.left_back_lower_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftBackLowerBack.setImageResource(R.drawable.left_back_lower_men_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftBackLowerBack.setImageResource(R.drawable.left_back_lower_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_hip_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHipBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftHipBack.setImageResource(R.drawable.left_hip_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftHipBack.setImageResource(R.drawable.left_hip_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftHipBack.setImageResource(R.drawable.left_hip_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_hip_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHipBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightHipBack.setImageResource(R.drawable.right_hip_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightHipBack.setImageResource(R.drawable.right_hip_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightHipBack.setImageResource(R.drawable.right_hip_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_hip2_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_hip2_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHip2");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftHip2.setImageResource(R.drawable.left_hip2_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftHip2.setImageResource(R.drawable.left_hip2_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftHip2.setImageResource(R.drawable.left_hip2_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_hip2_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_hip2_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHip2Back");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightHip2Back.setImageResource(R.drawable.right_hip2_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightHip2Back.setImageResource(R.drawable.right_hip2_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightHip2Back.setImageResource(R.drawable.right_hip2_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_thigh_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_thigh_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThighBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftThighBack.setImageResource(R.drawable.left_thigh_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftThighBack.setImageResource(R.drawable.left_thigh_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftThighBack.setImageResource(R.drawable.left_thigh_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_thigh_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_thigh_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThighBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightThighBack.setImageResource(R.drawable.right_thigh_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightThighBack.setImageResource(R.drawable.right_thigh_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightThighBack.setImageResource(R.drawable.right_thigh_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_knee_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_knee_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKneeBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightKneeBack.setImageResource(R.drawable.right_knee_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightKneeBack.setImageResource(R.drawable.right_knee_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightKneeBack.setImageResource(R.drawable.right_knee_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_knee_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_knee_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKneeBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftKneeBack.setImageResource(R.drawable.left_knee_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftKneeBack.setImageResource(R.drawable.left_knee_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftKneeBack.setImageResource(R.drawable.left_knee_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_crus_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_crus_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrusBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftCrusBack.setImageResource(R.drawable.left_crus_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftCrusBack.setImageResource(R.drawable.left_crus_men_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftCrusBack.setImageResource(R.drawable.left_crus_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_crus_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_crus_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrusBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightCrusBack.setImageResource(R.drawable.right_crus_male_back_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightCrusBack.setImageResource(R.drawable.right_crus_back_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightCrusBack.setImageResource(R.drawable.right_crus_male_back_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_tarsus_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_tarsus_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsusBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightTarsusBack.setImageResource(R.drawable.right_tarsus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightTarsusBack.setImageResource(R.drawable.right_tarsus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightTarsusBack.setImageResource(R.drawable.right_tarsus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_tarsus_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_tarsus_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsusBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftTarsusBack.setImageResource(R.drawable.left_tarsus_male_mild);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftTarsusBack.setImageResource(R.drawable.left_tarsus_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftTarsusBack.setImageResource(R.drawable.left_tarsus_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("right_foot_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "right_foot_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFootBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Right");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgRightFootBack.setImageResource(R.drawable.right_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgRightFootBack.setImageResource(R.drawable.right_foot_severe);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgRightFootBack.setImageResource(R.drawable.right_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
                if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmBodyName().equals("left_foot_back_male")) {
                    ApplicationTest.getInstance().addInSelectedPart("name", "left_foot_back_male");
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFootBack");
                    ApplicationTest.getInstance().addInLeftRight("bpm_right_left", "Left");
                    ApplicationTest.getInstance().addInFrontBack("bpm_front_back", "Back");
                    if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("1")) {
                        imgLeftFootBack.setImageResource(R.drawable.left_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "mild");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("3")) {
                        imgLeftFootBack.setImageResource(R.drawable.left_foot_back);
                        ApplicationTest.getInstance().addInSelectedColor("name", "severe");
                    } else if (ApplicationTest.getInstance().clipBoardData.get(i).getBpmColor().equals("2")) {
                        imgLeftFootBack.setImageResource(R.drawable.left_foot_male_moderate);
                        ApplicationTest.getInstance().addInSelectedColor("name", "moderate");
                    }
                }
            }

        }
    }

}
