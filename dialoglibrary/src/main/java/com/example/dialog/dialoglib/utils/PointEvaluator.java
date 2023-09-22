package com.example.dialog.dialoglib.utils;

import android.animation.TypeEvaluator;
import android.graphics.Point;

// 实现TypeEvaluator接口 估值器
public class PointEvaluator implements TypeEvaluator {

    // 复写evaluate（）
// 在evaluate（）里写入对象动画过渡的逻辑
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        // 参数说明
        // fraction：表示动画完成度（根据它来计算当前动画的值）
        // startValue、endValue：动画的初始值和结束值

        // ... 写入对象动画过渡的逻辑

        float startFloat = ((Number) startValue).floatValue();

        return startFloat + fraction * (((Number) endValue).floatValue() - startFloat);
        // 返回对象动画过渡的逻辑计算后的值
    }
}


