package com.example.marvin.memoryusage;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Activity activity;

    public ExceptionHandler(Activity activity){
        this.activity = activity;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Intent intent = new Intent (activity, MainActivity.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(MainRestart.getInstance().getBaseContext(),
                        0,intent,PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager =
                (AlarmManager) MainRestart  .getInstance()
                                            .getBaseContext()
                                            .getSystemService(Context.ALARM_SERVICE);

        intent.putExtra("crash",true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TASK|
                        Intent.FLAG_ACTIVITY_NEW_TASK);

        alarmManager.set(AlarmManager.RTC,System.currentTimeMillis()+100, pendingIntent);

        activity.finish();
        System.exit(2);


    }
}
