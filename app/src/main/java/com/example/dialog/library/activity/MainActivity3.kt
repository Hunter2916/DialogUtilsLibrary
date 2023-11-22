package com.example.dialog.library.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dialog.library.R
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_main3.*


class MainActivity3 : AppCompatActivity() {
    // mmvk全局实例
    private var mmkv: MMKV? = MMKV.defaultMMKV()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        initClick()

    }

    private fun initClick() {
        save.setOnClickListener {
//            val value = 1234567890
            val value = "MMKV 是基于 mmap 内存映射的 key-value 组件，底层序列化/反序列化使用 protobuf 实现，性能高，稳定性强。也是腾讯微信团队使用的技术"
            mmkv?.encode("id", value)
        }
        query.setOnClickListener {
//            val decodeInt = mmkv?.decodeInt("id", 0)
            val decodeInt = mmkv?.decodeString("id", "")
            show.text = decodeInt.toString();
        }
    }

}