package com.zdy.business_b;

import android.util.Log;

import com.zdy.module_router_annotation.VRouter;
import com.zdy.router.IdisPatchData;

import androidx.annotation.NonNull;

/**
 * 创建日期：2022/9/14 on 22:45
 * 描述：
 * 作者：zhudongyong
 */
@VRouter(path = "/business_b/BusinessBB")
public class BusinessBB implements IdisPatchData {


    @Override
    public void disPatch(@NonNull String data) {
        Log.i("zdy","========"+data);
    }
}