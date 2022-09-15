package com.zdy.router.vrouter

import android.content.Context
import com.zdy.router.IRouter
import com.zdy.router.IdisPatchData

/**
 * 创建日期：2022/9/15 on 00:15
 * 描述：
 * 作者：zhudongyong
 */
class VRouterManager {

    private val routerMap: Map<String, Class<out IdisPatchData?>> = HashMap()

    companion object {
        private var instance: VRouterManager? = null

        @Synchronized
        fun getInstance(context: Context): VRouterManager? {
            if (null == instance) {
                instance = VRouterManager()
            }
            return instance
        }

    }

    /**
     * path = /business_b/BusinessB
     */
    fun sendMessage(path: String, data: String) {
        val split = path.split("/")
        val className = "com.zdy.router." + (split[1] + "IRouter")
        val forName: IRouter = Class.forName(className).newInstance() as IRouter
        forName.classRegister(routerMap)
        routerMap[path]?.newInstance()?.disPatch(data)
    }


}