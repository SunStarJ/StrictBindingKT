package com.github.testbinding

import com.github.strict_databinding.DataBindingConfig
import com.github.strict_databinding.StrictBindingFragment
import com.github.strict_databinding.XMLBindingData

class UserFragment private constructor() : StrictBindingFragment() {
    private val userViewModel by lazy {
        applicationVM<UserViewModel>()
    }

    private val config: DataBindingConfig by lazy {
        DataBindingConfig(R.layout.user_fragment, XMLBindingData(BR.usrVM,userViewModel))
    }

    override fun initDataBindingConfig(): DataBindingConfig = config

    companion object{
        fun newInstance():UserFragment{
            return UserFragment()
        }
    }
}