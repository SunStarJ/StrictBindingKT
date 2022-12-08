## 声明
本库灵感以及很多设计方式借鉴 大佬KunMinX 的[Strict-DataBinding](https://github.com/KunMinX/Strict-DataBinding)，小弟只是站在巨人的肩膀上进行KT语法的开发以及部分自己设计，感谢大佬的思路

## 撸代码之前
### 重要的事情说三遍
请在使用模块build.gradle中开启 databinding 支持以及 kapt 依赖！！

请在使用模块build.gradle中开启 databinding 支持以及 kapt 依赖！！

请在使用模块build.gradle中开启 databinding 支持以及 kapt 依赖！！

··· 

plugins {

    id 'com.android.application'

    id 'org.jetbrains.kotlin.android'

    id 'kotlin-kapt'  //这里

} 

···