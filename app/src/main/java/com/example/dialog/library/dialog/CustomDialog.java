package com.example.dialog.library.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dialog.dialoglib.listener.OnDialogClickListener;
import com.example.dialog.dialoglib.utils.ScreenUtils;
import com.example.dialog.library.R;

import java.util.Objects;

public class CustomDialog extends AlertDialog implements View.OnClickListener {

    // 左上角关闭按钮
    private Drawable drawable;
    // 顶部标题
    private String title;
    // 顶部副标题
    private String subheadTitle;
    // 升级所需时间文本
    private String updateTimeTxt;
    // 中间文本
    private String message;
    // 确定按钮文本
    private String positiveButtonText;
    // 取消按钮文本
    private String negativeButtonText;

    private DialogInterface.OnDismissListener onDismissListener;

    // 是否开启点击外侧消失弹框,默认开启
    private boolean isOutside = true;

    // 按钮监听
    OnDialogClickListener1 positiveBtnClickListener;
    OnDialogClickListener1 negativeBtnClickListener;
    private OnDialogClickListener mListener;
    private final Context mContext;

    public CustomDialog(Builder builder) {
        super(builder.mContext, R.style.Theme_AudioDialog);
        this.mContext = builder.mContext;
        this.drawable = builder.drawable;
        this.updateTimeTxt = builder.updateTimeTxt;
        this.title = builder.title;
        this.subheadTitle = builder.subheadTitle;
        this.message = builder.message;
        this.positiveButtonText = builder.positiveButtonText;
        this.negativeButtonText = builder.negativeButtonText;
        this.positiveBtnClickListener = builder.positiveClickListener;
        this.negativeBtnClickListener = builder.negativeClickListener;
        this.isOutside = builder.isOutside;
        this.onDismissListener = builder.onDismissListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_withdraw);

        Window dialogWindow = this.getWindow();
        assert dialogWindow != null;
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.80); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);
        setCanceledOnTouchOutside(isOutside);
        initView();
    }

    private void initView() {

        // 左上角关闭按钮
        ImageView imgClose = findViewById(R.id.img_close_button);
        TextView mTitle = findViewById(R.id.txt_dialog_title);
        // 副标题
        TextView mSubTitle = findViewById(R.id.txt_update_version);
        // 升级所需时间
        TextView updateTime = findViewById(R.id.txt_update_time);
        // 升级信息
        TextView updateInfo = findViewById(R.id.txt_update_info);
        // 立即安装
        TextView install = findViewById(R.id.btn_upgrade_now);
        // 预约时间
        TextView schedule = findViewById(R.id.btn_set_time);
        imgClose.setOnClickListener(this);
        install.setOnClickListener(this);
        schedule.setOnClickListener(this);
        // 左上角关闭图标
        if (drawable != null) {
            imgClose.setImageDrawable(drawable);
            imgClose.setVisibility(View.VISIBLE);
        } else {
            imgClose.setVisibility(View.GONE);
        }
        // 标题
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
        } else {
            mTitle.setVisibility(View.GONE);
        }

        // 副标题
        if (!TextUtils.isEmpty(subheadTitle)) {
            mSubTitle.setText(subheadTitle);
            mSubTitle.setVisibility(View.VISIBLE);
        } else {
            mSubTitle.setVisibility(View.GONE);
        }

        // 升级所用时间
        if (!TextUtils.isEmpty(updateTimeTxt)) {
            updateTime.setText(updateTimeTxt);
            updateTime.setVisibility(View.VISIBLE);
        } else {
            updateTime.setVisibility(View.GONE);
        }

        // 中间文本信息
        if (!TextUtils.isEmpty(message)) {
            updateInfo.setText(message);
            updateInfo.setVisibility(View.VISIBLE);
        } else {
            updateInfo.setVisibility(View.GONE);
        }
        // 确定按钮 左侧那个
        if (!TextUtils.isEmpty(positiveButtonText)) {
            install.setText(positiveButtonText);
            install.setVisibility(View.VISIBLE);
        } else {
            install.setVisibility(View.GONE);
        }

        // 取消按钮 右侧那个
        if (!TextUtils.isEmpty(negativeButtonText)) {
            install.setText(negativeButtonText);
            install.setVisibility(View.VISIBLE);
        } else {
            install.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Objects.requireNonNull(this.getWindow()).setDimAmount(0f);
    }

    /**
     * 点击事件
     *
     * @param v The view that was clicked.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close_button:
                dismiss();
                break;
            case R.id.btn_upgrade_now:
                positiveBtnClickListener.onClick();
                break;
            case R.id.btn_set_time:
                negativeBtnClickListener.onClick();
                break;
            default:
                break;
        }
    }


    public static class Builder {
        private Context mContext;
        private Drawable drawable;
        private String title;
        private String subheadTitle;
        private String message;
        private String positiveButtonText;
        private OnDialogClickListener1 positiveClickListener;
        private String negativeButtonText;
        private OnDialogClickListener1 negativeClickListener;
        private String updateTimeTxt;
        private boolean isOutside;
        private DialogInterface.OnDismissListener onDismissListener;

        public Builder setContext(Context mContext) {
            this.mContext = mContext;
            return this;
        }

        public Builder setCloseDrawable(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSubheadTitle(String subheadTitle) {
            this.subheadTitle = subheadTitle;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnDialogClickListener1 listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnDialogClickListener1 listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeClickListener = listener;
            return this;
        }

        public Builder setDrawable(Drawable drawable) {
            this.drawable = drawable;
            return this;
        }

        public Builder setUpdateTimeTxt(String updateTimeTxt) {
            this.updateTimeTxt = updateTimeTxt;
            return this;
        }

        public Builder setOutside(boolean outside) {
            isOutside = outside;
            return this;
        }

        public Builder setDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        public CustomDialog build() {
            return new CustomDialog(this);
        }
    }

    /**
     * 销毁对话框
     */
    public void dismissDialog() {
        if (this.isShowing()) {
            dismiss();
            Log.i("ceshi", "dismissDialog: " + this.isShowing());
        }
    }
}
