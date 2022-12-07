package com.github.testbinding

import android.content.Context
import android.util.Log
import android.view.View
import com.github.strict_databinding.StrictBindingAdapter
import com.github.strict_databinding.XMLBindingData

class TestAdapter(context: Context) : StrictBindingAdapter<TestData>(context) {
    override fun setItemConfig(positionData: TestData): List<XMLBindingData> = mutableListOf(
        XMLBindingData(BR.testData,positionData),
        XMLBindingData(BR.genderClick,View.OnClickListener {
            Log.e("TestAdapter", "你点击了${positionData.name}的性别，他的性别是${positionData.gander}", )
        })
    )
    override fun setLayoutId(viewType: Int): Int = R.layout.user_test
}