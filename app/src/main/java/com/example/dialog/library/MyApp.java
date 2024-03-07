package com.example.dialog.library;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.mmkv.MMKV;

public class MyApp extends Application {

    private static final String TAG = "MyApp";

    private static MyApp sApp;

    public static Context get() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        String rootDir = MMKV.initialize(this);
        Log.e(TAG, "MMKV: "+rootDir);
    }

}
