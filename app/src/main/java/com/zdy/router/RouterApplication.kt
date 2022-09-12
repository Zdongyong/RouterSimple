package com.zdy.router

import android.app.Application
import com.zdy.router.arouter.RouterManager

/**
 * 创建日期：2022/9/9 on 23:51
 * 描述：
 * 作者：zhudongyong
 */
class RouterApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        RouterManager.init(BuildConfig.DEBUG, this)
    }

}