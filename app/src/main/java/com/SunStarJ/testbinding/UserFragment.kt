package com.SunStarJ.testbinding

import com.SunStarJ.strict_databinding.DataBindingConfig
import com.SunStarJ.strict_databinding.SBindFragment
import com.SunStarJ.strict_databinding.XMLBindingData
import com.github.testbinding.R

class UserFragment private constructor() : SBindFragment() {
    private val userViewModel by lazy {
        applicationVM<UserViewModel>()
    }

    private val config: DataBindingConfig by lazy {
        DataBindingConfig(R.layout.user_fragment, XMLBindingData(BR.usrVM,userViewModel))
    }

    override fun initDataBindingConfig(): DataBindingConfig = config

    companion object{
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }
}