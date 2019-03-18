package com.avp.mvvm_databinding

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.avp.mvvm_databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainDataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        val nameUser = "Anh Viet Pham"
        mainDataBinding.nameUser = nameUser
    }
}
