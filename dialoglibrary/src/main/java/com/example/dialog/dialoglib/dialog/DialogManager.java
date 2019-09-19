package com.example.dialog.dialoglib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.example.dialog.dialoglib.R;
import com.example.dialog.dialoglib.adapter.ListAdapters;
import com.example.dialog.dialoglib.listener.OnDialogClickListener;
import com.example.dialog.dialoglib.listener.OnDialogEditListener;
import com.example.dialog.dialoglib.listener.OnDialogListListener;
import com.example.dialog.dialoglib.utils.ScreenUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/***
 * 对话框
 */
public class DialogManager {
    private Dialog mDialog;

    private OnDialogClickListener mListener;
    private OnDialogListListener mSaveListener;
    private OnDialogEditListener mSaveCancelListener;
    private Context mContext;

    public DialogManager(Context context) {
        this.mContext = context;
    }

    public void setOnDialogClickListener(OnDialogClickListener listener) {
        mListener = listener;
    }

    public void setOnDialogListListener(OnDialogListListener listener) {
        mSaveListener = listener;
    }

    public void setOnDialogEditListener(OnDialogEditListener mSaveCancelListener) {
        this.mSaveCancelListener = mSaveCancelListener;
    }

    /**
     * 1、常规对话框（包括 内容、确定和取消）
     *
     * @param
     * @return
     */
    public void mySettingBuilder(String messageStr) {
        setDialogHint(messageStr, null, null);
    }

    /**
     * 2、对话框（包括 内容、确定和取消）
     *
     * @param
     * @return
     */
    public void mySettingBuilder(String messageStr, String confirmStr) {
        setDialogHint(messageStr, confirmStr, null);
    }

    /**
     * 3、通用对话框（包括 内容、确定和 标题）
     *
     * @param
     * @param resid
     * @param confirmStr
     */
    public void setDialogPictureAndMessage(String messageStr, String confirmStr, String titleStr, int resid) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView;
        CustomView = inflater.inflate(R.layout.custom_dialog_withdraw, null);
        ImageView image = CustomView.findViewById(R.id.tv_custom_dialog_setting_image);
        TextView messagetopStr = CustomView.findViewById(R.id.tv_custom_dialog_setting_messageTopStr);
        TextView messagemiddleStr = CustomView.findViewById(R.id.tv_custom_dialog_setting_messageMiddleStr);
        TextView confirm = CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);
        if (!String.valueOf(resid).equals("null")) {
            Picasso.with(mContext).load(resid).into(image);
        }
        if (null != titleStr && !titleStr.equals("")) {
            messagetopStr.setText(titleStr);
        } else {
            messagetopStr.setVisibility(View.GONE);
        }
        if (null != messageStr && !messageStr.equals("")) {
            messagemiddleStr.setText(messageStr);
        } else {
            messagemiddleStr.setVisibility(View.GONE);
        }
        if (null != confirm) {
            confirm.setText(confirmStr);
        }
        if (confirm != null) {
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onSaveClick();
                    }
                    dimissDialog();
                }
            });
        }

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        assert dialogWindow != null;
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.80); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }

    /**
     * 4、通用对话框（包括 内容、确定和 标题）
     *
     * @param
     * @param messageStr
     * @param confirmStr
     */
    public void setDialogTitleAndMessage(String messageStr, String confirmStr, String titleStr) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView;
        if (titleStr != null) {
            CustomView = inflater.inflate(R.layout.custom_dialog_message_title, null);
            TextView title = CustomView.findViewById(R.id.tv_custom_dialog_setting_title);
            title.setText(titleStr);
        } else {
            CustomView = inflater.inflate(R.layout.custom_dialog_update, null);
        }

        TextView message = CustomView.findViewById(R.id.tv_custom_dialog_setting_message);
        TextView confirm = CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);

        if (null != messageStr) {
            message.setText(messageStr);
        }
        if (null != confirmStr) {
            confirm.setText(confirmStr);
        }


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onSaveClick();
                }
                dimissDialog();
            }
        });

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.80); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    /**
     * 5、 通用对话框（包括 内容、确定和 标题）
     *
     * @param
     * @param messageStr
     * @param confirmStr
     */
    public void setDialogTitleAndLongMessage(String messageStr, String confirmStr, String titleStr) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView;
        if (titleStr != null) {
            CustomView = inflater.inflate(R.layout.custom_dialog_long_message_title, null);
            TextView title = (TextView) CustomView.findViewById(R.id.tv_custom_dialog_setting_title);
            title.setText(titleStr);
        } else {
            CustomView = inflater.inflate(R.layout.custom_dialog_update, null);
        }

        TextView message = CustomView.findViewById(R.id.tv_custom_dialog_setting_message);
        TextView confirm = CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);

        if (null != messageStr) {
            message.setText(messageStr);
        }
        if (null != confirmStr) {
            confirm.setText(confirmStr);
        }


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onSaveClick();
                }
                dimissDialog();
            }
        });

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.80); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    /**
     * 6、通用对话框（包括内容、确定和 标题）
     *
     * @param messageStr 中间内容
     * @param confirmStr 确认按钮
     * @param titleStr   标题
     */
    public void setDialogHint(String messageStr, String confirmStr, String titleStr) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView;
        if (titleStr != null) {
            CustomView = inflater.inflate(R.layout.custom_dialog_hint_title, null);
            TextView title = CustomView.findViewById(R.id.tv_custom_dialog_setting_title);
            title.setText(titleStr);
        } else {
            CustomView = inflater.inflate(R.layout.custom_dialog_update, null);
        }

        TextView message = CustomView.findViewById(R.id.tv_custom_dialog_setting_message);
        TextView cancel = CustomView.findViewById(R.id.tv_custom_dialog_setting_cancel);
        TextView confirm = CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);

        if (null != messageStr) {
            message.setText(messageStr);
        }
        if (null != confirmStr) {
            confirm.setText(confirmStr);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    mListener.onCancelClick();
                }
                dimissDialog();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onSaveClick();
                }
                dimissDialog();
            }
        });

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.80); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    /**
     * 7、对话框（选择）
     *
     * @param
     * @return
     */
    public void mySelectListBuilder(final List<String> list) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView = inflater.inflate(R.layout.custom_dialog_select, null);
        ListView listView = CustomView.findViewById(R.id.listView);
        if (null != list && list.size() > 0) {

            ListAdapters adapter = new ListAdapters(mContext);
            adapter.setData(list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mSaveListener != null) {
                        mSaveListener.save(position, list.get(position));
                    }
                    dimissDialog();
                }
            });
        }

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.75); // 宽度设置为屏幕的0.80
        if (null != list && list.size() > 0) {
            if (list.size() > 9) {
                p.height = (int) (ScreenUtils.getScreenHeight(mContext) * 0.80); // 宽度设置为屏幕的0.80
            }
        }
        dialogWindow.setAttributes(p);
        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mSaveListener != null) {
//                    mSaveListener.save(-1, null);
                }
            }
        });
    }

    /**
     * 8、对话框（加载等待）
     *
     * @param
     * @return
     */
    public void showDialog() {
        mDialog = new Dialog(mContext, R.style.Theme_Custom_Loading);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.custom_dialog_loading, null);
        ImageView imageView1 = view.findViewById(R.id.loading);
        Animation rotate = AnimationUtils.loadAnimation(mContext, R.anim.loading);
        imageView1.startAnimation(rotate);
        mDialog.setContentView(view);
//        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCanceledOnTouchOutside(true);
        /**
         * 设置loadingDialog不能被返回键销毁
         */
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == keyCode) {
//                    return true;
                    return false;
                }
                return false;
            }
        });
        mDialog.show();
    }

    /**
     * 9、对话框（包括 标题、内容、确定和取消,输入框）
     *
     * @param
     * @return
     */
    public void mySettingEditText(String messageStr, String confirmStr) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View CustomView = inflater.inflate(R.layout.custom_dialog_add, null);
        TextView message = CustomView.findViewById(R.id.tv_custom_dialog_setting_message);
        TextView cancel = CustomView.findViewById(R.id.tv_custom_dialog_setting_cancel);
        TextView confirm = CustomView.findViewById(R.id.tv_custom_dialog_setting_confirm);
        final EditText edit = CustomView.findViewById(R.id.edit);
        if (null != messageStr) {
            message.setText(messageStr);
        }
        if (null != messageStr && !confirmStr.equals("")) {
            confirm.setText(confirmStr);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    mListener.onCancelClick();
                }
                dimissDialog();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mSaveCancelListener) {
                    mSaveCancelListener.save(edit.getText().toString());
                }
                dimissDialog();
            }
        });

        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        mDialog.setContentView(CustomView);
        mDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = mDialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.75); // 宽度设置为屏幕的0.80
        dialogWindow.setAttributes(p);

        //点击屏幕外侧，dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
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

