package com.ucsf.painless.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


/**
 * @Author         Avion Team.
 * @Date           30 May 2019.
 * @SessionManager Handles Session - SharedPreferences.
 */

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int MODE_MULTI_PROCESS = 0;

    // Sharedpref file name
    private final String PREF_NAME = "RxOnDemand";


    private SharedPreferences getPref() {
        return _context.getSharedPreferences(PREF_NAME, Context.MODE_MULTI_PROCESS);
    }

    // Constructor
    public SessionManager(Context context) {
        super();
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, MODE_MULTI_PROCESS);
        editor = pref.edit();
        editor.commit();
    }

    //Clear all session data which is  saved into device
    public boolean clearShareUtils() {
        SharedPreferences settings = _context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        Editor editor = settings.edit();
        editor.clear();
        return editor.commit();
    }


    public void setBooleanKey(String key, boolean value) {
        Editor pref = getPref().edit();
        pref.putBoolean(key, value);
        pref.commit();
    }

    public boolean getBooleanKey(String key)
    {
        return getPref().getBoolean(key,false);
    }

    public void setStringKey(String key, String value) {
        Editor pref = getPref().edit();
        pref.putString(key, value);
        pref.commit();
    }
    public String getStringKey(String key)
    {
        return getPref().getString(key,null);
    }


    public void setIntKey(String key, int value) {
        Editor pref = getPref().edit();
        pref.putInt(key, value);
        pref.commit();
    }
    public int getIntKey(String key)
    {
        return getPref().getInt(key,0);
    }
}
