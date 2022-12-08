package com.github.strict_databinding

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

abstract class StirctBindingActivity : FragmentActivity() {
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