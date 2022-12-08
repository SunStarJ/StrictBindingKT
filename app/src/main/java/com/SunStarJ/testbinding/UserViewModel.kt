package com.SunStarJ.testbinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    fun setNewUserName() {
        userName.value = "this is change name: tony"
    }

    val userName = MutableLiveData("this is name: default")
}