package com.SunStarJ.testbinding

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class APP : Application(), ViewModelStoreOwner {
    private val appViewModelStore by lazy {
        ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore = appViewModelStore
}

inline fun <reified T : ViewModel> FragmentActivity.applicationVM(): T {
    return ViewModelProvider((application as APP))[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.applicationVM(): T {
    return ViewModelProvider((activity?.application as APP))[T::class.java]
}