package com.github.strict_databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class StrictBindingFragment:Fragment() {
    private lateinit var config:DataBindingConfig
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        config = initDataBindingConfig()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewDataBinding:ViewDataBinding = DataBindingUtil.inflate<ViewDataBinding?>(inflater,config.layoutId,container,false)
        viewDataBinding.lifecycleOwner = this
        config.xmlBindingDataArray.forEach {
            viewDataBinding.setVariable(it.id,it.value)
        }
        return viewDataBinding.root
    }

    abstract fun initDataBindingConfig():DataBindingConfig

}