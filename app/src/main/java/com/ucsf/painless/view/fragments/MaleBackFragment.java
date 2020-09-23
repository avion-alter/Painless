package com.ucsf.painless.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.ucsf.painless.R;

import butterknife.BindView;
import butterknife.OnClick;

public class MaleBackFragment extends Fragment {

    @BindView(R.id.img_left_head_back)
    ImageView imgLeftHeadBack;
    @BindView(R.id.img_right_head_back)
    ImageView imgRightHeadBack;
    @BindView(R.id.linear_head)
    LinearLayout linearHead;
    @BindView(R.id.img_left_head_2_back)
    ImageView imgLeftHead2Back;
    @BindView(R.id.img_right_head_2_back)
    ImageView imgRightHead2Back;
    @BindView(R.id.linear_face)
    LinearLayout linearFace;
    @BindView(R.id.img_left_neck_back)
    ImageView imgLeftNeckBack;
    @BindView(R.id.img_right_neck_back)
    ImageView imgRightNeckBack;
    @BindView(R.id.linear_neck)
    LinearLayout linearNeck;
    @BindView(R.id.img_left_back_top_back)
    ImageView imgLeftBackTopBack;
    @BindView(R.id.img_right_back_top_back)
    ImageView imgRightBackTopBack;
    @BindView(R.id.linear_chest)
    LinearLayout linearChest;
    @BindView(R.id.img_left_back_middle_back)
    ImageView imgLeftBackMiddleBack;
    @BindView(R.id.img_right_back_middle_back)
    ImageView imgRightBackMiddleBack;
    @BindView(R.id.linear_middle_chest)
    LinearLayout linearMiddleChest;
    @BindView(R.id.img_left_shoulder_back)
    ImageView imgLeftShoulderBack;
    @BindView(R.id.img_right_shoulder_back)
    ImageView imgRightShoulderBack;
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
    @BindView(R.id.img_left_back_lower_back)
    ImageView imgLeftBackLowerBack;
    @BindView(R.id.img_right_back_lower_back)
    ImageView imgRightBackLowerBack;
    @BindView(R.id.linear_stomach)
    LinearLayout linearStomach;
    @BindView(R.id.img_left_hip_back)
    ImageView imgLeftHipBack;
    @BindView(R.id.img_right_hip_back)
    ImageView imgRightHipBack;
    @BindView(R.id.linear_hip)
    LinearLayout linearHip;
    @BindView(R.id.img_left_hip2)
    ImageView imgLeftHip2;
    @BindView(R.id.img_right_hip2_back)
    ImageView imgRightHip2Back;
    @BindView(R.id.linear_pelvin)
    LinearLayout linearPelvin;
    @BindView(R.id.img_left_thigh_back)
    ImageView imgLeftThighBack;
    @BindView(R.id.img_right_thigh_back)
    ImageView imgRightThighBack;
    @BindView(R.id.linear_thigh)
    LinearLayout linearThigh;
    @BindView(R.id.img_right_knee_back)
    ImageView imgRightKneeBack;
    @BindView(R.id.img_left_knee_back)
    ImageView imgLeftKneeBack;
    @BindView(R.id.linear_knee)
    LinearLayout linearKnee;
    @BindView(R.id.img_left_crus_back)
    ImageView imgLeftCrusBack;
    @BindView(R.id.img_right_crus_back)
    ImageView imgRightCrusBack;
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
    private OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_male_body_map_back, container, false);

        return view;
    }

    @OnClick({R.id.img_left_head_back, R.id.img_right_head_back, R.id.img_left_head_2_back, R.id.img_right_head_2_back, R.id.img_left_neck_back, R.id.img_right_neck_back, R.id.img_left_back_top_back, R.id.img_right_back_top_back, R.id.img_left_back_middle_back, R.id.img_right_back_middle_back, R.id.img_left_shoulder_back, R.id.img_right_shoulder_back, R.id.img_right_arm, R.id.img_left_arm, R.id.img_right_cubital, R.id.img_left_cubital, R.id.img_right_forearm, R.id.img_left_forearm, R.id.img_right_wrist, R.id.img_left_wrist, R.id.img_right_palm, R.id.img_left_palm, R.id.img_left_back_lower_back, R.id.img_right_back_lower_back, R.id.img_left_hip_back, R.id.img_right_hip_back, R.id.img_left_hip2, R.id.img_right_hip2_back, R.id.img_left_thigh_back, R.id.img_right_thigh_back, R.id.img_right_knee_back, R.id.img_left_knee_back, R.id.img_left_crus_back, R.id.img_right_crus_back, R.id.img_right_tarsus, R.id.img_left_tarsus, R.id.img_right_foot, R.id.img_left_foot})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left_head_back:
                break;
            case R.id.img_right_head_back:
                break;
            case R.id.img_left_head_2_back:
                break;
            case R.id.img_right_head_2_back:
                break;
            case R.id.img_left_neck_back:
                break;
            case R.id.img_right_neck_back:
                break;
            case R.id.img_left_back_top_back:
                break;
            case R.id.img_right_back_top_back:
                break;
            case R.id.img_left_back_middle_back:
                break;
            case R.id.img_right_back_middle_back:
                break;
            case R.id.img_left_shoulder_back:
                break;
            case R.id.img_right_shoulder_back:
                break;
            case R.id.img_right_arm:
                break;
            case R.id.img_left_arm:
                break;
            case R.id.img_right_cubital:
                break;
            case R.id.img_left_cubital:
                break;
            case R.id.img_right_forearm:
                break;
            case R.id.img_left_forearm:
                break;
            case R.id.img_right_wrist:
                break;
            case R.id.img_left_wrist:
                break;
            case R.id.img_right_palm:
                break;
            case R.id.img_left_palm:
                break;
            case R.id.img_left_back_lower_back:
                break;
            case R.id.img_right_back_lower_back:
                break;
            case R.id.img_left_hip_back:
                break;
            case R.id.img_right_hip_back:
                break;
            case R.id.img_left_hip2:
                break;
            case R.id.img_right_hip2_back:
                break;
            case R.id.img_left_thigh_back:
                break;
            case R.id.img_right_thigh_back:
                break;
            case R.id.img_right_knee_back:
                break;
            case R.id.img_left_knee_back:
                break;
            case R.id.img_left_crus_back:
                break;
            case R.id.img_right_crus_back:
                break;
            case R.id.img_right_tarsus:
                break;
            case R.id.img_left_tarsus:
                break;
            case R.id.img_right_foot:
                break;
            case R.id.img_left_foot:
                break;
        }
    }


    public interface OnItemSelectedListener {
        public void onRssItemSelected(String link);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // may also be triggered from the Activity
    public void updateDetail(String uri) {
        // create a string just for testing
        String newTime = String.valueOf(System.currentTimeMillis());

        // inform the Activity about the change based
        // interface defintion
        listener.onRssItemSelected(newTime);
    }
}