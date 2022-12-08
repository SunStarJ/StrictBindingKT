package com.sunstar_j.testbinding

import android.content.Context
import android.util.Log
import android.view.View
import com.sunstar_j.strict_databinding.SBindAdapter
import com.sunstar_j.strict_databinding.XMLBindingData

class TestAdapter(context: Context) : SBindAdapter<TestData>(context) {
    override fun setItemConfig(positionData: TestData): List<XMLBindingData> = mutableListOf(
        XMLBindingData(BR.testData,positionData),
        XMLBindingData(BR.genderClick,View.OnClickListener {
            Log.e("TestAdapter", "你点击了${positionData.name}的性别，他的性别是${positionData.gander}", )
        })
    )
    override fun setLayoutId(viewType: Int): Int = R.layout.user_test
}