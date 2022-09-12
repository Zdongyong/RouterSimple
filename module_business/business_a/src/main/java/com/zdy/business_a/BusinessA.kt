package com.zdy.business_a

import android.util.Log
import com.zdy.router.arouter.IdisPatchData

class BusinessA : IdisPatchData {

    private var mValue:String?=""

    override fun disPatch(value: String) {
        Log.i("zdy","===disPatch===$value==")
        mValue = value
    }

    fun get():String{
        return mValue?:""
    }

}