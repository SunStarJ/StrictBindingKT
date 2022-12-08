package com.sunstar_j.testbinding

import com.sunstar_j.strict_databinding.DataBindingConfig
import com.sunstar_j.strict_databinding.SBindFragment

class HomeFragment private constructor() : SBindFragment() {
    override fun initDataBindingConfig(): DataBindingConfig =
        DataBindingConfig(R.layout.home_fragment)


    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }

}