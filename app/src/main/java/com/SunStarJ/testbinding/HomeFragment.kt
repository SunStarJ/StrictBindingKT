package com.SunStarJ.testbinding

import com.SunStarJ.strict_databinding.DataBindingConfig
import com.SunStarJ.strict_databinding.SBindFragment
import com.github.testbinding.R

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