package com.zdy.router.vrouter

import android.content.Context
import com.zdy.router.IdisPatchData

/**
 * 创建日期：2022/9/15 on 00:15
 * 描述：
 * 作者：zhudongyong
 */
class VRouterManager {


    init {
        val clazz = "".javaClass
        val call = clazz.newInstance() as IdisPatchData
        call.disPatch("111");

    }

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




    fun sendMessage(path: String, data: String) {


    }


}