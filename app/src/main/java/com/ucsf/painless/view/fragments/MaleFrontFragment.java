package com.ucsf.painless.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MaleFrontFragment extends Fragment {

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
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_male_body_map, container, false);
        imgRightHead = (ImageView) view.findViewById(R.id.img_right_head);
        imgLeftHead = (ImageView) view.findViewById(R.id.img_left_head);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.img_right_head, R.id.img_left_head, R.id.img_right_face, R.id.img_left_face, R.id.img_right_neck, R.id.img_left_neck, R.id.img_right_chest, R.id.img_left_chest, R.id.img_right_shoulder, R.id.img_left_shoulder, R.id.img_right_arm, R.id.img_left_arm, R.id.img_right_cubital, R.id.img_left_cubital, R.id.img_right_forearm, R.id.img_left_forearm, R.id.img_right_wrist, R.id.img_left_wrist, R.id.img_right_palm, R.id.img_left_palm, R.id.img_right_stomach, R.id.img_left_stomach, R.id.img_right_hip, R.id.img_left_hip, R.id.img_right_pelvis, R.id.img_left_pelvis, R.id.img_right_thigh, R.id.img_left_thigh, R.id.img_left_knee, R.id.img_right_knee, R.id.img_right_crus, R.id.img_left_crus, R.id.img_right_tarsus, R.id.img_left_tarsus, R.id.img_right_foot, R.id.img_left_foot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_right_head:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightHead.setImageResource(R.drawable.right_head_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHead");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightHead.setImageResource(R.drawable.right_head_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHead");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightHead.setImageResource(R.drawable.right_head_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHead");
                }
                break;
            case R.id.img_left_head:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftHead.setImageResource(R.drawable.left_head_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHead");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftHead.setImageResource(R.drawable.left_head_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHead");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftHead.setImageResource(R.drawable.left_head_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHead");
                }
                break;
            case R.id.img_right_face:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightFace.setImageResource(R.drawable.right_face_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFace");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightFace.setImageResource(R.drawable.right_face_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFace");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightFace.setImageResource(R.drawable.right_face_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFace");
                }
                break;
            case R.id.img_left_face:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftFace.setImageResource(R.drawable.left_face_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFace");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftFace.setImageResource(R.drawable.left_face_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFace");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftFace.setImageResource(R.drawable.left_face_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFace");
                }
                break;
            case R.id.img_right_neck:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightNeck.setImageResource(R.drawable.right_neck_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeck");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightNeck.setImageResource(R.drawable.right_neck_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeck");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightNeck.setImageResource(R.drawable.right_neck_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightNeck");
                }
                break;
            case R.id.img_left_neck:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftNeck.setImageResource(R.drawable.left_neck_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeck");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftNeck.setImageResource(R.drawable.left_neck_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeck");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftNeck.setImageResource(R.drawable.left_neck_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftNeck");
                }
                break;
            case R.id.img_right_chest:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightChest.setImageResource(R.drawable.right_chest_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightChest");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightChest.setImageResource(R.drawable.right_chest_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightChest");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightChest.setImageResource(R.drawable.right_chest_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightChest");
                }
                break;
            case R.id.img_left_chest:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftChest.setImageResource(R.drawable.left_chest_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftChest");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftChest.setImageResource(R.drawable.left_chest_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftChest");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftChest.setImageResource(R.drawable.left_chest_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftChest");
                }
                break;
            case R.id.img_right_shoulder:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightShoulder.setImageResource(R.drawable.right_shoulder_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulder");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightShoulder.setImageResource(R.drawable.right_shoulder_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulder");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightShoulder.setImageResource(R.drawable.right_shoulder_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightShoulder");
                }
                break;
            case R.id.img_left_shoulder:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftShoulder.setImageResource(R.drawable.left_shoulder_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulder");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftShoulder.setImageResource(R.drawable.left_shoulder_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulder");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftShoulder.setImageResource(R.drawable.left_shoulder_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftShoulder");
                }
                break;
            case R.id.img_right_arm:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightArm.setImageResource(R.drawable.right_arm_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightArm.setImageResource(R.drawable.right_arm_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightArm.setImageResource(R.drawable.right_arm_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightArm");
                }
                break;
            case R.id.img_left_arm:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftArm.setImageResource(R.drawable.left_arm_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftArm.setImageResource(R.drawable.left_arm_male_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftArm.setImageResource(R.drawable.left_arm_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftArm");
                }
                break;
            case R.id.img_right_cubital:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightCubital.setImageResource(R.drawable.right_cubital_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubital");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightCubital.setImageResource(R.drawable.right_cubital_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubital");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightCubital.setImageResource(R.drawable.right_cubital_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCubital");
                }
                break;
            case R.id.img_left_cubital:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftCubital.setImageResource(R.drawable.left_cubital_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubital");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftCubital.setImageResource(R.drawable.left_cubital_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubital");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftCubital.setImageResource(R.drawable.left_cubital_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCubital");
                }
                break;
            case R.id.img_right_forearm:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightForearm.setImageResource(R.drawable.right_forearm_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightForearm.setImageResource(R.drawable.right_forearm_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightForearm.setImageResource(R.drawable.right_forearm_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightForearm");
                }
                break;
            case R.id.img_left_forearm:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftForearm.setImageResource(R.drawable.left_forearm_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftForearm.setImageResource(R.drawable.left_forearm_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftForearm.setImageResource(R.drawable.left_forearm_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftForearm");
                }
                break;
            case R.id.img_right_wrist:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightWrist.setImageResource(R.drawable.right_wrist_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWrist");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightWrist.setImageResource(R.drawable.right_wrist_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWrist");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightWrist.setImageResource(R.drawable.right_wrist_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightWrist");
                }
                break;
            case R.id.img_left_wrist:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftWrist.setImageResource(R.drawable.left_wrist_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWrist");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftWrist.setImageResource(R.drawable.left_wrist_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWrist");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftWrist.setImageResource(R.drawable.left_wrist_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftWrist");
                }
                break;
            case R.id.img_right_palm:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightPalm.setImageResource(R.drawable.right_palm_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightPalm.setImageResource(R.drawable.right_palm_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightPalm.setImageResource(R.drawable.right_palm_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPalm");
                }
                break;
            case R.id.img_left_palm:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftPalm.setImageResource(R.drawable.left_palm_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftPalm.setImageResource(R.drawable.left_palm_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalm");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftPalm.setImageResource(R.drawable.left_palm_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPalm");
                }
                break;
            case R.id.img_right_stomach:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightStomach.setImageResource(R.drawable.right_stomach_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightStomach");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightStomach.setImageResource(R.drawable.right_stomach_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightStomach");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightStomach.setImageResource(R.drawable.right_stomach_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightStomach");
                }
                break;
            case R.id.img_left_stomach:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftStomach.setImageResource(R.drawable.left_stomach_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftStomach");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftStomach.setImageResource(R.drawable.left_stomach_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftStomach");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightStomach.setImageResource(R.drawable.left_stomach_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftStomach");
                }
                break;
            case R.id.img_right_hip:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightHip.setImageResource(R.drawable.right_hip_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHip");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightHip.setImageResource(R.drawable.right_hip_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHip");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightHip.setImageResource(R.drawable.right_hip_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightHip");
                }
                break;
            case R.id.img_left_hip:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftHip.setImageResource(R.drawable.left_hip_male_mild_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHip");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftHip.setImageResource(R.drawable.left_hip_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHip");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftHip.setImageResource(R.drawable.left_hip_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftHip");
                }
                break;
            case R.id.img_right_pelvis:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightPelvis.setImageResource(R.drawable.right_pelvis_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPelvis");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightPelvis.setImageResource(R.drawable.right_pelvis_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPelvis");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightPelvis.setImageResource(R.drawable.right_pelvis_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightPelvis");
                }
                break;
            case R.id.img_left_pelvis:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftPelvis.setImageResource(R.drawable.left_pelvis_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPelvis");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftPelvis.setImageResource(R.drawable.left_pelvis_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPelvis");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftPelvis.setImageResource(R.drawable.left_pelvis_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftPelvis");
                }
                break;
            case R.id.img_right_thigh:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightThigh.setImageResource(R.drawable.right_thigh_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThigh");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightThigh.setImageResource(R.drawable.right_thigh_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThigh");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightThigh.setImageResource(R.drawable.right_thigh_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightThigh");
                }
                break;
            case R.id.img_left_thigh:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftThigh.setImageResource(R.drawable.left_thigh_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThigh");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftThigh.setImageResource(R.drawable.left_thigh_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThigh");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftThigh.setImageResource(R.drawable.left_thigh_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftThigh");
                }
                break;
            case R.id.img_left_knee:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftKnee.setImageResource(R.drawable.left_knee_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKnee");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftKnee.setImageResource(R.drawable.left_knee_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKnee");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftKnee.setImageResource(R.drawable.left_knee_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftKnee");
                }
                break;
            case R.id.img_right_knee:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightKnee.setImageResource(R.drawable.right_knee_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKnee");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightKnee.setImageResource(R.drawable.right_knee_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKnee");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightKnee.setImageResource(R.drawable.right_knee_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightKnee");
                }
                break;
            case R.id.img_right_crus:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightCrus.setImageResource(R.drawable.right_crus_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrus");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightCrus.setImageResource(R.drawable.right_crus_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrus");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightCrus.setImageResource(R.drawable.right_crus_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightCrus");
                }
                break;
            case R.id.img_left_crus:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftCrus.setImageResource(R.drawable.left_crus_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrus");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftCrus.setImageResource(R.drawable.left_crus_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrus");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftCrus.setImageResource(R.drawable.left_crus_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftCrus");
                }
                break;
            case R.id.img_right_tarsus:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightTarsus.setImageResource(R.drawable.right_tarsus_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsus");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightTarsus.setImageResource(R.drawable.right_tarsus_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsus");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightTarsus.setImageResource(R.drawable.right_tarsus_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightTarsus");
                }
                break;
            case R.id.img_left_tarsus:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftTarsus.setImageResource(R.drawable.left_tarsus_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsus");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftTarsus.setImageResource(R.drawable.left_tarsus_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsus");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftTarsus.setImageResource(R.drawable.left_tarsus_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftTarsus");
                }
                break;
            case R.id.img_right_foot:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgRightFoot.setImageResource(R.drawable.right_foot_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFoot");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgRightFoot.setImageResource(R.drawable.right_foot_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFoot");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgRightFoot.setImageResource(R.drawable.right_foot_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgRightFoot");
                }
                break;
            case R.id.img_left_foot:
                if (ApplicationTest.getInstance().getSelectedSeverity().equals("mild")) {
                    imgLeftFoot.setImageResource(R.drawable.left_foot_male_mild);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFoot");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("severe")) {
                    imgLeftFoot.setImageResource(R.drawable.left_foot_severe);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFoot");
                } else if (ApplicationTest.getInstance().getSelectedSeverity().equals("moderate")) {
                    imgLeftFoot.setImageResource(R.drawable.left_foot_male_moderate);
                    ApplicationTest.getInstance().addInSelected("id", "imgLeftFoot");
                }
                break;
        }
    }


    public void undo() {
        if (ApplicationTest.getInstance().getSelectedSevere().size() > 0) {
            ArrayList<HashMap<String, String>> idStringHashMap = ApplicationTest.getInstance().getSelectedSevere();
            for (int i = 0; i < idStringHashMap.size(); i++) {
                HashMap<String, String> map = idStringHashMap.get(i);
                if (map.get("id").equals("imgRightHead")) {
                    imgRightHead.setImageResource(R.drawable.left_arm_male_severe);
                }
            }
        }
    }
}