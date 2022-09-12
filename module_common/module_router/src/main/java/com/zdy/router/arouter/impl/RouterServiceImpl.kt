package com.zdy.router.arouter.impl

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.zdy.router.arouter.IdisPatchData
import com.zdy.router.arouter.RouterService

/**
 * 创建日期：2022/9/13 on 00:04
 * 描述：
 * 作者：zhudongyong
 */
@Route(path = "/service/RouterServiceImpl")
class RouterServiceImpl : RouterService {

    override fun sendData(data: String,disPatchData: IdisPatchData) {
        Log.i("zdy", "========$data===")
        disPatchData?.disPatch(data)
    }

    override fun init(context: Context?) {
    }


}