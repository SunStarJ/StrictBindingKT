package com.SunStarJ.testbinding


import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.SunStarJ.strict_databinding.DataBindingConfig
import com.SunStarJ.strict_databinding.SBindActivity
import com.SunStarJ.strict_databinding.XMLBindingData
import com.github.testbinding.R
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : SBindActivity() {

    private val mainVM by viewModels<MainTestViewModel>()

    private val userVM by lazy {
        applicationVM<UserViewModel>()
    }

    private var lastIndex = -1

    private val fragmentList =
        listOf<Fragment>(HomeFragment.newInstance(), UserFragment.newInstance())

    private val dataBindingConfig by lazy {
        DataBindingConfig(
            R.layout.activity_main,
            XMLBindingData(BR.changeTextClick, OnClickListener { mainVM.addListData() }),
            XMLBindingData(BR.mainVm, mainVM),
            XMLBindingData(BR.adapter, TestAdapter(this)),
            XMLBindingData(
                BR.navigationChange,
                OnItemSelectedListener {
                    val index = when (it.itemId) {
                        R.id.main_home -> 0
                        R.id.main_my -> 1
                        else -> -1
                    }
                    changeFragment(index)
                    index != -1
                }),
            XMLBindingData(
                BR.fabClick,
                OnClickListener { userVM.setNewUserName() })
        )
    }

    override fun initBindingConfig(): DataBindingConfig {
        changeFragment(0)
        return dataBindingConfig
    }


    fun changeFragment(index: Int) {
        if (lastIndex == index) return
        val showFragment = fragmentList[index]
        val hidFragment = if (lastIndex == -1) null else fragmentList[lastIndex]
        supportFragmentManager.commit {
            hidFragment?.run {
                hide(this)
            }
            if (!showFragment.isAdded) {
                add(R.id.fcv, showFragment)
            }
            show(showFragment)
        }
        lastIndex = index;
    }

}