package com.example.syyam.chatbot;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class PushNotification extends Activity {

    DatePicker pickerDate;
    TimePicker pickerTime;
    Button buttonSetAlarm;
    TextView info;

    final static int RQS_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        final String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
        info = (TextView) findViewById(R.id.info);
        pickerDate = (DatePicker) findViewById(R.id.pickerdate);
        pickerTime = (TimePicker) findViewById(R.id.pickertime);

        Calendar now = Calendar.getInstance();

        pickerDate.init(now.get(Calendar.YEAR), now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH), null);

        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));

        buttonSetAlarm = (Button) findViewById(R.id.setalarm);
        buttonSetAlarm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Calendar current = Calendar.getInstance();

                Calendar cal = Calendar.getInstance();
// cal.set(pickerDate.getYear(), pickerDate.getMonth(),
// pickerDate.getDayOfMonth(),
// pickerTime.getCurrentHour(),
// pickerTime.getCurrentMinute(), 00);

                cal.set(2014,1,18,2,01, 04);


                if (cal.compareTo(current) <= 0) {
                    // The set Date/Time already passed
                    //Toast.makeText(getApplicationContext(), "Invalid Date/Time", Toast.LENGTH_LONG).show();
                    setAlarm(cal,newString);
                } else {
                    Toast.makeText(getApplicationContext(),
                            ""+cal.getTime(), Toast.LENGTH_LONG).show();
                    setAlarm(cal,newString);
                }

            }
        });
    }

    private void setAlarm(Calendar targetCal,String newString){

        info.setText("\n\n***\n"
                + "Alarm is set@ " + targetCal.getTime() + "\n"
                + "***\n");



        Intent intent = new Intent(getBaseContext(), AlarmReciever.class);
        intent.putExtra("string", newString);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
    }
}
