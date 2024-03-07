package com.example.dialog.library.listener;

// EventPublisher.java
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EventPublisher {
    private MyCallback callbacks;

    public void addCallback(MyCallback callback) {
        this.callbacks = callback;
    }


    public void performAction(boolean success) {
        Log.i("ceshi", "callback: " + callbacks);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                Log.i("ceshi", "callback: " + callbacks);
                try {
                    TimeUnit.MILLISECONDS.sleep(5000);
                    if (callbacks != null) {
                        callbacks.onSuccess("成功");
                    }
//                    Log.i("ceshi", "callback: " + callbacks);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public String getMessage() {
        return "失败";
    }
}

