package com.zdy.business_a

import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/business/BusinessA")
class BusinessA {

    fun init(){
//        ARouter.getInstance().inject(this)
    }

    @JvmField
    @Autowired(name = "zdy")
    var value:String = "张三"

    fun getValue():String{
        Log.i("zdyzdy","======$value==")
        return value
    }

}