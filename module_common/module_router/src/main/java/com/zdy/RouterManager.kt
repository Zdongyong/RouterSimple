package com.zdy

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import java.util.*

/**
 * 创建日期：2022/9/9 on 21:18
 * 描述：
 * 作者：zhudongyong
 */
object RouterManager {

    fun init(isDebug:Boolean , application: Application):RouterManager{
        if (isDebug){
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(application)
        return this
    }

    fun inject(objects: Objects){
        ARouter.getInstance().inject(objects)
    }

    fun navigation(path:String){
        ARouter.getInstance().build(path).navigation()
    }

    fun sendMessage(path:String){
        ARouter.getInstance()
            .build(path)
            .withString("zdy","666")
            //发射数据
            .navigation()
    }


}