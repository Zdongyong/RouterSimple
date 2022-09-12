package com.zdy.router.arouter

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 创建日期：2022/9/9 on 21:18
 * 描述：
 * 作者：zhudongyong
 */
object RouterManager {

    fun init(isDebug:Boolean , application: Application): RouterManager {
        if (isDebug){
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(application)
        return this
    }

    fun navigation(path:String){
        ARouter.getInstance().build(path).navigation()
    }

    fun sendMessage(path:String,data: String,disPatchData: IdisPatchData){
        val routerService = ARouter.getInstance()
            .build(path)//发射数据
            .navigation() as RouterService
        routerService.sendData(data,disPatchData)
    }


}