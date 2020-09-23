package com.ucsf.painless.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ucsf.painless.network.APIInterface;


public class Constant {

    Context context;
    public static String COUNTRY_CODE1="RO";

    public static APIInterface apiInterface;
    public static SessionManager SESSION;

    public static String CURRENT_FRAGMENT;

    public static String SELECTED_DATE;
    public static String SELECTED_TIME;
    public static String SELECTED_END_TIME;


    //Sets the font to all Textview,Edittext,Buttons etc
    public static void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "font/montserrat_light.ttf"));
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "font/montserrat_light.ttf"));
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "font/montserrat_light.ttf"));
            }
        } catch (Exception e) {
        }
    }

}
