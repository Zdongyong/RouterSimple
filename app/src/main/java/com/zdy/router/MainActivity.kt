package com.zdy.router

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.zdy.business_a.BusinessA
import com.zdy.router.arouter.RouterManager
import com.zdy.router.databinding.ActivityMainBinding

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


        mainBinding.sendMessage.apply {
            setOnClickListener {
                RouterManager.sendMessage("/service/RouterServiceImpl","zdy",businessA)
                Handler().postDelayed(Runnable {
                    mainBinding.aroutGet.text = businessA.get()
                }, 1000)
            }

        }
    }
}