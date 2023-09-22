package com.example.dialog.library.live;

import androidx.lifecycle.LiveData;

/**
 * livedata
 */
public class DemoData extends LiveData<DemoData> {
    private int tag1;
    private int tag2;

    public int getTag1() {
        return tag1;

    }
    public void setTag1(int tag1) {
        this.tag1 = tag1;
        postValue(this);
    }

    public int getTag2() {
        return tag2;
    }

    public void setTag2(int tag2) {
        this.tag2 = tag2;
        postValue(this);
    }
}
