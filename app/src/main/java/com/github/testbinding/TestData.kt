package com.github.testbinding

data class TestData(var name: String, var age: Int, var gander: String){
    override fun toString(): String {
        return "TestData(name='$name', age=$age, gander='$gander')"
    }
}