package com.zdy.business_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zdy.business_b.databinding.ActivityBusinessBactivityBinding

class BusinessBActivity : AppCompatActivity() {

    private lateinit var businessBactivityBinding: ActivityBusinessBactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        businessBactivityBinding = ActivityBusinessBactivityBinding.inflate(layoutInflater)
        setContentView(businessBactivityBinding.root)
    }
}