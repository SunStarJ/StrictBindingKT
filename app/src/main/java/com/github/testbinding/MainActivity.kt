package com.github.testbinding


import android.view.View.OnClickListener
import androidx.activity.viewModels
import com.github.strict_databinding.DataBindingConfig
import com.github.strict_databinding.StirctBindingActivity
import com.github.strict_databinding.XMLBindingData

class MainActivity : StirctBindingActivity() {

    val mainVM by viewModels<MainTestViewModel>()
    override fun initBindingConfig(): DataBindingConfig =
        DataBindingConfig(
            R.layout.activity_main,
            XMLBindingData(BR.changeTextClick, OnClickListener { mainVM.addListData() }),
            XMLBindingData(BR.mainVm, mainVM),
            XMLBindingData(BR.adapter,TestAdapter(this))
        )
}