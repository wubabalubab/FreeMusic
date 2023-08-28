package com.example.freemusic.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.freemusic.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlin.random.Random

class ListActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var refreshLayout: SmartRefreshLayout
    var list: MutableList<String> = mutableListOf()
    lateinit var mAdapter:MineAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        recyclerView = findViewById(R.id.rv_list)
        refreshLayout = findViewById(R.id.refreshlayout)

        for (i in 1..5){
            list.add(Random(System.currentTimeMillis()).toString())
        }
        mAdapter=MineAdapter(list)
        recyclerView.layoutManager =LinearLayoutManager(this)
        recyclerView.adapter=mAdapter
        refreshLayout.setEnableRefresh(false)
        refreshLayout.setEnableLoadMore(true)
        refreshLayout.setEnableAutoLoadMore(false)
        refreshLayout.setEnableLoadMoreWhenContentNotFull(true)
        refreshLayout.setOnLoadMoreListener {
           addLis()
        }

    }
    var pageCount=0


    fun addLis() {

        for (i in 1..5) {
            list.add(java.util.Random().toString())
        }
//        mAdapter.notifyDataSetChanged()
        pageCount++
        refreshLayout.finishLoadMore(1000,true,pageCount>4)
    }

    class MineAdapter(data: MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_simple_text, data) {

        override fun convert(holder: BaseViewHolder, item: String) {
           holder.setText(R.id.tv_simple_item,item)
            if (holder.layoutPosition ==4) {
                holder.itemView.layoutParams.height=0
            }
        }

    }
}