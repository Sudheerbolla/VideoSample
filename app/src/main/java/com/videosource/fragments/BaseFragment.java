package com.videosource.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission);
    }

    public void requestForPermission(String permission, int requestCode) {
        requestPermissions(new String[]{permission}, requestCode);
    }

    public void requestForPermission(String[] permission, int requestCode) {
        requestPermissions(permission, requestCode);
    }

}
