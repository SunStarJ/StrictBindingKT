## 声明
本库灵感以及很多设计方式借鉴 大佬[KunMinX](https://github.com/KunMinX) 的[Strict-DataBinding](https://github.com/KunMinX/Strict-DataBinding)，小弟只是站在巨人的肩膀上进行KT语法的开发以及部分自己设计，感谢大佬的思路

## 撸代码之前

### 本库作用
您可以阅读上面KunMinX 的源库获取最原始的解释 

下面是我这边做的简单介绍

本库的主要作用是剥离上层使用者对于binding的访问，以解决用户使用binding去调用View实例的null安全一致性的问题

### 目前可能带来的问题
* 由于剥离binding导致Activity无法对View进行访问，所以在做一些动画时可能需要去做bindingAdapter进行View操作扩展，然后进行绑定
* 关于Activity引入Fragment和Fragment切换问题，在MainActivity中有实现，但是仍旧是用到了viewId，这个暂时没想到好办法，后面看看能不能解决

### 重要的事情说三遍
请在使用模块build.gradle中开启 databinding 支持以及 kapt 依赖！！

请在使用模块build.gradle中开启 databinding 支持以及 kapt 依赖！！

请在使用模块build.gradle中开启 databinding 支持以及 kapt 依赖！！

````groovy
plugins {

    id 'com.android.application'

    id 'org.jetbrains.kotlin.android'

    id 'kotlin-kapt'  //这里

} 
````
```groovy
android {
    // ...省略N多代码
    dataBinding {
        enabled = true
    }
}

```

## 开始撸码
### 绑定工具介绍

仅仅展示数据构造，使用时按照格式传入即可

```kotlin
// layoutId 绑定viewResourceId
// xmlBindingData 绑定使用数据(可变参数，传入多少就好了)
class DataBindingConfig(val layoutId: Int, vararg xmlBindingData: XMLBindingData){
    var xmlBindingDataArray = xmlBindingData
}
// id databinding 自动生成的BR id 数据 
// value xml 文件中variable 导入绑定数据
data class XMLBindingData(val id: Int, val value: Any)

```

### Activity使用
仅展示核心代码，代码细节可以直接查看原始[MainActivity](/app/src/main/java/com/SunStarJ/testbinding/MainActivity.kt)

``` kotlin

class MainActivity : SBindActivity() {

    // ...省略代码

    private val dataBindingConfig by lazy { //构造绑定配置文件，因为直接声明的话BR没有生成就已经调用所以使用lazy，也可以在initBindingConfig() 方法中直接return DataBindingConfig 实例
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
        // ..省略代码
        return dataBindingConfig
    }
    // ...省略代码

}

```
### Adapter 实现
```xml
<androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rc_view"
            recycleViewAdapter="@{adapter}"
            recycleViewList="@{mainVm.dataList}"
            android:layout_width="match_parent"
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            android:orientation="horizontal"
            app:layout_constraintTop_toBottomOf="@+id/btn_add"
            android:layout_height="wrap_content"/>
```

Adapter 实现代码
```kotlin

class TestAdapter(context: Context) : SBindAdapter<TestData>(context) {
    override fun setItemConfig(positionData: TestData): List<XMLBindingData> = mutableListOf(
        XMLBindingData(BR.testData,positionData),
        XMLBindingData(BR.genderClick,View.OnClickListener {
            Log.e("TestAdapter", "你点击了${positionData.name}的性别，他的性别是${positionData.gander}", )
        })
    )
    override fun setLayoutId(viewType: Int): Int = R.layout.user_test
}

```

使用了BindAdapter 实现，实现方式如下

```kotlin

@BindingAdapter("recycleViewList")
fun initRecycleViewList(recyclerView: RecyclerView, dataList: ObservableArrayList<*>) {
    recyclerView.adapter?.let {
        (it as SBindAdapter<*>).initDataList(dataList)
    }
}

@BindingAdapter("recycleViewAdapter")
fun initRecycleViewAdapter(recyclerView: RecyclerView, adapter: SBindAdapter<*>) {
    recyclerView.adapter = adapter
}

```


### Fragment 核心代码

```kotlin

class UserFragment private constructor() : SBindFragment() {
    private val userViewModel by lazy { //生成viewmodel
        applicationVM<UserViewModel>()
    }

    private val config: DataBindingConfig by lazy { //生成配置实例
        DataBindingConfig(R.layout.user_fragment, XMLBindingData(BR.usrVM,userViewModel))
    }

    override fun initDataBindingConfig(): DataBindingConfig = config

    companion object{
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }
}

```

## 写在最后
再次感谢[KunMinX](https://github.com/KunMinX)大佬开源出来的实现思路

也不知道会不会有人用，如果有人用的话能不能给个start。一个不入流小Android，期待您的批评和指正，谢谢，完结撒花

