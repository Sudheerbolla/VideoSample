package com.videosource.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.videosource.interfaces.ISearchClickListener;

public class SmsReceiver extends BroadcastReceiver {

    private static ISearchClickListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();
        SmsMessage[] messages;
        if (myBundle != null) {
            Object[] pdus = (Object[]) myBundle.get("pdus");
            assert pdus != null;
            messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; i++) {
                messages[i] = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? SmsMessage.createFromPdu((byte[]) pdus[i], myBundle.getString("format")) : SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (mListener != null) mListener.onClick(messages[i].getMessageBody());
            }
        }
    }

    public static void bindListener(ISearchClickListener listener) {
        mListener = listener;
    }

}