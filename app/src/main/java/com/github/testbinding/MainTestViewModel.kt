package com.github.testbinding

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainTestViewModel : ViewModel() {
    val testText = MutableLiveData("123")
    val dataList = ObservableArrayList<TestData>()
    fun changeText() {
        testText.value = "老铁这是修改过的数据呢"
    }

    fun changeByPosition(position: Int) {
        testText.value = when (position) {
            0 -> "测试1"
            1 -> "测试2"
            2 -> "测试3"
            3 -> "测试4"
            else -> "测试数据"
        }
    }

    fun addListData(){
        dataList.add(TestData("测试1",20,"男"))
        dataList.add(TestData("测试2",25,"女"))
        dataList.add(TestData("测试3",20,"男"))
    }
}