package com.SunStarJ.strict_databinding

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

abstract class SBindActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = initBindingConfig()
        val db = DataBindingUtil.setContentView<ViewDataBinding>(this, config.layoutId)
        db.lifecycleOwner = this
        config.xmlBindingDataArray.forEach {
            db.setVariable(it.id, it.value)
        }

    }


    abstract fun initBindingConfig(): DataBindingConfig

}