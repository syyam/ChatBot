package com.example.syyam.chatbot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Syyam on 08-Jul-17.
 */

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context arg0, Intent arg1) {

       // String get_your_string = arg1.getExtras().getString("string");
        Toast.makeText(arg0, "Alarm Recieved", Toast.LENGTH_LONG).show();
    }
}
