package com.example.dialog.dialoglib.utils;

import android.view.animation.Interpolator;

// 自定义插值器
public class SpringScalingInterpolator implements Interpolator {
    //弹性因数
    private float factor;

    public SpringScalingInterpolator(float factor){
        this.factor = factor;
    }
    @Override
    public float getInterpolation(float input) {
        return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
}