package com.github.strict_databinding

class DataBindingConfig(val layoutId: Int, vararg xmlBindingData: XMLBindingData){
    var xmlBindingDataArray = xmlBindingData
}
data class XMLBindingData(val id: Int, val value: Any)