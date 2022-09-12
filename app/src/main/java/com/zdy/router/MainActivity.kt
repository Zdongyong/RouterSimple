package com.zdy.router

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zdy.RouterManager
import com.zdy.business_a.BusinessA
import com.zdy.router.databinding.ActivityMainBinding

@Route(path = "/app/MainActivity")
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val businessA = BusinessA()

    @JvmField
    @Autowired(name = "zdy")
    var value:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.jumpToA.apply {
            setOnClickListener {
//                ARouter.getInstance().build("/app/SecondActivity").navigation()
                RouterManager.navigation("/app/SecondActivity")
            }
        }

        businessA.init()

        mainBinding.sendMessage.apply {
            setOnClickListener {
//                ARouter.getInstance()
//                    .build("/business/BusinessA")
//                    .withString("zdy","666")
//                    //发射数据
//                    .navigation()
                RouterManager.sendMessage("/app/MainActivity")
                Handler().postDelayed(Runnable {
                    mainBinding.aroutGet.text = value
                }, 1000)
            }

        }
    }
}