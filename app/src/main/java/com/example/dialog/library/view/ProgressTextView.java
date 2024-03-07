package com.example.dialog.library.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class ProgressTextView extends AppCompatTextView {

    public ProgressTextView(Context context) {
        super(context);
    }

    public ProgressTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 设置文本的方法
    public void setProgressText(int progress, int max) {
        String text = progress + "/" + max;
        setText(text);
    }
}
