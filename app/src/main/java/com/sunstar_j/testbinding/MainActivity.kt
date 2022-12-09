package com.sunstar_j.testbinding


import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.sunstar_j.strict_databinding.DataBindingConfig
import com.sunstar_j.strict_databinding.SBindActivity
import com.sunstar_j.strict_databinding.XMLBindingData
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : SBindActivity() {

    private val mainVM by viewModels<MainTestViewModel>()

    private val userVM by lazy {
        applicationVM<UserViewModel>()
    }

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
                    mainVM.changeFragment(index)
                    index != -1
                }),
            XMLBindingData(
                BR.fabClick,
                OnClickListener { userVM.setNewUserName() })
        )
    }

    override fun initBindingConfig(): DataBindingConfig {
        return dataBindingConfig
    }
}