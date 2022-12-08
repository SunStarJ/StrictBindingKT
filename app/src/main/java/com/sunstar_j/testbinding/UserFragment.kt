package com.sunstar_j.testbinding

import com.sunstar_j.strict_databinding.DataBindingConfig
import com.sunstar_j.strict_databinding.SBindFragment
import com.sunstar_j.strict_databinding.XMLBindingData

class UserFragment private constructor() : SBindFragment() {
    private val userViewModel by lazy { //生成viewmodel
        applicationVM<UserViewModel>()
    }

    private val config: DataBindingConfig by lazy { //生成配置实例
        DataBindingConfig(R.layout.user_fragment, XMLBindingData(BR.usrVM,userViewModel))
    }

    override fun initDataBindingConfig(): DataBindingConfig = config

    companion object{
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }
}