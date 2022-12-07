package com.github.strict_databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("recycleViewList")
fun initRecycleViewList(recyclerView: RecyclerView, dataList: ObservableArrayList<*>) {
    recyclerView.adapter?.let {
        (it as StrictBindingAdapter<*>).initDataList(dataList)
    }
}

@BindingAdapter("recycleViewAdapter")
fun initRecycleViewAdapter(recyclerView: RecyclerView, adapter: StrictBindingAdapter<*>) {
    recyclerView.adapter = adapter
}