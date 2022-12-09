package com.sunstar_j.strict_databinding

import android.content.ContextWrapper
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

fun ViewGroup.beginTransaction(): FragmentTransaction? {
    return kotlin.runCatching {
        (this.context as FragmentActivity).supportFragmentManager.beginTransaction()
    }.getOrNull()
}

fun ViewGroup.showFragment(hideFragment: Fragment?, showFragment: Fragment) {
    val viewGroupId = id
    beginTransaction()?.run {
        if(!showFragment.isAdded){
            add(viewGroupId,showFragment)
        }
        hideFragment?.let { hide(it) }
        show(showFragment)
        commit()
    }
}