package com.zdy.router

import com.alibaba.android.arouter.facade.template.IProvider


/**
 * 创建日期：2022/9/9 on 22:52
 * 描述：
 * 作者：zhudongyong
 */
interface RouterService : IProvider {

    fun sendData(data: String,disPatchData:IdisPatchData)

}