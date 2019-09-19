package com.example.dialog.library.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.dialog.library.R
import com.example.dialog.library.adapter.DialogAdapter
import com.example.dialog.library.bean.DialogBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    val list = mutableListOf<DialogBean>()
    private var adapter: DialogAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()


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