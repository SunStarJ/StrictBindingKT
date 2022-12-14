package com.sunstar_j.strict_databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

@BindingAdapter("recycleViewList")
fun initRecycleViewList(recyclerView: RecyclerView, dataList: ObservableArrayList<*>) {
    recyclerView.adapter?.let {
        (it as SBindAdapter<*>).initDataList(dataList)
    }
}

@BindingAdapter("recycleViewAdapter")
fun initRecycleViewAdapter(recyclerView: RecyclerView, adapter: SBindAdapter<*>) {
    recyclerView.adapter = adapter
}

@BindingAdapter("bottomNavigationChange")
fun initBottomNavigationChange(
    bottomNavigationView: BottomNavigationView,
    itemSelectListener: NavigationBarView.OnItemSelectedListener
) {
    bottomNavigationView.setOnItemSelectedListener(itemSelectListener)
}