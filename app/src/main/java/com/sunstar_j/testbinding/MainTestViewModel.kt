package com.sunstar_j.testbinding

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainTestViewModel : ViewModel() {

    private var lastIndex = 0
    val dataList = ObservableArrayList<TestData>()
    private val fragmentList =
        listOf<Fragment>(HomeFragment.newInstance(), UserFragment.newInstance())
    val showFragment = MutableLiveData(fragmentList[0])
    val hideFragment = MutableLiveData<Fragment>(null)
    fun addListData() {
        dataList.add(TestData("测试1", 20, "男"))
        dataList.add(TestData("测试2", 25, "女"))
        dataList.add(TestData("测试3", 20, "男"))
        dataList.forEach {
            Log.e("MainTestViewModel", "addListData: $it")
        }
    }

    fun changeFragment(index: Int) {
        if (lastIndex == index) return
        showFragment.value = fragmentList[index]
        hideFragment.value = if (lastIndex == -1) null else fragmentList[lastIndex]
        lastIndex = index
    }

}