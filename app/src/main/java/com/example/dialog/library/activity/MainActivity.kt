package com.example.dialog.library.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dialog.library.R
import com.example.dialog.library.adapter.DialogAdapter
import com.example.dialog.library.bean.DialogBean
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessController.getContext


class MainActivity : Activity() {
    val list = mutableListOf<DialogBean>()
    private var adapter: DialogAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initCLick()

    }

    private fun initCLick() {
        sample_text.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            this.startActivity(intent)
        }
    }

    private fun initView() {
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter = DialogAdapter(ArrayList())
        recycler.adapter = adapter
        setData()

    }

    private fun setData() {
        for (i in 1..9) {
            list.add(DialogBean(i.toString(), "弹框$i"))
        }
        adapter!!.setNewData(list)
    }
}