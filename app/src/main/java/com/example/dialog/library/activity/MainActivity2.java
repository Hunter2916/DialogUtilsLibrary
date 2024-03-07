package com.example.dialog.library.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.dialog.dialoglib.listener.OnDialogClickListener;
import com.example.dialog.dialoglib.utils.EaseCubicInterpolator;
import com.example.dialog.dialoglib.utils.ScreenUtils;
import com.example.dialog.library.R;

import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity2 extends AppCompatActivity {

    private Dialog mDialog;
    private OnDialogClickListener mListener;
    private WindowManager mWindowManager;

    private final int flags_outside_clickable = WindowManager.LayoutParams.FLAG_TOUCHABLE_WHEN_WAKING
            | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH
            | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
            | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
            | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tvToast = findViewById(R.id.tv_toast);
//        if (!Settings.canDrawOverlays(this)) {
//            Intent intent = new Intent();
//            intent.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//            startActivity(intent);
//        }
        tvToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDialogHint("我是测试的内容", "确定", "政策");
            }
        });
    }

    /**
     * 6、通用对话框（包括内容、确定和 标题）
     *
     * @param messageStr 中间内容
     * @param confirmStr 确认按钮
     * @param titleStr   标题
     */
    public void setDialogHint(String messageStr, String confirmStr, String titleStr) {
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout CustomView;

        CustomView = (LinearLayout) inflater.inflate(com.example.dialog.dialoglib.R.layout.custom_dialog_hint_title, null);
        TextView title = CustomView.findViewById(com.example.dialog.dialoglib.R.id.tv_custom_dialog_setting_title);
        title.setText(titleStr);

        TextView message = CustomView.findViewById(com.example.dialog.dialoglib.R.id.tv_custom_dialog_setting_message);
        TextView cancel = CustomView.findViewById(com.example.dialog.dialoglib.R.id.tv_custom_dialog_setting_cancel);
        TextView confirm = CustomView.findViewById(com.example.dialog.dialoglib.R.id.tv_custom_dialog_setting_confirm);

        if (null != messageStr) {
            message.setText(messageStr);
        }
        if (null != confirmStr) {
            confirm.setText(confirmStr);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimissDialog();
                if (CustomView.isAttachedToWindow()) {
                    mWindowManager.removeView(CustomView);
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dimissDialog();
                if (CustomView.isAttachedToWindow()) {
                    mWindowManager.removeView(CustomView);
                }
            }
        });
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mDialog = new Dialog(this, com.example.dialog.dialoglib.R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(true);
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        dialogWindow.setDimAmount(0.6f);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if ((CustomView.getParent()) != null && (CustomView.getParent() instanceof ViewGroup)) {
            ((ViewGroup) CustomView.getParent()).removeAllViews();
        }
        layoutParams.height = (int) (ScreenUtils.getScreenWidth(this) * 0.2);
        layoutParams.width = (int) (ScreenUtils.getScreenHeight(this) * 0.6);
        mWindowManager.addView(CustomView, layoutParams);
        starAnimation(CustomView);

        //点击屏幕外侧，dialog不消失
        mDialog.show();
    }

    private void starAnimation(View view) {
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", 0, -70, 0);
        animatorY.setEvaluator(new FloatEvaluator());
        AnimatorSet set = new AnimatorSet();
        set.setDuration(4500);
//        set.setInterpolator(new EaseCubicInterpolator(0.37f, 0.75f, 0.46f, 1f));
        set.setInterpolator(new EaseCubicInterpolator(0.37f, 0.75f, 0.46f, 1f));

        set.playTogether(animatorY);
        set.start();
//        pauseAnimation(set);
//        AnimatorSet animatorSet = animatorSet = new AnimatorSet();
        // 第一段动画设置相关属性
//        ValueAnimator openAnimator1 = ValueAnimator.ofFloat(translationY, 0);
//        openAnimator1.setInterpolator(new EaseCubicInterpolator(0.37f, 0.75f, 0.46f, 1f));
//        openAnimator1.setDuration(isDragging ? duration : 420);
//        openAnimator1.setInterpolator(new EaseCubicInterpolator(0.37f, 0.75f, 0.46f, 1f));
          // 第二段动画
//        ValueAnimator openAnimator2 = ValueAnimator.ofFloat(0, 0);
//        openAnimator2.setInterpolator(new EaseCubicInterpolator(0.3f, 0.0f, 0.5f, 1f));
//        openAnimator2.setDuration(250);
//        animatorSet.playSequentially(openAnimator1, openAnimator2);
    }

    /**
     * 销毁对话框
     */
    public void dimissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}