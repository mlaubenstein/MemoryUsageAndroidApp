package com.example.marvin.memoryusage;

import android.app.Application;
import android.content.Context;

public class MainRestart extends Application {

    public static MainRestart instance;

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    @Override
    public Context getApplicationContext(){
        return super.getApplicationContext();
    }

    public static MainRestart getInstance(){
        return instance;
    }

}
