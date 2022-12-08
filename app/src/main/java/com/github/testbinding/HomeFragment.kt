package com.github.testbinding

import com.github.strict_databinding.DataBindingConfig
import com.github.strict_databinding.StrictBindingFragment

class HomeFragment private constructor() : StrictBindingFragment() {
    override fun initDataBindingConfig(): DataBindingConfig =
        DataBindingConfig(R.layout.home_fragment)


    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }

}