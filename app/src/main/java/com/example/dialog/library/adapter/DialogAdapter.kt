package com.example.dialog.library.adapter

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.dialog.dialoglib.dialog.DialogManager
import com.example.dialog.dialoglib.listener.OnDialogClickListener
import com.example.dialog.dialoglib.listener.OnDialogEditListener
import com.example.dialog.library.R
import com.example.dialog.library.bean.DialogBean


/**
 *@uthor:created by Hunter2916
 *时间:2019/9/5  17:01
 *描述:
 **/
class DialogAdapter(data: List<DialogBean>) :
    BaseQuickAdapter<DialogBean, BaseViewHolder>(R.layout.item_recycler_dialog, data) {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun convert(helper: BaseViewHolder, item: DialogBean?) {
        val mDialog = DialogManager(mContext)
        helper.setText(R.id.id, item!!.id)
        helper.setText(R.id.text, item.text)
        helper.itemView.setOnClickListener {
            when (item.id) {
                "1" -> {
                    /**
                     * 只有副标题
                     */
                    mDialog.mySettingBuilder("小宝宝的内心是有点儿害怕的")
                }
                "2" -> {
                    /**
                     * 副标题，确认按钮文字
                     */
                    mDialog.mySettingBuilder("小宝宝的内心是有点儿害怕的你想好了吗？", "想好了")
                }
                "3" -> {
                    /**
                     * 图片，主标题，副标题 ，非文字按钮
                     */
                    mDialog.setDialogPictureAndMessage("小宝宝的内心是有点儿害怕的你想好了吗？", "想好了", "通知", R.mipmap.ic_launcher)

                }
                "4" -> {
                    /**
                     * 主副标题 只有确认文字按钮
                     */
                    mDialog.setDialogTitleAndMessage("小宝宝的内心是有点儿害怕的你想好了吗？", "想好了", "通知")
                }
                "5" -> {
                    /**
                     * 主副标题 非文字按钮
                     */
                    mDialog.setDialogTitleAndLongMessage("小宝宝的内心是有点儿害怕的你想好了吗？", "想好了", "通知")
//                    Toast.makeText(mContext, "你点击的是${item.text}", Toast.LENGTH_SHORT).show()
                }
                "6" -> {
                    /**
                     * 主副标题 确认按钮
                     */
                    mDialog.setDialogHint("小宝宝的内心是有点儿害怕的你想好了吗？", "想好了", "通知")
                    mDialog.setOnDialogClickListener(object : OnDialogClickListener {
                        override fun onSaveClick() {
                            Toast.makeText(mContext, "你点击的是确定", Toast.LENGTH_SHORT).show()
                        }


                        override fun onCancelClick() {
                        }
                    })
                }
                /**
                 * 可滑动的list弹框
                 */
                "7" -> {
                    val list = mutableListOf<String>()
                    for (i in 1..19) {
                        list.add("第$i")
                    }
                    mDialog.mySelectListBuilder(list)
                    mDialog.setOnDialogListListener { index, value ->
                        Toast.makeText(mContext, "你点击的是第$index$value", Toast.LENGTH_SHORT).show()
                    }
                }
                /**
                 * 数据加载进度款
                 */
                "8" -> {
                    mDialog.showDialog()

//                    Toast.makeText(mContext, "你点击的是${item.text}", Toast.LENGTH_SHORT).show()
                }
                /**
                 * 可输入弹框
                 */
                "9" -> {
                    mDialog.mySettingEditText("哈哈儿和你哥hi的hi而过湄公河", "确定")
                    mDialog.setOnDialogEditListener(object :
                        OnDialogEditListener {
                        override fun save(valueSelectedStr: String?) {
                            Toast.makeText(mContext, "你输入的是$valueSelectedStr", Toast.LENGTH_SHORT).show()
                        }

                        override fun cancel() {

                        }
                    })
                }

            }
        }
    }
}