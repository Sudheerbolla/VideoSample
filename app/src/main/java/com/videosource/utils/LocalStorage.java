package com.videosource.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LocalStorage {
    public static final String PREF_NAME = "com.videosource.userdata";
    private static LocalStorage mInstance;
    private static SharedPreferences preferences;
    private static Editor editor;

    public static final String IS_GUEST_USER = "IS_GUEST_USER";
    public static final String IS_LOGGED_IN_ALREADY = "IS_LOGGED_IN_ALREADY";
    public static final String IS_AUTOLOGIN_ENABLED = "IS_AUTOLOGIN_ENABLED";
    public static final String IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH";
    public static final String PREF_LANGUAGE = "PREF_LANGUAGE";

    private LocalStorage(Context mContext) {
        preferences = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static synchronized LocalStorage getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new LocalStorage(mContext);
        }
        return mInstance;
    }

    public void putString(String key, String value) {
        editor = editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public void putInt(String key, int value) {
        editor = editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void putFloat(String key, float value) {
        editor = editor.putFloat(key, value);
        editor.commit();
    }

    public float getFloat(String key, float defValue) {
        return preferences.getFloat(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        editor = editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void clearLocalStorage() {
        editor.clear().commit();
    }

}
