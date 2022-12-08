package com.sunstar_j.testbinding

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel

class MainTestViewModel : ViewModel() {
    val dataList = ObservableArrayList<TestData>()


    fun addListData(){
        dataList.add(TestData("测试1",20,"男"))
        dataList.add(TestData("测试2",25,"女"))
        dataList.add(TestData("测试3",20,"男"))
        dataList.forEach {
            Log.e("MainTestViewModel", "addListData: $it", )
        }

    }
}