package com.example.dialog.library.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.dialog.library.R;

/**
 * 自定义progressBar,带进度文本
 */
public class ProgressBarView extends View {
    // 底色圆环的画笔
    private Paint bgPaint;
    // 底色圆环的颜色
    private int bgColor;
    // 进度圆的画笔
    private Paint ringProgressPaint;
    // 进度圆颜色
    private int ringProgressColor;
    // 中心文字的画笔
    private Paint textPaint;
    // 文字的颜色
    private int mTextColor;
    // 文字的大小
    private int mTextSize;
    // 圆环的宽度
    private float ringWidth;
    private int max = 100;
    private int progress = 0;
    private String mText = "0%";

    public ProgressBarView(Context context) {
        this(context, null);
        initPaint();
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        initAttrs(context, attrs);
        initPaint();
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
    }

    private void initPaint() {
        // 初始化圆环背景画笔
        bgPaint = new Paint();
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(ringWidth);
        bgPaint.setAntiAlias(true);

        // 初始化进度圆的画笔
        ringProgressPaint = new Paint();
        ringProgressPaint.setColor(ringProgressColor);
        ringProgressPaint.setStrokeWidth(ringWidth);
        // 设置进度圆的圆角
        ringProgressPaint.setStrokeCap(Paint.Cap.SQUARE);
        ringProgressPaint.setAntiAlias(true);
        ringProgressPaint.setStyle(Paint.Style.STROKE);

        // 初始化进度文本画笔
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(mTextColor);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(mTextSize);

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressBarView);
        bgColor = typedArray.getColor(R.styleable.ProgressBarView_ringColor, Color.GRAY);
        ringProgressColor = typedArray.getColor(R.styleable.ProgressBarView_ringProgressColor, Color.GREEN);
        ringWidth = typedArray.getDimension(R.styleable.ProgressBarView_ringWidth, 20);
        // 字体颜色
        mTextColor = typedArray.getColor(R.styleable.ProgressBarView_ringTextColor, Color.WHITE);
        // 字体大小
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.ProgressBarView_ringTextSize, 14);
        max = typedArray.getInteger(R.styleable.ProgressBarView_max, 100);
        //资源回收
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;
        int radius = (int) (xCenter - ringWidth / 2);
        // 绘制背景圆
        canvas.drawCircle(xCenter, yCenter, radius, bgPaint);
        // 绘制进度圆
        RectF rectF = new RectF(xCenter - radius, yCenter - radius, xCenter + radius, yCenter + radius);
        canvas.drawArc(rectF, -90, progress * 360 / max, false, ringProgressPaint);

        // 设置文本的位置，可以根据需要调整
        Rect textBounds = new Rect();

        textPaint.getTextBounds(mText, 0, mText.length(), textBounds);
        canvas.drawText(mText, xCenter, textBounds.height() / 2 + getHeight() / 2, textPaint);

    }

    /**
     * 设置文本
     */
    public void setText(String text) {
        mText = text;
    }

    /**
     * 设置文本的颜色
     */
    public void setTextColor(int color) {
        if (color <= 0) {
            throw new IllegalArgumentException("Color value can not be less than 0");
        }
        mTextColor = color;
    }

    /**
     * 设置文本的大小
     */
    public void setTextSize(int textSize) {
        if (textSize < 0) {
            throw new IllegalArgumentException("textSize can not be less than 0");
        }
        mTextSize = textSize;
    }

    public synchronized int getMax() {
        return max;
    }

    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }

    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }
    }

    public Paint getBgPaint() {
        return bgPaint;
    }

    public void setBgPaint(Paint bgPaint) {
        this.bgPaint = bgPaint;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public Paint getRingProgressPaint() {
        return ringProgressPaint;
    }

    public void setRingProgressPaint(Paint ringProgressPaint) {
        this.ringProgressPaint = ringProgressPaint;
    }

    public int getRingProgressColor() {
        return ringProgressColor;
    }

    public void setRingProgressColor(int ringProgressColor) {
        this.ringProgressColor = ringProgressColor;
    }

    public float getRingWidth() {
        return ringWidth;
    }

    public void setRingWidth(float ringWidth) {
        this.ringWidth = ringWidth;
    }
}