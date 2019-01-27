package com.death.tip.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.death.tip.R
import com.death.tip.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: com.death.tip.databinding.ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG,"onCreate()")
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
    }

    override fun onDestroy() {
        Log.e(TAG,"onDestroy()")
        super.onDestroy()
    }
}

const val TAG = "MainActivity"
