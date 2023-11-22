package com.example.dialog.library;

import android.app.Application;
import android.util.Log;

import com.tencent.mmkv.MMKV;

public class MyApp extends Application {

    private static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();
        String rootDir = MMKV.initialize(this);
        Log.e(TAG, "MMKV: "+rootDir);
    }

}
