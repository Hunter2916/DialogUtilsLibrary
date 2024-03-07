package com.example.dialog.library.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dialog.library.R;

/**
 * 可折叠的TextView
 */
public class FoldTextView extends LinearLayout {
    FoldTextViewHolder mFoldTextViewHolder;
    View myView;

    /**
     * 真实行数
     */
    int realLineCounts = 0;

    /**
     * 默认行数
     */
    int defaultLineCounts = 4;

    /**
     * 真实高度
     */
    int realHeight = 0;

    /**
     * 折叠后的高度
     */
    int foldHeight = 0;

    boolean isFirstLoad = true;

    /**
     * 当前是否展开
     */
    boolean isExp = false;

    /**
     * 展开TextView
     */
    final int TEXT_OPEN = 1;

    /**
     * 关闭TextView
     */
    final int TEXT_CLOSE = 2;

    final Handler mHander = new Handler(Looper.getMainLooper()) {

        public void handleMessage(android.os.Message msg) {
            int lines = (Integer) msg.obj;
            switch (msg.what) {
                case TEXT_OPEN: //打开，增加高度
                    mFoldTextViewHolder.tvFold.setMaxLines(lines);
                    mFoldTextViewHolder.tvButton.setText("折叠");
                    break;
                case TEXT_CLOSE: //关闭，减少高度
                    mFoldTextViewHolder.tvFold.setMaxLines(lines);
                    mFoldTextViewHolder.tvButton.setText("更多");
                    break;
            }
        }
    };

    public FoldTextView(Context context) {
        super(context);

        init();
    }

    public FoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public FoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        myView = inflater.inflate(R.layout.view_widget_foldtextview, null);
        this.addView(myView, layoutParams);

        mFoldTextViewHolder = new FoldTextViewHolder(this);
    }

    public void setText(String text) {
        mFoldTextViewHolder.tvFold.setText(text);

        ViewTreeObserver vto = mFoldTextViewHolder.tvFold.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!isFirstLoad) {
                    return;
                }

                //获取真实行数
                realLineCounts = mFoldTextViewHolder.tvFold.getLineCount();
                realHeight = mFoldTextViewHolder.tvFold.getMeasuredHeight();

                //如果真实行数大于默认的显示行数，则默认将其折叠起来  isExp为false
                if (realLineCounts > defaultLineCounts) {
                    mFoldTextViewHolder.tvFold.setMaxLines(defaultLineCounts);
                    mFoldTextViewHolder.tvFold.measure(0, 0);
                    foldHeight = mFoldTextViewHolder.tvFold.getMeasuredHeight();
                    mFoldTextViewHolder.tvButton.setVisibility(View.VISIBLE);

                    mFoldTextViewHolder.tvFold.setOnClickListener(FoldOnclick);
                    mFoldTextViewHolder.tvButton.setOnClickListener(FoldOnclick);
                    isExp = false;
                }
                //如果真实行数小于默认行数，则直接展示出来。isExp为true;
                else {
                    mFoldTextViewHolder.tvButton.setVisibility(View.GONE);
                    isExp = true;
                }

                isFirstLoad = false;
            }
        });
    }

    View.OnClickListener FoldOnclick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isExp) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int endcount = realLineCounts;
                        while (endcount-- > defaultLineCounts) {
                            Message msg = Message.obtain();
                            msg.what = TEXT_CLOSE;
                            msg.obj = endcount;
                            mHander.sendMessage(msg);
                        }
                    }
                }).start();

                isExp = false;
            } else {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        int startcount = defaultLineCounts;
                        while (startcount++ < realLineCounts) {
                            Message msg = Message.obtain();
                            msg.what = TEXT_OPEN;
                            msg.obj = startcount;
                            mHander.sendMessage(msg);
                        }
                    }
                }).start();
                isExp = true;
            }
        }
    };

    static class FoldTextViewHolder {
        TextView tvFold;
        TextView tvButton;

        public FoldTextViewHolder(View v) {
            tvFold = (TextView) v.findViewById(R.id.tv_fold);
            tvButton = (TextView) v.findViewById(R.id.tv_button);
        }
    }
}
